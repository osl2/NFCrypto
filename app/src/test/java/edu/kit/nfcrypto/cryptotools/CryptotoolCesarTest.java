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
        assertEquals("",cesar.decrypt(testString),decryptString );
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
}