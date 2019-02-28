package edu.kit.nfcrypto;

import java.io.Serializable;

import edu.kit.nfcrypto.activities.ActivityAlice;
import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;

public class Alice implements Serializable {
    private Message currentMessage = null;
    private Key key;


    /**
     * Stellt alle Daten für das Preview in der GUI bereit
     *
     * @param text     der Eingelesen wird
     * @param mode     wie Cesar etc.
     * @param activity Activity aus der die Funktionaufgerufen wurde
     */
    public void alicePreview(String text, Mode mode, ActivityAlice activity) {
        if (key == null) {
            switch (mode) {
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
        }
        User.getInstance().setLastKey(key);
        currentMessage = new Message(text, key.encrypt(text), mode);

        activity.setTextView(currentMessage.getEncryptedText()); //Sollte den TextView in AliceActivity umsetzen
    }


    public Message getCurrentMessage() {
        return currentMessage;
    }


    public Key getKey() {
        return key;
    }


    public void setKey(Key key) {
        this.key = key;
    }
}
