package edu.kit.nfcrypto.keys;

import java.io.Serializable;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.WrongIdentifierException;

public abstract class Key implements Serializable {
    public Mode mode;
    private String keyDataString;

    public Key(Mode mode) {
        this.mode = mode;
    }

    public Key(Mode mode, String keyDataString) {
        this.mode = mode;
        setKeyDataString(keyDataString);
    }

    public Key(){};

    /**
     *
     * @return den verschlüsselten Suffix
     */
    public String suffix(){
        return encrypt("ENTSCHLUESSELT");
    }

    protected void setKeyDataString(String keyDataString) {
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
    };


    /**
     * nimmt den Key Daten String und
     * @return die Key Daten :O
     * Der Datentyp
     */

    public static Key decodeKey(String keyString)
    {
        String[] splitKeyString = {keyString.substring(0,2), keyString.substring(3,5), keyString.substring(6)};
        if(!splitKeyString[0].equals("KEY")){
            throw new WrongIdentifierException("Identifier 'KEY' needed instead of " + splitKeyString[0] + ".");
        }
        return Mode.createKey(splitKeyString[1], splitKeyString[2]);
    }

    public String getKeyDataString() {
        return keyDataString;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
