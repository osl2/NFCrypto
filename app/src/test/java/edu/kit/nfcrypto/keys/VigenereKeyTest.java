package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class VigenereKeyTest {
    Key keyRead;
    Key keyRandom;

    @Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"ABCDEZ",  "HALLO WELT",   "HBNOS WFNW"},
                {"ABCDEZ",  "HALLO!!",      "HBNOS!!"},
                {"ABCDEZ",  "ZZZZZZZZZZ",   "ZABCDYZABC"}
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
        keyRandom = new VigenereKey();
        keyRead = new VigenereKey(keyData);
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
    public void getKeyLength() {
        assertEquals(VigenereKey.getKeylength(),6);
    }
}