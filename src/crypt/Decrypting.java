package crypt;

import javax.rmi.CORBA.Util;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Bućko on 03.03.2017.
 */
public class Decrypting {

    public static String fenceDecrypt(int key, String text) {

        //zienne pomocnicze do numeru wiersza , kolumny, aktualnego znaku
        int rowNumber = 0, colNumber = 0, index = 0;

        //zakodowany tekst
        StringBuilder codedText = new StringBuilder(text.length());

        //tablica kodująca
        char[][] codedTab = new char[key][text.length()];

        //domyslny kierunek przeglądania dół
        boolean directionDown = true;


        //wypelnienie tablicy znakami - znacznikami
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                codedTab[i][j] = '\n';

        //zaznaczenie miejsc gdzie będą znaki - poprzez gwiazdkę
        for (int i = 0; i < text.length(); i++) {
            if (rowNumber == 0) directionDown = true; // kierunek dol
            if (rowNumber == key - 1) directionDown = false; // kierunek góra
            codedTab[rowNumber][i] = '*';
            if (directionDown) rowNumber++; // jak dol zwieksz wiersz
            else rowNumber--; // jak góra zmniejsz wiersz
        }

        //wstawienie znaków w odpowiednie miejsca tabeli
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (codedTab[i][j] == '*' && index < text.length())
                    codedTab[i][j] = text.charAt(index++);

        //resetujemy
        rowNumber = 0;
        colNumber = 0;


        //przejście po tablicy i zczytanie właściwych znaków
        for (int i = 0; i < text.length(); i++) {
            if (rowNumber == 0) directionDown = true;
            if (rowNumber == key - 1) directionDown = false;
            if (codedTab[rowNumber][colNumber] != '*') {
                codedText.append(codedTab[rowNumber][colNumber++]);

            }
            if (directionDown) rowNumber++;
            else rowNumber--;
        }

        return codedText.toString();
    }

    public static String matrixDecryptA(String textToDecrypt, int[] key) {

        int matrixRows = textToDecrypt.length() % key.length > 0 ? (textToDecrypt.length() / key.length) + 1 : textToDecrypt.length() / key.length;
        int matrixColumns = key.length;

        char[][] matrix = new char[matrixRows][key.length];

        StringBuilder decodedText = new StringBuilder(textToDecrypt.length());


        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < matrixColumns; j++) {
                if (j + (i * key.length) <= textToDecrypt.length())
                    if (j + (i * key.length) == textToDecrypt.length())
                        matrix[i][(key[j] - 1)] = textToDecrypt.charAt(j - 1 + i * key.length);
                    else
                        matrix[i][(key[j] - 1)] = textToDecrypt.charAt(j + i * key.length);
            }
        }
        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < matrixColumns; j++) {
                decodedText.append(matrix[i][j]);
            }
        }
        return decodedText.toString().substring(0, textToDecrypt.length());
    }

    public static String matrixDecryptB(String textToDecrypt, String key) {
        //Utworzenie klucza
        int count = 0;
        int[] klucz = new int[key.length()];

        for (char let = 'A'; let <= 'Z'; ++let) {

            for (int i = 0; i < key.length(); ++i) {

                if (key.charAt(i) == let) {
                    klucz[i] = count;
                    ++count;
                }
            }
        }

        int rows = textToDecrypt.length() / key.length();

        if ((textToDecrypt.length() % key.length()) > 0)
            rows = rows + 1;

        int longercolumns = 0;

        if (rows != textToDecrypt.length() / key.length())
            longercolumns = textToDecrypt.length() - ((rows - 1) * key.length());

        char[][] kodowanie = new char[rows][key.length()];
        int actualrows = 0;
        int letterindex = 0;

        for (int i = 0; i < key.length(); ++i) {

            for (int j = 0; j < key.length(); ++j) {

                if (klucz[j] == i) {
                    if (j < longercolumns || longercolumns == 0)
                        actualrows = rows;
                    else
                        actualrows = rows - 1;

                    for (int u = 0; u < actualrows; ++u) {
                        kodowanie[u][j] = textToDecrypt.charAt(letterindex);

                        ++letterindex;

                    }//Trzeci for
                }//Pierszy if
            }//Drugi for
        }//Pierwszy for


        String answer = "";

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < key.length(); ++j)
                answer = answer + kodowanie[i][j];
        }

        return answer.toString();
    }

    public static String matrixDecryptC(String textToDecrypt, String key) {
        //Utworzenie klucza
        int count = 0;
        int[] klucz = new int[key.length()];

        for (char let = 'A'; let <= 'Z'; ++let) {

            for (int i = 0; i < key.length(); ++i) {

                if (key.charAt(i) == let) {
                    klucz[i] = count;
                    ++count;
                }
            }
        }


        //licznik liter
        int lettercounter = 0;

        //licznik wierszy
        int rows = 0;

        //wspolrzedne dla kazdej litery
        int[][] position = new int[textToDecrypt.length()][2];

        while (lettercounter < textToDecrypt.length()) {

            for (int i = 0; i < key.length(); ++i) {

                for (int u = 0; u < key.length(); ++u) {

                    if (i == klucz[u]) {

                        for (int j = 0; j < u + 1; ++j) {

                            if (lettercounter >= textToDecrypt.length())
                                break;


                            if (lettercounter < textToDecrypt.length()) {

                                position[lettercounter][0] = rows;
                                position[lettercounter][1] = j;
                                ++lettercounter;
                            }
                        }//
                        ++rows;
                    }
                }//drugi for
            }//pierwszy for
        }//konie while

        char[][] ciphertable = new char[rows][key.length()];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); ++j)
                ciphertable[i][j] = ' ';
        }


        for (int i = 0; i < lettercounter; ++i) {

            if (!(textToDecrypt.charAt(i) == ' '))
                ciphertable[position[i][0]][position[i][1]] = '*';
        }



        int number = 0;
        int actualIndex = 0;
        String codedword = "";

        for (int i = 0; i < key.length(); ++i) {

            for (int k = 0; k < key.length(); ++k) {
                if (klucz[k] == i)
                    number = k;
            }

            for (int j = 0; j < rows; ++j) {
                if (ciphertable[j][number] == '*') {
                    ciphertable[j][number] = textToDecrypt.charAt(actualIndex);

                    actualIndex++;

                }
                // codedword = codedword + ciphertable[j][number];
            }
        }



        String answer = "";

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < key.length(); ++j)
                if (ciphertable[i][j] != ' ')
                    answer = answer + ciphertable[i][j];
        }


        return answer;
    }

    public static String cesarDecryptA(String textToDecrypt, int key) {
        StringBuilder decryptedText = new StringBuilder(textToDecrypt.length());
        for (int i = 0; i < textToDecrypt.length(); i++) {
            int index = (Utils.ALPHABET.indexOf(textToDecrypt.charAt(i)) + (Utils.ALPHABET.length() - key)) % Utils.ALPHABET.length();
            decryptedText.append(Utils.ALPHABET.charAt(index));
        }
        return decryptedText.toString();
    }

    public static String cesarDecryptB(String textToDecrypt, int key0, int key1) {
        int fi_26 = 12;
        StringBuilder decryptedText = new StringBuilder(textToDecrypt.length());
        for (int i = 0; i < textToDecrypt.length(); i++) {
            //TODO: change to BigInt for bigger keys value!!!!
            BigInteger pow = BigDecimal.valueOf(Math.pow(key1, fi_26 - 1)).toBigInteger();
            BigInteger index = BigInteger.valueOf(( (Utils.ALPHABET.indexOf(textToDecrypt.charAt(i)) + (Utils.ALPHABET.length() - key0))  )).multiply(pow);
            index = index.mod(BigInteger.valueOf(Utils.ALPHABET.length()));
            decryptedText.append(Utils.ALPHABET.charAt(index.intValue()));
        }

        return decryptedText.toString();
    }

    public static String vigenereDecrypt(String textToDecrypt, String key) {
        StringBuilder uncoded = new StringBuilder(textToDecrypt.length());
        for (int i = 0; i < textToDecrypt.length(); i++) {
            int index = (Utils.ALPHABET.indexOf(textToDecrypt.charAt(i)) - Utils.ALPHABET.indexOf(key.charAt(i)) + Utils.ALPHABET.length()) % Utils.ALPHABET.length();
            uncoded.append(Utils.ALPHABET.charAt(index));
        }

        return uncoded.toString();
    }
}
