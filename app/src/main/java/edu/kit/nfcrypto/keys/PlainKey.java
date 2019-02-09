package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Mode;
import static edu.kit.nfcrypto.data.Mode.PLA;

public class PlainKey extends Key {


    public PlainKey() {
        super(PLA,"");
        setKeyDataString("");
    }

    @Override
    public String encrypt(String text) {
        return text;
    }

    @Override
    public String decrypt(String text) {
        return text;
    }


}
