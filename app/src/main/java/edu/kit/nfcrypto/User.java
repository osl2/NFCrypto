package edu.kit.nfcrypto;

import edu.kit.nfcrypto.exceptions.InputFormatException;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;


public final class User {
    //Speichert die Daten im Hintergrund für alle Activities
    private Key lastKey;
    private Mode[] permission;
    private static User instance;

    private User(){}

    public static User getInstance() {
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public static void checkMessage(String message) throws InputFormatException {
        //TODO soll den Eingabe Text checken ob alles okay ist, wenn nicht throw Exception
    }
    

    //Preview Funktionen


    //Auslagern in 3 Vers. Klassen



    public void bob(){
        //Muss dann den Nachrichtentag Inhalt anzeigen
        //Außerdem Key, Text abspeichern
    }

    public void eve(){
        //Muss dann den Nachrichten Inhalt anzeigen, abspeichern
    }




}
