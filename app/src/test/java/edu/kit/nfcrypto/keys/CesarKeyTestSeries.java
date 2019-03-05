package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CesarKeyTestSeries {

    Key keyRead;
    Key keyRandom;

    @Parameterized.Parameters
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
                {"9",   "! ? , : . ; ( ) ",             "! ? , : . ; ( ) "}             //Sonderzeichen

        });
    }

    @Parameterized.Parameter
    public String keyData;

    @Parameterized.Parameter(1)
    public String plainText;

    @Parameterized.Parameter(2)
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