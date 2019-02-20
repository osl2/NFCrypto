package edu.kit.nfcrypto.data;

import org.junit.Test;

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
    /*
        Hier wird ein Fall getestet
     */
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
    public void createKeyCaesarNull() {
        String keyData = "";
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
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarK() {
        String keyData = "11";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
}

    @Test
    public void createKeyCaesarL() {
        String keyData = "12";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarM() {
        String keyData = "13";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarN() {
        String keyData = "14";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarO() {
        String keyData = "15";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarP() {
        String keyData = "16";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarQ() {
        String keyData = "17";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarR() {
        String keyData = "18";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarS() {
        String keyData = "19";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarT() {
        String keyData = "20";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarU() {
        String keyData = "21";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarV() {
        String keyData = "22";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarW() {
        String keyData = "23";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarX() {
        String keyData = "24";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarY() {
        String keyData = "25";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    @Test
    public void createKeyCaesarZ() {
        String keyData = "26";
        Key ces = Mode.createKey("CES", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", ces instanceof CesarKey);
        Key cesKey = new CesarKey(keyData);
        assertSame(cesKey.getKeyDataString(), ces.getKeyDataString());
        assertSame(cesKey.getMode(),ces.getMode());
        assertSame(cesKey.getClass(),ces.getClass());
    }

    //TODO Mehr Fälle Testen Randfälle
    @Test
    public void createKeyCaesarAAAAAA() {
        String keyData = "AAAAAA";
        Key vig = Mode.createKey("VIG", keyData);
        assertTrue("Ist keine Instanz von CäsarKey", vig instanceof VigenereKey);
        Key vigKey = new VigenereKey(keyData);
        assertSame(vigKey.getKeyDataString(), vig.getKeyDataString());
        assertSame(vigKey.getMode(),vig.getMode());
        assertSame(vigKey.getClass(),vig.getClass());
    }

    //TODO Randfälle testen

    @Test
    public void createKeyAES() {

    }

    @Test
    public void toInt() {

    }

    @Test
    public void toMode() {
    }
}