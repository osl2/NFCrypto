package edu.kit.nfcrypto;

import org.junit.Test;

import java.security.AllPermission;

import edu.kit.nfcrypto.activities.ActivityAlice;
import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;

import static org.junit.Assert.*;

public class AliceTest {

    //##############################################################################################
    //Hier wird getCurrentMessage getestet
    //##############################################################################################

    @Test
    public void getCurrentMessage() {
        Alice alice = new Alice();
        Message testMessage = alice.getCurrentMessage();
        assertSame( testMessage, null );

    }

    //##############################################################################################
    //Hier wird setKey getestet
    //##############################################################################################

    @Test
    public void getsetKeyPLA() {
        Alice alice = new Alice();
        Key testKey = new PlainKey();
        alice.setKey(testKey);
        assertSame(alice.getKey(),testKey);
    }

    @Test
    public void getsetKeyCES() {
        Alice alice = new Alice();
        Key testKey = new CesarKey();
        alice.setKey(testKey);
        assertSame(alice.getKey(),testKey);
    }
    @Test
    public void getsetKeyVEG() {
        Alice alice = new Alice();
        Key testKey = new CesarKey();
        alice.setKey(testKey);
        assertSame(alice.getKey(),testKey);
    }@Test
    public void getsetKeyAES() {
        Alice alice = new Alice();
        Key testKey = new AESKey();
        alice.setKey(testKey);
        assertSame(alice.getKey(),testKey);
    }


}