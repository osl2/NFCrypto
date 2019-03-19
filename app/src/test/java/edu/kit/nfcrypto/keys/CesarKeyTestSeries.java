package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CesarKeyTestSeries {

    private Key keyRead;
    private Key keyRandom;

    @Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"0",   "ABCDEFGHIJKLMNOPQRSTUVWXYZ",   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"},  //encrypt()
                {"0",   "",                             ""},                            //NULL
                {"25",  "ABCDEFGHIJKLMNOPQRSTUVWXYZ",   "ZABCDEFGHIJKLMNOPQRSTUVWXY"},  //Z
                {"9",   "! ? , : . ; ( ) ",             "! ? , : . ; ( ) "},            //Sonderzeichen
                {"0",   "ABCDE",                        "ABCDE"},                       //decrypt
                {"25",  "ABCDEFGHIJKLMNOPQRSTUVWXYZ",   "ZABCDEFGHIJKLMNOPQRSTUVWXY"},  //Z
                {"1",   "ABCDEFGHIJKLMNOPQRSTUVWXYZ",   "BCDEFGHIJKLMNOPQRSTUVWXYZA"},  //B
                {"1",   "",                             ""},                            //NULL
                {"9",   "! ? , : . ; ( ) ",             "! ? , : . ; ( ) "},            //Sonderzeichen
                {"5",   "HALLO BOB WIE GEHT ES DIR",    "MFQQT GTG BNJ LJMY JX INW"}    //Entwurf
        });
    }

    @Parameter
    public String keyData;

    @Parameter(1)
    public String plainText;

    @Parameter(2)
    public String encryptedText;

    @Before
    public void setUp() {
        keyRandom = new CesarKey();
        keyRead = new CesarKey(keyData);
    }

    @Test
    public void encrypt() {
        String encrypted = keyRead.encrypt(plainText);
        assertEquals(encryptedText, encrypted);
    }

    @Test
    public void decrypt() {
        String decrypted = keyRead.decrypt(encryptedText);
        assertEquals(plainText,decrypted);
    }

    @Test
    public void randomKey() {
        assertEquals(plainText,keyRandom.decrypt(keyRandom.encrypt(plainText)));
    }

    @Test
    public void keyData() {
        assertEquals(Integer.parseInt(keyData),((CesarKey) keyRead).getKeyData());
    }
}