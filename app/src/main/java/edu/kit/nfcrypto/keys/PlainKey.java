package edu.kit.nfcrypto.keys;

import static edu.kit.nfcrypto.data.Mode.PLA;

public class PlainKey extends Key {

    public PlainKey() {
        super(PLA,"");
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

}
