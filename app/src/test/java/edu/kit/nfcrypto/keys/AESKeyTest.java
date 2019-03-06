package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.util.Base64;

import edu.kit.nfcrypto.exceptions.KeyFormatException;
import edu.kit.nfcrypto.exceptions.WrongKeyException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Base64.class)
@PowerMockIgnore({"javax.crypto.*" })
public class AESKeyTest {
    private final String TEST_STRING = "HALLO WELT";
    private final String TEST_STRING_64 = "ABC";
    private final String TEST_KEY_DATA = "NUxBRHVMK1BKY0o0M0RkeA==";

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

    @Test
    public void suffix() {
        Key key = new AESKey();
        assertEquals(14, key.suffix().length());
    }

    @Test
    public void encodeKey() {
        Key key = new AESKey(TEST_KEY_DATA);
        assertEquals("KEYAES" + TEST_KEY_DATA, key.encodeKey());
    }

    @Test(expected = WrongKeyException.class)
    public void wrongKeyError() {
        Key key1 = new AESKey();
        Key key2 = new AESKey();
        key1.decrypt(key2.encrypt(TEST_STRING));
    }

    @Test(expected = KeyFormatException.class)
    public void encryptError() {
        Key key = new AESKey(TEST_STRING_64);
        key.encrypt(TEST_STRING);
    }

    @Test(expected = KeyFormatException.class)
    public void decryptError() {
        Key key = new AESKey(TEST_STRING_64);
        key.decrypt(TEST_STRING);
    }

    @Test(expected = KeyFormatException.class)
    public void faultyKeyError() {
        new AESKey(TEST_STRING);
    }
}