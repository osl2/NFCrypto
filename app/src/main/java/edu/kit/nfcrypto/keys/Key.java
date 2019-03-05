package edu.kit.nfcrypto.keys;

import java.io.Serializable;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.WrongIdentifierException;

public abstract class Key implements Serializable {
    private Mode mode;
    private String keyDataString;

    Key(Mode mode) {
        this.mode = mode;
    }

    Key(Mode mode, String keyDataString) {
        this.mode = mode;
        setKeyDataString(keyDataString);
    }

    //Key(){} //Wird dieser Konstruktor benötigt? Wenn nicht, löschen!

    /**
     *
     * @return den verschlüsselten Suffix
     */
    public String suffix(){
        return encrypt("ENTSCHLUESSELT");
    }

    void setKeyDataString(String keyDataString) {
        this.keyDataString = keyDataString;
    }

    /**
     *  Verschlüsstelt
     * @param text entsprechend der KeyData
     * @return und gibt diesen verschlüsselten Text zurück
     */
    public abstract String encrypt(String text);

    /**
     *  Entschlüsstelt
     * @param text entsprechend der KeyData
     * @return und gibt diesen entschlüsselten Text zurück
     */
    public abstract String decrypt(String text);

    /**
     * encode,decodeKey sind bijektiv
     *
     * nimmt sich selbst und
     * @return einen String, abhängig von Key, also fertig für NFC
     */
    public String encodeKey() {
        return "KEY" + mode.toString() + keyDataString;
    }


    public String getKeyDataString() {
        return keyDataString;
    }

    public Mode getMode() {
        return mode;
    }

    void setMode(Mode mode) {
        this.mode = mode;
    }
}
