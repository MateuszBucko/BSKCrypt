package app;

import crypt.Decrypting;
import crypt.Encrypting;

public class Main {

    public static void main(String[] args) {
        String text = "CRYPTOGRAPHY";

        String cesarEncrypt = Encrypting.cesarEncryptB(text,7,3);

        System.out.println(cesarEncrypt);

        String cesarDecrypt = Decrypting.cesarDecryptB(cesarEncrypt,7,3);

        System.out.println(cesarDecrypt);

    }
}
