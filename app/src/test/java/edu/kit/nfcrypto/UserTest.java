package edu.kit.nfcrypto;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    //##############################################################################################
    //Hier wird splitInput getestet
    //##############################################################################################

    @Test
    public void splitInputMESPLA() {
        String input = "MESPLATESTENTSCHLUESSELT";
        String[] output = User.splitInput(input);
        assertTrue("Das wird nicht als Nachricht erkannt.", output[0].equals("MES"));
        assertTrue("Das wird nicht als PlainKey erkannt.", output[1].equals("PLA"));
        assertTrue("Das ist nicht der korrekte Text.", output[2].equals("TEST"));
        assertTrue("Das wird nicht als entschlüsselt erkannt.", output[3].equals("ENTSCHLUESSELT"));
    }

    @Test
    public void splitInputKEYPLA() {
        String input = "KEYPLA";
        String[] output = User.splitInput(input);
        assertTrue("Das wird nicht als Nachricht erkannt.", output[0].equals("KEY"));
        assertTrue("Das wird nicht als PlainKey erkannt.", output[1].equals("PLA"));
        assertTrue("Das ist nicht der korrekte Text.", output[2].equals(""));
    }

    @Test
    public void splitInputMESCES() {
        String input = "MESCESTESTENTSCHLUESSELT";
        String[] output = User.splitInput(input);
        assertTrue("Das wird nicht als Nachricht erkannt.", output[0].equals("MES"));
        assertTrue("Das wird nicht als Cäsar erkannt.", output[1].equals("CES"));
        assertTrue("Das ist nicht der korrekte Text.", output[2].equals("TEST"));
        assertTrue("Das wird nicht als entschlüsselt erkannt.", output[3].equals("ENTSCHLUESSELT"));
    }

    @Test
    public void splitInputKEYCES() {
        String input = "KEYCESA";
        String[] output = User.splitInput(input);
        assertTrue("Das wird nicht als Nachricht erkannt.", output[0].equals("KEY"));
        assertTrue("Das wird nicht als Cäsar erkannt.", output[1].equals("CES"));
        assertTrue("Das ist nicht der korrekte Schlüssel.", output[2].equals("A"));
    }

    @Test
    public void splitInputMESVIG() {
        String input = "MESVIGTESTENTSCHLUESSELT";
        String[] output = User.splitInput(input);
        assertTrue("Das wird nicht als Nachricht erkannt.", output[0].equals("MES"));
        assertTrue("Das wird nicht als Vigenere erkannt.", output[1].equals("VIG"));
        assertTrue("Das ist nicht der korrekte Text.", output[2].equals("TEST"));
        assertTrue("Das wird nicht als entschlüsselt erkannt.", output[3].equals("ENTSCHLUESSELT"));
    }

    @Test
    public void splitInputKEYVIG() {
        String input = "KEYVIGAAAAAA";
        String[] output = User.splitInput(input);
        assertTrue("Das wird nicht als Schlüssel erkannt.", output[0].equals("KEY"));
        assertTrue("Das wird nicht als Vigenere erkannt.", output[1].equals("VIG"));
        assertTrue("Das ist nicht der korrekte Schlüssel.", output[2].equals("AAAAAA"));
    }

    @Test
    public void splitInputMESAES() {
        String input = "MESAESTESTENTSCHLUESSELT";
        String[] output = User.splitInput(input);
        assertTrue("Das wird nicht als Nachricht erkannt.", output[0].equals("MES"));
        assertTrue("Das wird nicht als AES erkannt.", output[1].equals("AES"));
        assertTrue("Das ist nicht der korrekte Text.", output[2].equals("TEST"));
        assertTrue("Das wird nicht als entschlüsselt erkannt.", output[3].equals("ENTSCHLUESSELT"));
    }

    @Test
    public void splitInputKEYAES() {
        String input = "KEYAESSCHLUESSEL";
        String[] output = User.splitInput(input);
        assertTrue("Das wird nicht als Schlüssel erkannt.", output[0].equals("KEY"));
        assertTrue("Das wird nicht als AES erkannt.", output[1].equals("AES"));
        assertTrue("Das ist nicht der korrekte Schlüssel.", output[2].equals("SCHLUESSEL"));
    }



    //##############################################################################################
    //Hier wird getInstance getestet
    //##############################################################################################

    @Test
    public void getInstance() {
    }


    //##############################################################################################
    //Hier wird setPermission getestet
    //##############################################################################################

    @Test
    public void setPermission() {
    }


    //##############################################################################################
    //Hier wird setPermission getestet
    //##############################################################################################

    @Test
    public void getPermissionArray() {
    }


    //##############################################################################################
    //Hier wird addPermission getestet
    //##############################################################################################

    @Test
    public void addPermission() {
    }


    //##############################################################################################
    //Hier wird setLastKey getestet
    //##############################################################################################

    @Test
    public void setLastKey() {
    }


    //##############################################################################################
    //Hier wird getLastKey getestet
    //##############################################################################################

    @Test
    public void getLastKey() {
    }
}