package crypt;

/**
 * Created by Mateusz Bućko on 03.03.2017.
 */
public class Decrypting {

    public static String fenceDecrypt(int numberOfRows, String textToEncrypt) {
        return null;
    }

    public static String matrixDecryptA(String textToDecrypt) {

        return null;
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

        return answer;
    }

    public static String matrixDecryptC(String textToDecrypt, String key) {
        //TODO: impl
        return null;
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
        int fi_26 = 9;
        StringBuilder decryptedText = new StringBuilder(textToDecrypt.length());
        for (int i = 0; i < textToDecrypt.length(); i++) {
            //TODO: change to BigInt for bigger keys value!!!!
            int index = (Utils.ALPHABET.indexOf(textToDecrypt.charAt(i)) + (Utils.ALPHABET.length() - key0)) * (int) Math.pow(key1, fi_26 - 1) % Utils.ALPHABET.length();
            decryptedText.append(Utils.ALPHABET.charAt(index));
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
