package edu.kit.nfcrypto.keys;

import org.junit.Test;

import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static org.junit.Assert.*;

public class VigenereKeyTest {

    @Test
    public void suffix() {
        Key key = new VigenereKey();
        assertEquals(14, key.suffix().length());
    }

    @Test
    public void encodeKey() {
        String keyData = "ABCDEZ";
        Key key = new VigenereKey(keyData);
        assertEquals("KEYVIG" + keyData, key.encodeKey());
    }

    @Test
    public void getKeyLength() {
        assertEquals(VigenereKey.getKeylength(),6);
    }

    @Test(expected = KeyFormatException.class)
    public void createKeyFromNumber() {
        new VigenereKey("1");
    }

    @Test(expected = KeyFormatException.class)
    public void createKeyFromNull() {
        new VigenereKey(null);
    }

    @Test(expected = KeyFormatException.class)
    public void createKeyFromEmpty() {
        new VigenereKey("");
    }

    @Test(expected = KeyFormatException.class)
    public void createKeyFromShort() {
        new VigenereKey("AB");
    }

    @Test(expected = KeyFormatException.class)
    public void createKeyFromLong() {
        new VigenereKey("AAAAAAB");
    }
}