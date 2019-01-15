package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.KeyFormatException;

public abstract class Key {
    private Mode mode;


    public abstract String encrypt(String text) throws Exception;

    public abstract String decrypt(String text) throws Exception;

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

    public static Key decodeKey(String keyString) throws KeyFormatException
    {
        String[] splitKeyString = {keyString.substring(0,2), keyString.substring(3,5), keyString.substring(6)};
        if(!splitKeyString[0].equals("KEY")){
            //TODO Fehlermeldung? Weil kein Schlüssel?
        }
        switch(splitKeyString[1]) {
            case "PLA": return new PlainKey();
            case "CES": return new CesarKey(splitKeyString[2]);
            case "VIG": return new VigenereKey(splitKeyString[2]);
            case "AES": return new AESKey(splitKeyString[2]);
            default: return null; //TODO Fehlermeldung? Weil kein gültiger Modus?
        }
    }


    public Mode getMode() {
        return mode;
    }
}
