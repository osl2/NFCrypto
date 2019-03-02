package edu.kit.nfcrypto.keys;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VigenereKeyTest {
    Key key;
    ArrayList<String[]> testStrings = new ArrayList<String[]>();

    @Before
    public void readStrings() {
        addString("HALLO WELT","HBNOS WFNW");
        addString("HALLO!!","HBNOS!!");
    }
    private void addString(String string, String encoded) {
        testStrings.add(new String[]{string,encoded});
    }

    @Before
    public void before() {
        String keyData = "ABCDEF";
        key = new VigenereKey(keyData);
    }


    @Test
    public void encrypt() {
        for (String[] s : testStrings) {
            assertEquals(key.encrypt(s[0]),s[1]);
        }
    }


    @Test
    public void decrypt() {
        for (String[] s : testStrings) {
            assertEquals(key.decrypt(s[1]),s[0]);
        }
    }

    @Test
    public void getKeylength() {
        assertEquals(VigenereKey.getKeylength(),6);
    }
}