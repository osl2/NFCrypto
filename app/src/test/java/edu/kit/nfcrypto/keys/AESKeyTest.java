package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.util.Base64;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Base64.class)
public class AESKeyTest {
    Key key;

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

        key = new AESKey();
    }

    @Test
    public void suffix() {
        assertSame(key.suffix(), "ENTSCHLUSSELT");
    }

    @Test
    public void encrypt() {
    }

    @Test
    public void decrypt() {
    }
}