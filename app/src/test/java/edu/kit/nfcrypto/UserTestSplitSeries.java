package edu.kit.nfcrypto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UserTestSplitSeries {
    private static final String SUFFIX = "ENTSCHLUESSELT";

    @Parameters
    public static Collection<String[]> data(){
        return Arrays.asList(new String[][] {
                {"MESPLATESTENTSCHLUESSELT",    "MES",  "PLA",  "TEST",         SUFFIX},
                {"KEYPLA",                      "KEY",  "PLA",  "",             null},
                {"MESCESTESTENTSCHLUESSELT",    "MES",  "CES",  "TEST",         SUFFIX},
                {"KEYCESA",                     "KEY",  "CES",  "A",            null},
                {"MESVIGTESTENTSCHLUESSELT",    "MES",  "VIG",  "TEST",         SUFFIX},
                {"KEYVIGAAAAAA",                "KEY",  "VIG",  "AAAAAA",       null},
                {"MESAESTESTENTSCHLUESSELT",    "MES",  "AES",  "TEST",         SUFFIX},
                {"KEYAESSCHLUESSEL",            "KEY",  "AES",  "SCHLUESSEL",   null}
        });
    }

    @Parameter
    public String input;

    @Parameter(1)
    public String outputType;

    @Parameter(2)
    public String outputMode;

    @Parameter(3)
    public String outputMessage;

    @Parameter(4)
    public String outputSuffix;

    @Test
    public void splitInput() {
        String[] output = User.splitInput(input);
        assertEquals(outputType,    output[0]);
        assertEquals(outputMode,    output[1]);
        assertEquals(outputMessage, output[2]);
        if (outputSuffix != null) {
            assertEquals(outputSuffix, output[3]);
        }
    }
}