package edu.kit.nfcrypto.keys;

import android.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Parameterized.class)
@PowerMockIgnore({"javax.crypto.*" })
@PrepareForTest(Base64.class)
public class AESKeyTestSeries {
    private Key keyRandom;
    private Key keyRead;

    @Parameterized.Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"NUxBRHVMK1BKY0o0M0RkeA==",   "HALLO BOB",             "PTCAnR1QfpCcaIUxbEbI3A=="},
                {"Z0Q3N3A4SE0yUEVDTWVncA==",   "A",                     "I8Bji8/CXIRnv62leITKAw=="},
                {"Z0Q3N3A4SE0yUEVDTWVncA==",   "0123456789#!?:-);-)*",  "3pz3CvGzjWqRSqEq/uUm68eHTk6vCz05KPtgh8jinY0="}
        });
    }

    @Parameterized.Parameter
    public String keyData;

    @Parameterized.Parameter(1)
    public String plainText;

    @Parameterized.Parameter(2)
    public String encryptedText;

    @Before
    public void setUp() {
        //Mock
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

        //Init
        keyRandom = new AESKey();
        keyRead = new AESKey(keyData);
    }

    @Test
    public void encrypt() {
        String encrypted = keyRead.encrypt(plainText);
        assertEquals(encryptedText, encrypted);
    }

    @Test
    public void decrypt() {
        String decrypted = keyRead.decrypt(encryptedText);
        assertEquals(plainText,decrypted);
    }

    @Test
    public void randomKey() {
        assertEquals(plainText,keyRandom.decrypt(keyRandom.encrypt(plainText)));
    }
}