package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;

public abstract class Key {
    private Mode mode;


    public abstract String encrypt(String text);

    public abstract String decrypt(String text);

    /**
     * encode,decodeKey sind bijektiv
     *
     * nimmt sich selbst und
     * @return einen String, abhängig von Key, also fertig für NFC
     */
    public abstract String encodeKey();

    /**
     * nimmt den Key Daten String und
     * @return die Key Daten :O
     * Der Datentyp
     */
    public abstract Key decodeKey(String keyString);

    public Mode getMode() {
        return mode;
    }
}
