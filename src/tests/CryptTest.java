package tests;

import crypt.Decrypting;
import crypt.Encrypting;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by X on 05.03.2017.
 */
public class CryptTest {

    @Test
    public void fenceTest() {
        String textToCode = "CRYPTOGRAPHY";
        String codedText = Encrypting.fenceEncrypt(3, textToCode);
        Assert.assertEquals("CTARPORPYYGH", codedText);
        Assert.assertEquals(textToCode,Decrypting.fenceDecrypt(3,codedText));
    }

    @Test
    public void cesarATest() {
        String textToCode = "CRYPTOGRAPHY";
        String codedText = Encrypting.cesarEncryptA(textToCode, 3);
        Assert.assertEquals("FUBSWRJUDSKB", codedText);
        Assert.assertEquals(textToCode, Decrypting.cesarDecryptA(codedText, 3));
    }

    @Test
    public void vigenereTest() {
        String textToCode = "CRYPTOGRAPHY";
        String key = "BREAKBREAKBR";
        String codedText = Encrypting.vigenereEncrypt(textToCode, key);
        Assert.assertEquals("DICPDPXVAZIP", codedText);
        Assert.assertEquals(textToCode, Decrypting.vigenereDecrypt(codedText, key));
    }

    @Test
    public void matrixATest() {
        String textToCode = "CRYPTOGRAPHYOSA";
        int[] key = new int[]{3, 1, 4, 2};
        String codedText = Encrypting.matrixEncryptA(textToCode, key);
        Assert.assertEquals("YCPRGTROHAYPAOS", codedText);
    }

    @Test
    @Ignore
    public void matrixBTest() {
        String textToCode = "HEREISASECRETMESSAGEENCIPHEREDBYTRANSPOSITION";
        String key = "CONVENIENCE";
        String codedText = Encrypting.matrixEncryptB(textToCode, key);
        Assert.assertEquals("HECRNCEYIISEPSGDIRNTOAAESRMPNSSROEEBTETIAEEHS", codedText);
        Assert.assertEquals(textToCode, Decrypting.matrixDecryptB(codedText, key));

    }
}
