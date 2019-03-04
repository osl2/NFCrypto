package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VigenereKeyTest {
    Key keyRead;
    Key keyRandom;
    String keyData = "";
    ArrayList<String[]> testStrings = new ArrayList<String[]>();

    private void readStrings() {
        keyData = "ABCDEZ";
        addString("HALLO WELT","HBNOS WFNW");
        addString("HALLO!!","HBNOS!!");
        addString("ZZZZZZZZZZ","ZABCDYZABC");
    }
    private void addString(String string, String encoded) {
        testStrings.add(new String[]{string,encoded});
    }

    @Before
    public void setUp() {
        readStrings();
        keyRead = new VigenereKey(keyData);
        keyRandom = new VigenereKey();
    }


    @Test
    public void encrypt() {
        for (String[] s : testStrings) {
            assertEquals(keyRead.encrypt(s[0]),s[1]);
        }
    }


    @Test
    public void decrypt() {
        for (String[] s : testStrings) {
            assertEquals(keyRead.decrypt(s[1]),s[0]);
        }
    }

    @Test
    public void getKeylength() {
        assertEquals(VigenereKey.getKeylength(),6);
    }

    @Test
    public void randomKey() {
        for (String[] s : testStrings) {
            assertEquals(keyRandom.decrypt(keyRandom.encrypt(s[0])),s[0]);
        }
    }
}