package edu.kit.nfcrypto.cryptotools;

import edu.kit.nfcrypto.keys.CesarKey;

public class CryptotoolCesar extends Cryptotool {
    int cesar;
    String text;

    public CryptotoolCesar(int cesar) {
        this.cesar = cesar;

    }

    public CryptotoolCesar() {

    }

    public String decrypt(String text) {
        CesarKey key = new CesarKey(cesar);
        return key.decrypt(text);

    }

    @Override
    public String crack(String text, String help) {
        char[] helpArray = help.toCharArray();
        char ch;
        int key;
        cesar = ((helpArray[0] + 65) - 'E') % 65;
        return decrypt(text);


    }
}
