package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static edu.kit.nfcrypto.data.Mode.CES;

public class CesarKey extends Key {
    private int keyData;
    private Mode mode = CES;
    private String letterRegEx = "[A-Z]";

    public CesarKey(String keyDataString) throws  KeyFormatException{
        try {
            keyData = Integer.valueOf(keyDataString);
        } catch (NumberFormatException e) {
            throw new KeyFormatException();
        }
    }

    public CesarKey() {
        keyData = (int) (Math.random() * 25 + 1);

    }

    @Override
    public String encrypt(String text) {
        String encryptedMessage = "";
        char ch;
        String st;

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

    public String getKeyDataString() {
        return String.valueOf(keyData);
    }


}
