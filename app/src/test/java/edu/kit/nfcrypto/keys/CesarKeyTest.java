package edu.kit.nfcrypto.keys;

import org.junit.Test;

import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static org.junit.Assert.*;

public class CesarKeyTest {

    @Test(expected = KeyFormatException.class)
    public void createKeyFromLetter() {
        new CesarKey("A");
    }

    @Test(expected = KeyFormatException.class)
    public void createKeyFromNull() {
        new CesarKey(null);
    }

    @Test
    public void createKeyFromEmpty() {
        Key key = new CesarKey("");
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", key.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }
}