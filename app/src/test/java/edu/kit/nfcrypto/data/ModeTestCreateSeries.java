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
public class ModeTestCreateSeries {

    private Key key;
    private Key controlKey;
    private Class keyClass;

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
                byte[] keyDataByte = keyData.getBytes();
                keyDataByte = Arrays.copyOf(keyDataByte,16);
                keyDataByte = Base64.encode(keyDataByte,Base64.DEFAULT);
                keyData = Base64.encodeToString(keyDataByte, Base64.DEFAULT);
                keyClass = AESKey.class;
                controlKey = new AESKey(keyData);
                break;
            case "PLA":
                keyClass = PlainKey.class;
                controlKey = new PlainKey();
                break;
            case "VIG":
                keyClass = VigenereKey.class;
                controlKey = new VigenereKey(keyData);
                break;
            case "CES":
                keyClass = CesarKey.class;
                controlKey = new CesarKey(keyData);
                break;
            default:
                keyClass = PlainKey.class;
                controlKey = new PlainKey();
                break;
        }

    }

    @Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"PLA",     ""},
                {"CES",     "1"},
                {"CES",     "2"},
                {"CES",     "3"},
                {"CES",     "4"},
                {"CES",     "5"},
                {"CES",     "6"},
                {"CES",     "7"},
                {"CES",     "8"},
                {"CES",     "9"},
                {"CES",     "10"},
                {"CES",     "11"},
                {"CES",     "12"},
                {"CES",     "13"},
                {"CES",     "14"},
                {"CES",     "15"},
                {"CES",     "16"},
                {"CES",     "17"},
                {"CES",     "18"},
                {"CES",     "19"},
                {"CES",     "20"},
                {"CES",     "21"},
                {"CES",     "22"},
                {"CES",     "23"},
                {"CES",     "24"},
                {"CES",     "25"},
                {"CES",     "26"},
                {"CES",     ""},
                {"VIG",     "AAAAAA"},
                {"VIG",     "ABCDEF"},
                {"VIG",     "ZZZZZZ"},
                {"AES",     "AAAAAAAAAAAAAAAAAAAAAAAAAAAAA"}
        });
    }

    @Parameter
    public String modeString;

    @Parameter(1)
    public String keyData;

    @Test
    public void createKey() {
        key = Mode.createKey(modeString,keyData);
        assertTrue(keyClass.isInstance(key));
        assertEquals(controlKey.getKeyDataString(), key.getKeyDataString());
        assertEquals(controlKey.getMode(), key.getMode());
        assertEquals(controlKey.getClass(), key.getClass());
    }
}