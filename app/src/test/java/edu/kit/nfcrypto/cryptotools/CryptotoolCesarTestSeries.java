package edu.kit.nfcrypto.cryptotools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CryptotoolCesarTestSeries {
    private static final String TEST_HELP = "ENTSCHLUESSELT";
    @Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"HALLO WELT",  "HALLO WELT",   "0", TEST_HELP},
                {"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ZABCDEFGHIJKLMNOPQRSTUVWXY", "25", "DMSRBGKTDRRDKS"},
                {"", "", "0", "ENTSCHLUESSELT"},
                {"! ? () ; , : . ", "! ? () ; , : . ", "0", TEST_HELP},
                {"", "", "1", "FOUTDIMVFTTFMU"},
                {"ZABCDEFGHIJKLMNOPQRSTUVWXY", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "1", "FOUTDIMVFTTFMU"},
                {"HALLO BOB WIE GEHT ES DIR", "MFQQT GTG BNJ LJMY JX INW", "5", "JSYXHMQZJXXJQY"},  //Entwurf
                {"IBMMP CPC XJF HFIU FT EJS", "MFQQT GTG BNJ LJMY JX INW", "4", "IRXWGLPYIWWIPX"}   //Entwurf
        });
    }

    @Parameter
    public String plaintext;

    @Parameter(1)
    public String encryptedText;

    @Parameter(2)
    public String keyString;

    @Parameter(3)
    public String help;

    @Test
    public void decrypt() {
        CryptotoolCesar cesar = new CryptotoolCesar(Integer.parseInt(keyString));
        assertEquals(plaintext, cesar.decrypt(encryptedText));
    }

    @Test
    public void crack() {
        CryptotoolCesar cesar = new CryptotoolCesar();
        assertEquals(plaintext, cesar.crack(encryptedText, help));
    }


}