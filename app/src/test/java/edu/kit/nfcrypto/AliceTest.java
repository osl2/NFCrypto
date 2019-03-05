package edu.kit.nfcrypto;

import android.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Base64.class)
@PowerMockIgnore({"javax.crypto.*" })
public class AliceTest {
    @Before
    public void mock() {
        PowerMockito.mockStatic(Base64.class);
        when(Base64.encode(any(byte[].class), anyInt())).thenAnswer(
                new Answer<byte[]>() {
                    @Override
                    public byte[] answer(final InvocationOnMock invocation) {
                        return java.util.Base64.getEncoder().encode((byte[]) invocation.getArguments()[0]);
                    }
                }
        );
        when(Base64.decode(anyString(), anyInt())).thenAnswer(
                new Answer<byte[]>() {
                    @Override
                    public byte[] answer(final InvocationOnMock invocation) {
                        return java.util.Base64.getMimeDecoder().decode((String) invocation.getArguments()[0]);
                    }
                }
        );
        when(Base64.encodeToString(any(byte[].class), anyInt())).thenAnswer(
                new Answer<String>() {
                    @Override
                    public String answer(final InvocationOnMock invocation) {
                        return java.util.Base64.getMimeEncoder().encodeToString((byte[]) invocation.getArguments()[0]);
                    }
                }
        );
    }

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