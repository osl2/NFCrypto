package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PlainKeyTestSeries {
    Key key;

    @Parameterized.Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"ABCDEFGHIJKLMNOPQRSTUVWXYZ",   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"},
                {"",""}
        });
    }

    @Parameterized.Parameter
    public String plainText;

    @Parameterized.Parameter(1)
    public String encryptedText;

    @Before
    public void setUp() {
        key = new PlainKey();
    }

    @Test
    public void encrypt() {
        String encrypted = key.encrypt(plainText);
        assertEquals(encryptedText, encrypted);
    }

    @Test
    public void decrypt() {
        String decrypted = key.decrypt(encryptedText);
        assertEquals(plainText,decrypted);
    }
}