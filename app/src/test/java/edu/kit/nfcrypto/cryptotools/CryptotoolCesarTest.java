package edu.kit.nfcrypto.cryptotools;

import org.junit.Test;

import static org.junit.Assert.*;

public class CryptotoolCesarTest {
    //##############################################################################################
    //Hier wird decrypt getestet
    //##############################################################################################

    @Test
    public void decrypt() {
        CryptotoolCesar cesar = new CryptotoolCesar(0);
        String testString = "Hallo Welt";
        String decryptString = "Hallo Welt";
        assertEquals("",decryptString ,cesar.decrypt(testString));
    }

    @Test
    public void decryptZ() {
        CryptotoolCesar cesar = new CryptotoolCesar(25);
        String testString = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
        String decryptString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals("",decryptString ,cesar.decrypt(testString));
    }

    @Test
    public void decryptNULL() {
        CryptotoolCesar cesar = new CryptotoolCesar(0);
        String testString = "";
        String decryptString = "";
        assertEquals("",decryptString ,cesar.decrypt(testString));
    }

    @Test
    public void decryptSonderzeichen() {
        CryptotoolCesar cesar = new CryptotoolCesar(0);
        String testString = "! ? () ; , : . ";
        String decryptString = "! ? () ; , : . ";
        assertEquals("",decryptString,cesar.decrypt(testString) );
    }


    //##############################################################################################
    //Hier wird crack getestet
    //##############################################################################################

    @Test
    public void crack() {
        CryptotoolCesar cesar = new CryptotoolCesar();
        String testString = "Hallo Welt";
        String crackedString = "Hallo Welt";
        String help = "Entschlüsselt";
        assertEquals("", crackedString,cesar.crack(testString,help) );

    }

    @Test
    public void crackNULL() {
        CryptotoolCesar cesar = new CryptotoolCesar();
        String testString = "";
        String crackedString = "";
        String help = "FOUTDIMVFTTFMU";
        assertEquals("", crackedString,cesar.crack(testString,help) );

    }

    @Test
    public void crackB(){
        CryptotoolCesar cesar = new CryptotoolCesar();
        String testString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String crackedString = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
        String help = "FOUTDIMVFTTFMU";
        assertEquals("", crackedString,cesar.crack(testString,help) );

    }

}