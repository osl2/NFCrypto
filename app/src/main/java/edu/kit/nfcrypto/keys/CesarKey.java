package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static edu.kit.nfcrypto.data.Mode.CES;

public class CesarKey extends Key {
    private int keyData;
    private String letterRegEx = "[A-Z]";

    public CesarKey(String keyDataString) throws  KeyFormatException{
        super(CES,keyDataString);
        try {
            keyData = Integer.valueOf(keyDataString);
        } catch (NumberFormatException e) {
            throw new KeyFormatException("Integer needed instead of " + keyDataString + ".");
        }
    }

    public CesarKey() {
        super(CES);
        keyData = (int) (Math.random() * 25 + 1);
    }

    public CesarKey(int keyData){
        this.keyData = keyData;
    }

    @Override
    public String encrypt(String text) {
        String encryptedMessage = "";
        char ch;

        for (int i = 0; i < text.length(); ++i) {
            ch = text.charAt(i);

            if(ch >= 'A' && ch <= 'Z'){
                ch = (char) (ch + keyData);
                if (ch > 'Z') {
                    ch = (char) (ch - 'Z' + 'A' - 1);
                }
            }
            encryptedMessage += ch;
        }
        return encryptedMessage;
    }

    @Override
    public String decrypt(String text) {

        String decryptedMessage = "";
        char ch;

        for (int i = 0; i < text.length(); ++i) {
            ch = text.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - keyData);

                if (ch < 'A') {
                    ch = (char) (ch + 'Z' - 'A' + 1);
                }
            }
            decryptedMessage += ch;
        }

        return decryptedMessage;
    }


}
