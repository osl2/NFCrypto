package edu.kit.nfcrypto;

import edu.kit.nfcrypto.activities.ActivityAlice;
import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;

public class Alice {
    Message currentMessage = null;

    /**
     *
     * @param text der Eingelesen wird
     * @param mode wie Cesar etc.
     * @param cesarDetails wenn Cesardetails != 0, sonst 0
     */
    public void alicePreview(String text, Mode mode, int cesarDetails, ActivityAlice activity){
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
        currentMessage = new Message(text, key.encrypt(text), mode);

        activity.setTextView(key.encrypt(text)); //Sollte den TextView in AliceActivity umsetzen
    }


    public Message getCurrentMessage() {
        return currentMessage;
    }
}
