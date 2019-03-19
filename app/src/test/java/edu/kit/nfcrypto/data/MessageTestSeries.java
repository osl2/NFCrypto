package edu.kit.nfcrypto.data;

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
public class MessageTestSeries {
    private Message message;

    @Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"HALLO WELT",  "HALLO WELT",   "PLA", "MESPLAHALLO WELT"},
                {"HALLO BOB WIE GEHT ES DIR",    "MFQQT GTG BNJ LJMY JX INW", "CES", "MESCESMFQQT GTG BNJ LJMY JX INW"},
                {"HALLO WELT",   "HBNOS WFNW", "VIG", "MESVIGHBNOS WFNW"},
                {"HALLO BOB", "NUxBRHVMK1BKY0o0M0RkeA==", "AES", "MESAESNUxBRHVMK1BKY0o0M0RkeA=="}
        });
    }

    @Parameter
    public String plaintext;

    @Parameter(1)
    public String encryptedText;

    @Parameter(2)
    public String modeString;

    @Parameter(3)
    public String encoded;

    @Before
    public void setUp() {
        Mode mode = Mode.toMode(modeString);
        message = new Message(plaintext, encryptedText, mode);
    }

    @Test
    public void encodeEncryptedText() {
        assertEquals(message.encodeEncryptedText(),encoded);
    }

    @Test
    public void getPlainText() {
        assertEquals(plaintext, message.getPlaintext());
    }

    @Test
    public void getEncryptedText() {
        assertEquals(encryptedText, message.getEncryptedText());
    }

}