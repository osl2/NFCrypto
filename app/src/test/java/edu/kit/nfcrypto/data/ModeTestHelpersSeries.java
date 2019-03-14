package edu.kit.nfcrypto.data;

import android.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.Arrays;
import java.util.Collection;

import edu.kit.nfcrypto.exceptions.KeyFormatException;
import edu.kit.nfcrypto.exceptions.WrongIdentifierException;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Parameterized.class)
@PowerMockIgnore({"javax.crypto.*" })
@PrepareForTest(Base64.class)
public class ModeTestHelpersSeries {

    private Mode mode;

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

        switch (modeString) {
            case "AES":
                mode = Mode.AES;
                break;
            case "PLA":
                mode = Mode.PLA;
                break;
            case "VIG":
                mode = Mode.VIG;
                break;
            case "CES":
                mode = Mode.CES;
                break;
            default:
                mode = Mode.PLA;
                break;
        }

    }

    @Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"PLA",     "0"},
                {"CES",     "1"},
                {"VIG",     "2"},
                {"AES",     "3"}
        });
    }

    @Parameter
    public String modeString;

    @Parameter(1)
    public String modeIntString;

    @Test
    public void toInt() {
        assertEquals(mode.toInt(),Integer.parseInt(modeIntString));
    }

    @Test
    public void toMode() {
        assertEquals(mode, Mode.toMode(modeString));
    }
}