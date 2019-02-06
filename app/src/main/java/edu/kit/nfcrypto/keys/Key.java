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
        this(mode);
        setKeyDataString(keyDataString);
    }

    Key(){}

    public String suffix(){
        return encrypt("ENTSCHLUESSELT");
    }

    void setKeyDataString(String keyDataString) {
        this.keyDataString = keyDataString;
    }


    public abstract String encrypt(String text);

    public abstract String decrypt(String text);

    /**
     * encode,decodeKey sind bijektiv
     *
     * nimmt sich selbst und
     * @return einen String, abhängig von Key, also fertig für NFC
     */
    public String encodeKey() {
        return "KEY" + getMode().toString() + keyDataString;
    }


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


    private Mode getMode() {
        return mode;
    }
}
