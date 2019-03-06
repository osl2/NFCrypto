package edu.kit.nfcrypto.cryptotools;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CryptotoolMinikeyTestSeries {
    private Cryptotool ct;

    @Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"HALLO WELT",  "ENTSCHLUESSELT",   "HALLO WELT"},
                {"IBMMP XFMU",  "FOUTDIMVFTTFMU",   "HALLO WELT"},
                {"HALLO WELT",  "FOUTDIMVFTTFMU",   "GZKKN VDKS"}, //Falscher Schlüssel
        });
    }

    @Parameter
    public String text;

    @Parameter(1)
    public String help;

    @Parameter(2)
    public String solution;

    @Before
    public void setUp() {
        ct = new CryptotoolMinikey();
    }

    @Test
    public void crack() {
        String cracked = ct.crack(text,help);
        assertEquals(solution, cracked);
    }
}