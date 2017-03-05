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

    public static String matrixEncryptA(String textToEncrypt,int[] key) {
        key = new int[]{3, 4, 1, 5, 2};
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

    public static String matrixEncryptB(String textToEncrypt,String key){
        //TODO: impl
        return null;
    }

    public static String matrixEncryptC(String textToEncrypt,String key){
        //TODO: impl
        return null;
    }

    public static String cesarEncryptA(String textToEncrypt,int key){
        StringBuilder coded = new StringBuilder(textToEncrypt.length());

        for (int i = 0; i < textToEncrypt.length(); i++) {
            int index = (Utils.ALPHABET.indexOf(textToEncrypt.charAt(i)) + key) % Utils.ALPHABET.length();
            coded.append(Utils.ALPHABET.charAt(index));
        }


        return coded.toString();
    }

    public static String cesarEncryptB(String textToEncrypt,int key0,int key1){

        StringBuilder coded = new StringBuilder(textToEncrypt.length());
        for(int i=0;i<textToEncrypt.length();i++) {
            int index = (Utils.ALPHABET.indexOf(textToEncrypt.charAt(i))*key1+key0)%Utils.ALPHABET.length();
            coded.append(Utils.ALPHABET.charAt(index));
        }

        return coded.toString();
    }

    public static String vigenereEncrypt(String textToEncrypt, String key){
        StringBuilder coded = new StringBuilder(textToEncrypt.length());
        for(int i=0;i <textToEncrypt.length();i++){
            int index = (Utils.ALPHABET.indexOf(textToEncrypt.charAt(i))+Utils.ALPHABET.indexOf(key.charAt(i)))%Utils.ALPHABET.length();
            coded.append(Utils.ALPHABET.charAt(index));
        }

        return coded.toString();
    }

}
