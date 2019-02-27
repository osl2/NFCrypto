package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlainKeyTest {
    Key key;
    @Before
    public void before() {
        key = new PlainKey();
    }

    @Test
    public void encrypt() {
        String testString = "HalloWelt";
        String testEncoded = "HalloWelt";
        assertSame(key.encrypt(testString),testEncoded);
    }

    @Test
    public void decrypt() {
        String testString = "HalloWelt";
        String testEncoded = "HalloWelt";
        assertSame(key.decrypt(testEncoded),testString);
    }
}