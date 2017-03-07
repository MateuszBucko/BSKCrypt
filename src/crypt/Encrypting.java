package crypt;

/**
 * Created by Mateusz Bućko on 03.03.2017.
 */
public class Encrypting {


    public static String fenceEncrypt(int numberOfRows, String textToEncrypt) {
        char[][] fenceTable = new char[numberOfRows][textToEncrypt.length()];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < textToEncrypt.length(); j++) {
                fenceTable[i][j] = '\n';
            }
        }

        //wstepnie ustawiamy kierunek na "w górę"
        boolean directionDown = false;
        int row = 0;

        for (int i = 0; i < textToEncrypt.length(); i++) {
            // jezeli wiersz jest pierwszy lub ostatni, zmieniamy kierunek
            if (row == 0 || row == numberOfRows - 1) {
                directionDown = !directionDown;
            }
            //wstawiamy znak
            fenceTable[row][i] = textToEncrypt.charAt(i);

            //zmieniamy numer wiersza zgodnie z kierunkiem (directionDown)
            if (directionDown) row++;
            else row--;
        }

        StringBuilder codedText = new StringBuilder(textToEncrypt.length());

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < textToEncrypt.length(); j++) {
                if (fenceTable[i][j] != '\n') codedText.append(fenceTable[i][j]);
            }
        }
        return codedText.toString();
    }

    public static String matrixEncryptA(String textToEncrypt, int[] key) {
        //key = new int[]{3, 4, 1, 5, 2};
        int matrixRows = textToEncrypt.length() % key.length > 0 ? (textToEncrypt.length() / key.length) + 1 : textToEncrypt.length() / key.length;

        char[][] matrix = new char[matrixRows][key.length];
        int letterNumber = 0;

        StringBuilder codedText = new StringBuilder(textToEncrypt.length());

        //wstawienie słowa do macierzy
        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < key.length; j++) {
                if (letterNumber < textToEncrypt.length()) {
                    matrix[i][j] = textToEncrypt.charAt(letterNumber);
                    letterNumber++;
                } else {
                    matrix[i][j] = '\n';
                }
            }
        }

        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < key.length; j++) {
                if (matrix[i][key[j] - 1] != '\n')
                    codedText.append(matrix[i][key[j] - 1]);
            }
        }

        return codedText.toString();
    }

    public static String matrixEncryptB(String textToEncrypt, String key) {
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

        // Wyznaczenie liczby wierszy
        int rows = textToEncrypt.length() / key.length();

        if ((textToEncrypt.length() % key.length()) > 0)
            rows = rows + 1;


        char[][] kodowanie = new char[rows][key.length()];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); ++j)
                kodowanie[i][j] = ' ';
        }


        int count2 = 0;


        // Uzupełnienie tabliczy słowem do zaszyfrowania
        for (int i = 0; i < rows; ++i) {

            for (int j = 0; j < key.length(); ++j) {

                if (count2 < textToEncrypt.length()) {
                    if (textToEncrypt.charAt(count2) == ' ')
                        ++count2;
                    kodowanie[i][j] = textToEncrypt.charAt(count2);
                    ++count2;
                }
            }
        }

        String codedword = "";

        int number = 0;

        //Odczytanie kolumn do zakodowania według klucza
        for (int i = 0; i < key.length(); ++i) {

            for (int k = 0; k < key.length(); ++k) {
                if (klucz[k] == i)
                    number = k;
            }

            for (int j = 0; j < rows; ++j) {
                if (kodowanie[j][number] != ' ')
                    codedword = codedword + kodowanie[j][number];
            }
        }
        return codedword;
    }

    public static String matrixEncryptC(String textToEncrypt, String key) {
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
        int[][] position = new int[textToEncrypt.length()][2];

        while (lettercounter < textToEncrypt.length()) {

            for (int i = 0; i < key.length(); ++i) {

                for (int u = 0; u < key.length(); ++u) {

                    if (i == klucz[u]) {

                        for (int j = 0; j < u + 1; ++j) {

                            if (lettercounter >= textToEncrypt.length())
                                break;


                            while (textToEncrypt.charAt(lettercounter) == ' ' && lettercounter < textToEncrypt.length())
                                ++lettercounter;


                            if (lettercounter < textToEncrypt.length()) {

                                position[lettercounter][0] = rows;
                                position[lettercounter][1] = j;
                                ++lettercounter;
                            }
                        }//
                        ++rows;
                    }
                }//drugi for
            }//pierwszy for
        }//koniec while

        char[][] ciphertable = new char[rows][key.length()];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); ++j)
                ciphertable[i][j] = ' ';
        }


        for (int i = 0; i < lettercounter; ++i) {

            if (!(textToEncrypt.charAt(i) == ' '))
                ciphertable[position[i][0]][position[i][1]] = textToEncrypt.charAt(i);
        }

        int number = 0;
        String codedword = "";

        for (int i = 0; i < key.length(); ++i) {

            for (int k = 0; k < key.length(); ++k) {
                if (klucz[k] == i)
                    number = k;
            }

            for (int j = 0; j < rows; ++j) {
                if (!(ciphertable[j][number] == ' '))
                    codedword = codedword + ciphertable[j][number];
            }
        }

        return codedword;
    }

    public static String cesarEncryptA(String textToEncrypt, int key) {
        StringBuilder coded = new StringBuilder(textToEncrypt.length());

        for (int i = 0; i < textToEncrypt.length(); i++) {
            int index = (Utils.ALPHABET.indexOf(textToEncrypt.charAt(i)) + key) % Utils.ALPHABET.length();
            coded.append(Utils.ALPHABET.charAt(index));
        }


        return coded.toString();
    }

    public static String cesarEncryptB(String textToEncrypt, int key0, int key1) {

        StringBuilder coded = new StringBuilder(textToEncrypt.length());
        for (int i = 0; i < textToEncrypt.length(); i++) {
            int index = (Utils.ALPHABET.indexOf(textToEncrypt.charAt(i)) * key1 + key0) % Utils.ALPHABET.length();
            coded.append(Utils.ALPHABET.charAt(index));
        }

        return coded.toString();
    }

    public static String vigenereEncrypt(String textToEncrypt, String key) {
        StringBuilder coded = new StringBuilder(textToEncrypt.length());
        for (int i = 0; i < textToEncrypt.length(); i++) {
            int index = (Utils.ALPHABET.indexOf(textToEncrypt.charAt(i)) + Utils.ALPHABET.indexOf(key.charAt(i))) % Utils.ALPHABET.length();
            coded.append(Utils.ALPHABET.charAt(index));
        }

        return coded.toString();
    }

}
