package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VigenereKeyTest {
    Key key;
    @Before
    public void before() {
        String keyData = "ABCDEF";
        key = new VigenereKey(keyData);
    }


    @Test
    public void encrypt() {
        String testString = "HALLO WELT";
        String testEncoded = "HBNOS WFNW";
        assertEquals(key.encrypt(testString),testEncoded);
    }


    @Test
    public void decrypt() {
        String testString = "HALLO WELT";
        String testEncoded = "HBNOS WFNW";
        assertEquals(key.decrypt(testEncoded),testString);
    }

    @Test
    public void getKeylength() {
        assertEquals(VigenereKey.getKeylength(),6);
    }
}