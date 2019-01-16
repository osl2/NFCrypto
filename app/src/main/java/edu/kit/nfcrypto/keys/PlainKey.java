package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.Key;

import static edu.kit.nfcrypto.data.Mode.PLA;

public class PlainKey extends Key {
    String keyData = "";
    Mode mode = PLA;

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
        return "";

    }

    public String getKeyDataString() {
        return keyData;
    }

}
