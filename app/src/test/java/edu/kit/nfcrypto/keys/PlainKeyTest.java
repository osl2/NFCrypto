package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class PlainKeyTest {
    @Test
    public void suffix() {
        Key key = new PlainKey();
        assertEquals(14, key.suffix().length());
    }

    @Test
    public void encodeKey() {
        Key key = new PlainKey();
        assertEquals("KEYPLA",key.encodeKey());
    }
}