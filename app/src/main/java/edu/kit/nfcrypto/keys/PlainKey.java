package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.keys.Key;

public class PlainKey extends Key {
    String keyData = "";

    public PlainKey() {

    }

    @Override
    public String encrypt(String text) {
        return text;
    }

    @Override
    public String decrypt(String text) {
        return text;
    }

    @Override
    public String encodeKey() {
        return "KEY" + this.getMode().toString() + this.keyData;

    }

    /*@Override // Methode wird nicht benötigt, da statisch in Superklasse
    public static Key decodeKey(String keyString) {
        //TODO REGEX invers zu encode

        return null;
    }*/
}
