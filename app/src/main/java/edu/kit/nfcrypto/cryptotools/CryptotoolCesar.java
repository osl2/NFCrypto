package edu.kit.nfcrypto.cryptotools;

import edu.kit.nfcrypto.keys.CesarKey;

public class CryptotoolCesar extends Cryptotool {
    int cesar;
    String text;

    public CryptotoolCesar(int cesar){
        this.cesar = cesar;

    }

    public CryptotoolCesar(){

    }

    public String decrypt(String text){
        CesarKey key = new CesarKey(cesar);
        return key.decrypt(text);

    }

    @Override //TODO: FIX THIS FAST!
    public String crack(String text, String help) {
        String decryptedMessage = "";
        String decryptedHelp = "";
        char ch;
        int key;

        //hier wird nur der help-String überprüft
        for (key = 0; key < 26; key++) {
            for (int i = 0; i < help.length(); i++) {
                ch = help.charAt(i);

                if (ch >= ('A') && ch <= ('Z')) {
                    ch = (char) (ch - key);
                    if (ch < 'A') {
                        ch = (char) (ch + 26);
                    }
                }
                decryptedHelp += ch;

            }

            //ich habe nirgends gefunden, wie der help String im Original heißt,
            //oder wo er angehängt wird (ich dachte in der Message Klasse?)
            //gehe aber davon aus, dass es wie im Entwurf vereinbart ist
            if (decryptedHelp.equals("ENTSCHLUESSELT")) {
                break;
            }
        }
            //hier wird mit dem oben ermittelten key der text entschlüsselt
            for (int i = 0; i < text.length(); i++) {
                ch = text.charAt(i);

                if (ch >= ('A') && ch <= ('Z')) {
                    //Achtung: richtiger Schlüssel ist key -1, weil am Ende der Schleife nochmal inkrementiert wurde
                    ch = (char) (ch - key - 1);
                    if (ch < 'A') {
                        ch = (char) (ch + 26);
                    }
                }
                decryptedMessage += ch;
            }
            return decryptedMessage;
    }
}
