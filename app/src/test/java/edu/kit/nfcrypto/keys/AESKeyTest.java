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

import javax.crypto.IllegalBlockSizeException;

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
    Key keyRandom;
    Key keyRead;
    String plainText;
    String encryptedText;

    @Before
    public void setUp() {
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


        keyRandom = new AESKey();
        keyRead = new AESKey("NUxBRHVMK1BKY0o0M0RkeA==");
        plainText = "HALLO BOB";
        encryptedText = "PTCAnR1QfpCcaIUxbEbI3A==";
    }

    @Test
    public void suffix() {
        assertSame(keyRandom.suffix(), "ENTSCHLUSSELT");
        assertSame(keyRead.suffix(),"ENTSCHLUSSELT");
    }

    @Test
    public void encrypt() {
        assertEquals(encryptedText, keyRead.encrypt(plainText));
    }

    @Test
    public void decrypt() {
        assertEquals(plainText, keyRead.decrypt(encryptedText));
    }

    @Test
    public void randomKey() {
        assertEquals(keyRandom.decrypt(keyRandom.encrypt(plainText)),plainText);
    }

    @Test(expected = WrongKeyException.class)
    public void wrongKeyError() {
        keyRandom.decrypt(encryptedText);
    }

    @Test(expected = KeyFormatException.class)
    public void encryptError() {
        Key key = new AESKey("ABC");
        key.encrypt(plainText);
    }

    @Test(expected = KeyFormatException.class)
    public void decryptError() {
        Key key = new AESKey("ABC");
        key.decrypt(plainText);
    }
}