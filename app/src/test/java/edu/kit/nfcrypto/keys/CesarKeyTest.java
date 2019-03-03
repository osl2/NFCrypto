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
        String testString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals("Der Text wird nicht richtig encrypted",string,testKey.encrypt(testString));
    }

    @Test
    public void encryptNULL() {
        CesarKey testKey = new CesarKey("0");
        String testString = "";
        String string = "";
        assertEquals("Der Text wird nicht richtig encrypted",string,testKey.encrypt(testString));
    }

    @Test
    public void encryptZ() {
        CesarKey testKey = new CesarKey("25");
        String testString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String string = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
        assertEquals("Der Text wird nicht richtig encrypted",string,testKey.encrypt(testString));
    }

    @Test
    public void encryptSonderzeichen() {
        CesarKey testKey = new CesarKey("9");
        String testString = "! ? , : . ; ( ) ";
        String string = "! ? , : . ; ( ) ";
        assertEquals("Der Text wird nicht richtig encrypted",string,testKey.encrypt(testString));
    }
//##############################################################################################
    //Hier wird decrypt getestet
    //##############################################################################################

    @Test
    public void decrypt() {
        CesarKey testKey = new CesarKey("0");
        String testString = "ABCDE";
        String string = "ABCDE";
        assertEquals("Der Text wird nicht richtig decrypted",string,testKey.decrypt(testString));
    }

    @Test
    public void decryptZ() {
        CesarKey testKey = new CesarKey("1");
        String testString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String string = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
        assertEquals("Der Text wird nicht richtig decrypted",string,testKey.decrypt(testString));
    }


    @Test
    public void decryptB() {
        CesarKey testKey = new CesarKey("25");
        String testString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String string = "BCDEFGHIJKLMNOPQRSTUVWXYZA";
        assertEquals("Der Text wird nicht richtig decrypted",string,testKey.decrypt(testString));
    }

    @Test
    public void decryptNULL() {
        CesarKey testKey = new CesarKey("1");
        String testString = "";
        String string = "";
        assertEquals("Der Text wird nicht richtig decrypted",string,testKey.decrypt(testString));
    }

    @Test
    public void decryptSonderzeichen() {
        CesarKey testKey = new CesarKey("9");
        String testString = "! ? , : . ; ( ) ";
        String string = "! ? , : . ; ( ) ";
        assertEquals("Der Text wird nicht richtig encrypted",string,testKey.encrypt(testString));
    }
}