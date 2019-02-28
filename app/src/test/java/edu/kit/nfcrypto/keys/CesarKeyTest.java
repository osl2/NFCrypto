package edu.kit.nfcrypto.keys;

import org.junit.Test;

import static org.junit.Assert.*;

public class CesarKeyTest {
//##############################################################################################
    //Hier wird encrypt getestet
    //##############################################################################################

    @Test
    public void encrypt() {
        CesarKey testKey = new CesarKey("0");
        String testString = "ABCDE";
        String string = "ABCDE";
        assertEquals("Der Text wird nicht richtig encrypted",testKey.encrypt(testString),string);
    }
//##############################################################################################
    //Hier wird decrypt getestet
    //##############################################################################################

    @Test
    public void decrypt() {
        CesarKey testKey = new CesarKey("0");
        String testString = "ABCDE";
        String string = "ABCDE";
        assertEquals("Der Text wird nicht richtig encrypted",testKey.decrypt(testString),string);
    }

}