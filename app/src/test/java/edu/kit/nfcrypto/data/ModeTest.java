package edu.kit.nfcrypto.data;

import android.util.Base64;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;


import edu.kit.nfcrypto.exceptions.KeyFormatException;
import edu.kit.nfcrypto.exceptions.WrongIdentifierException;


import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Base64.class)
@PowerMockIgnore({"javax.crypto.*" })
public class ModeTest {

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
    //Hier werden die Exceptions und Sonderfälle getestet
    //##############################################################################################

    @Test(expected= WrongIdentifierException.class)
    public void createKeyUnknown() {
        String keyData = "";
        Key key = Mode.createKey("unknown", keyData);
    }

    @Test(expected= KeyFormatException.class)
    public void createKeyCaesarNull() {
        String keyData = null;
        Key key = Mode.createKey("CES", keyData);
    }

    @Test(expected= KeyFormatException.class)
    public void createKeyVigenereNull() {
        String keyData = null;
        Key key = Mode.createKey("VIG", keyData);
    }

    @Test(expected = KeyFormatException.class)
    public void createKeyAESNull() {
        Mode.createKey("AES", null);
    }

    @Test
    public void toModeNull() {
        assertNull(Mode.toMode("TEST"));
    }
}