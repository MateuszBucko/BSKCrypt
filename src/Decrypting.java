/**
 * Created by Mateusz Bućko on 03.03.2017.
 */
public class Decrypting {

    public static String fenceDecrypt(int numberOfRows, String textToDecrypt){
        //TODO: impl
        return null;
    }

    public static String matrixDecryptA(String textToDecrypt){
        //TODO: impl
        return null;
    }

    public static String matrixDecryptB(String textToDecrypt,String key){
        //TODO: impl
        return null;
    }

    public static String matrixDecryptC(String textToDecrypt,String key){
        //TODO: impl
        return null;
    }

    public static String cesarDecryptA(String textToDecrypt,int key){
        //TODO: impl
        return null;
    }

    public static String cesarDecryptB(String textToDecrypt,int key0,int key1){
        //TODO: impl
        return null;
    }

    public static String vigenereDecrypt(String textToDecrypt, String key){
        StringBuilder uncoded = new StringBuilder(textToDecrypt.length());
        for(int i=0;i <textToDecrypt.length();i++){
            int index = (Utils.ALPHABET.indexOf(textToDecrypt.charAt(i)) - Utils.ALPHABET.indexOf(key.charAt(i))+Utils.ALPHABET.length())%Utils.ALPHABET.length();
            uncoded.append(Utils.ALPHABET.charAt(index));
        }

        return uncoded.toString();
    }
}
