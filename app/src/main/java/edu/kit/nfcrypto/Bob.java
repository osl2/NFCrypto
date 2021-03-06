package edu.kit.nfcrypto;

import edu.kit.nfcrypto.activities.ActivityBob;
import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.WrongKeyException;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;

public class Bob {
    private Message currentMessage = null;
    private Key key;

    /**
     * Stellt alle Daten für das Preview in der GUI bereit
     * @param text der Eingelesen wird
     * @param mode wie Cesar etc.
     */
    public void bobPreview(String text, Mode mode, String keyString, ActivityBob activity) throws WrongKeyException {
        switch (mode) {
            case PLA:

                key = new PlainKey();
                break;
            case CES:
                key = new CesarKey(keyString);
                break;
            case VIG:
                key = new VigenereKey(keyString);
                break;
            case AES:
                key = new AESKey(keyString);
                break;
        }

        currentMessage = new Message(key.decrypt(text), text, mode);

        activity.setTextViewDecrypted(currentMessage.getPlaintext()); //Sollte den TextView in BobActivity umsetzen
    }

}
