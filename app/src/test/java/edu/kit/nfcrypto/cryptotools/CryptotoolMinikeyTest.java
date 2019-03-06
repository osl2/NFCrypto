package edu.kit.nfcrypto.cryptotools;

import org.junit.Before;
import org.junit.Test;

public class CryptotoolMinikeyTest {
    private Cryptotool ct;
    private final String TEST_TEXT = "HALLO WELT";
    private final String TEST_HELP = "ENTSCHLUESSELT";

    @Before
    public void setUp() {
        ct = new CryptotoolMinikey();
    }

    @Test(expected = NullPointerException.class)
    public void helpNullError() {
        ct.crack(TEST_TEXT, null);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void helpEmptyError() {
        ct.crack(TEST_TEXT, "");
    }

    @Test(expected = NullPointerException.class)
    public void textNullError() {
        ct.crack(null,TEST_HELP);
    }

    @Test
    public void testEmpty() {
        ct.crack("",TEST_HELP);
    }
}