package tests;

import crypt.Encrypting;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by X on 05.03.2017.
 */
public class CryptTest {

    @Test
    public void fenceTest(){
        String textToCode = "CRYPTOGRAPHY";
        String codedText = Encrypting.fenceEncrypt(3,textToCode);
        Assert.assertEquals("CTARPORPYYGH",codedText);
    }
}
