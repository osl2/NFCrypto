package edu.kit.nfcrypto.data;

import android.util.Base64;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;


import edu.kit.nfcrypto.exceptions.KeyFormatException;
import edu.kit.nfcrypto.exceptions.WrongIdentifierException;


import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;

import static org.junit.Assert.*;


/*
Wenn ihr für eine neue Klasse Tests anlegen wollt klickt auf den Klassennamen  mit alt-enter
Dann auf create Tests und unten könnt ihr hacken setzen für jede Methode die ihr ggf. Testen wollt
dann erstellt sich eine Datei WIE DIESE ganz von alleine ;)
auf den grünen Pfeil neben dem Test könnt ihr den einzelnen Test laufen lassen
@Test vor der Methode zeigt an das danach eine Testmethode kommt
Rechtsklick auf den Testordner dann auf "Run tests in "" " um alle Tests zu starten
 */
public class ModeTest {

    //##############################################################################################
    //Hier wird PlainKey getestet
    //##############################################################################################

    @Test // Testet einen Methoden Anwendungsfall
    public void createKeyPlain() { //Denn Test wenn möglich spezifisch auf denn Test benennen
        Key pla = Mode.createKey("PLA", ""); //Die zu Testende Methode nur einmal im Test ausführen
        //JUNIT-assert verwenden, so dass die Failenden Tests Aussagekräftige Fehlermeldungen schmeißen
        assertTrue(/*Nachricht wird bei fehlschlag angezeigt*/"Hey, das ist jetzt keine Instanz von PlainKey", /*Überprüfung */pla instanceof PlainKey);
        Key plaKey = new PlainKey();
        assertSame(plaKey.getKeyDataString(), pla.getKeyDataString());
        assertSame(plaKey.getMode(), pla.getMode());
        assertSame(plaKey.getClass(), pla.getClass());
    }


    //##############################################################################################
    //Hier werden die Cäsarschlüssel getestet
    //##############################################################################################

    @Test
    public void createKeyCaesarA() {
        String keyData = "1";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarB() {
        String keyData = "2";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarC() {
        String keyData = "3";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarD() {
        String keyData = "4";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarE() {
        String keyData = "5";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarF() {
        String keyData = "6";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarG() {
        String keyData = "7";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarH() {
        String keyData = "8";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarI() {
        String keyData = "9";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarJ() {
        String keyData = "10";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarK() {
        String keyData = "11";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarL() {
        String keyData = "12";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarM() {
        String keyData = "13";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarN() {
        String keyData = "14";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarO() {
        String keyData = "15";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarP() {
        String keyData = "16";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarQ() {
        String keyData = "17";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarR() {
        String keyData = "18";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarS() {
        String keyData = "19";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarT() {
        String keyData = "20";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarU() {
        String keyData = "21";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarV() {
        String keyData = "22";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarW() {
        String keyData = "23";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarX() {
        String keyData = "24";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarY() {
        String keyData = "25";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarZ() {
        String keyData = "26";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }

    @Test
    public void createKeyCaesarNull() {
        String keyData = "";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(), ces.getMode());
        assertSame(cesKey.getClass(), ces.getClass());
    }



    //##############################################################################################
    //Hier werden die Vigenere-Schlüssel getestet
    //##############################################################################################

    @Test
    public void createKeyVigenereAAAAAA() {
        String keyData = "AAAAAA";
        Key vig = Mode.createKey("VIG", keyData);
        assertTrue("Ist keine Instanz von VigenereKey", vig instanceof VigenereKey);
        Key vigKey = new VigenereKey(keyData);
        assertSame(vigKey.getKeyDataString(), vig.getKeyDataString());
        assertSame(vigKey.getMode(), vig.getMode());
        assertSame(vigKey.getClass(), vig.getClass());
    }

    public void createKeyVigenereABCDEF() {
        String keyData = "ABCDEF";
        Key vig = Mode.createKey("VIG", keyData);
        assertTrue("Ist keine Instanz von VigenereKey", vig instanceof VigenereKey);
        Key vigKey = new VigenereKey(keyData);
        assertSame(vigKey.getKeyDataString(), vig.getKeyDataString());
        assertSame(vigKey.getMode(),vig.getMode());
        assertSame(vigKey.getClass(),vig.getClass());
    }

    public void createKeyVigenereZZZZZZ() {
        String keyData = "ZZZZZZ";
        Key vig = Mode.createKey("VIG", keyData);
        assertTrue("Ist keine Instanz von VigenereKey", vig instanceof VigenereKey);
        Key vigKey = new VigenereKey(keyData);
        assertSame(vigKey.getKeyDataString(), vig.getKeyDataString());
        assertSame(vigKey.getMode(),vig.getMode());
        assertSame(vigKey.getClass(),vig.getClass());
    }



    //##############################################################################################
    //Hier werden die AES-Schlüssel getestet
    //##############################################################################################



    @Test
    @Ignore
    //Dieser Test benötigt android.util.Base64, was nicht für JUnit verfügbar ist -> nur mit Mocking
    public void createKeyAES() {
        String keyDataString = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        byte[] keyDataByte = keyDataString.getBytes();
        keyDataByte = Arrays.copyOf(keyDataByte,16);
        keyDataByte = Base64.encode(keyDataByte,Base64.DEFAULT);
        String keyData = Base64.encodeToString(keyDataByte, Base64.DEFAULT);

        Key aes = Mode.createKey("AES", keyData);
        assertTrue(aes instanceof AESKey);
        Key aesKey = new AESKey(keyData);
        assertSame(aesKey.getKeyDataString(), aes.getKeyDataString());
        assertSame(aesKey.getMode(), aes.getMode());
        assertSame(aesKey.getClass(), aes.getClass());
    }




    //##############################################################################################
    //Hier werden die Hilfsmethoden getestet
    //##############################################################################################

    @Test
    public void toIntPLA() {
        Mode mode = Mode.PLA;
        int test = mode.toInt();
        assertSame(0, test);
    }

    @Test
    public void toIntCES() {
        Mode mode = Mode.CES;
        int test = mode.toInt();
        assertSame(1, test);
    }

    @Test
    public void toIntVIG() {
        Mode mode = Mode.VIG;
        int test = mode.toInt();
        assertSame(2, test);
    }

    @Test
    public void toIntAES() {
        Mode mode = Mode.AES;
        int test = mode.toInt();
        assertSame(3, test);
    }

    @Test
    public void toModePLA() {
        assertSame(Mode.PLA, Mode.toMode("PLA"));
    }

    @Test
    public void toModeCES() {
        assertSame(Mode.CES, Mode.toMode("CES"));
    }

    @Test
    public void toModeVIG() {
        assertSame(Mode.VIG, Mode.toMode("VIG"));
    }

    @Test
    public void toModeAES() {
        assertSame(Mode.AES, Mode.toMode("AES"));
    }



    //##############################################################################################
    //Hier werden die Exceptions getestet
    //##############################################################################################


    @Test(expected= WrongIdentifierException.class)
    public void createKeyUnknown() {
        String keyData = "";
        Key key = Mode.createKey("unkown", keyData);
    }

    @Test(expected= KeyFormatException.class)
    public void createKeyCaesarEmpty() {
        String keyData = "";
        Key key = Mode.createKey("CES", keyData);
    }

    @Test(expected= NullPointerException.class)
    public void createKeyVigenereNull() {
        String keyData = null;
        Key key = Mode.createKey("VIG", keyData);
    }


    //##############################################################################################
    //Hier werden die Randfälle getestet
    //##############################################################################################


    //TODO Randfälle testen


}