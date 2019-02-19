package edu.kit.nfcrypto.data;

import org.junit.Test;

import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;

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
    }

    @Test
    public void toInt() {
    }

    @Test
    public void toMode() {
    }
}