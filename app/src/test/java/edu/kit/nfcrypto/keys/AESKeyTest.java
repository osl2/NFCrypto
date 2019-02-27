package edu.kit.nfcrypto.keys;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class AESKeyTest {

    @Test
    @Ignore //wegen Mocking
    public void suffix() {
        Key key = new AESKey();
        assertSame(key.suffix(), "ENTSCHLUSSELT");
    }

    @Test
    @Ignore
    public void encrypt() {
    }

    @Test
    @Ignore
    public void decrypt() {
    }
}