package edu.kit.nfcrypto;

import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;

public final class User {
    private Key lastKey;
    private Mode[] permission;


    //Preview Funktionen

    /**
     *
     * @param text der Eingelesen wird
     * @param mode wie Cesar etc.
     * @param cesarDetails wenn Cesardetails != 0, sonst 0
     */
    public void alicePreview(String text, Mode mode, int cesarDetails){
        Key key = null;
        switch (mode){
            case PLA:
                key = new PlainKey();
                break;
            case CES:
                key = new CesarKey();
                break;
            case VIG:
                key = new VigenereKey();
                break;
            case AES:
                key = new AESKey();
                break;
        }
        Message currentMessage = new Message(text, key.encrypt(text), mode);
        //Setze den Textview auf den encrypted Text
    }


    public void bob(){
        //Muss dann den Nachrichtentag Inhalt anzeigen
        //Außerdem Key, Text abspeichern
    }

    public void eve(){
        //Muss dann den Nachrichten Inhalt anzeigen, abspeichern
    }




}
