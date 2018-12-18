package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.keys.Key;

public class PlainKey extends Key {
    String keyData = "";

    public PlainKey() {

    }

    @Override
    public String encrypt(String text) {
        return null;
    }

    @Override
    public String decrypt(String text) {
        return null;
    }

    @Override
    public String encodeKey() {
        return "KEY" + this.getMode().toString() + this.keyData;

    }

    @Override
    public String decodeKey() {
        //TODO REGEX invers zu encode
        return null;
    }
}
