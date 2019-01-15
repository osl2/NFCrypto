package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static edu.kit.nfcrypto.data.Mode.CES;

public class CesarKey extends Key {
    private int keyData;
    private Mode mode = CES;

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
        char ch;
        String encryptedMessage = "";

        for (int i = 0; i < text.length(); ++i) {
            ch = text.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch + keyData);

                if (ch > 'z') {
                    ch = (char) (ch - 'z' + 'a' - 1);
                }

                encryptedMessage += ch;
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + keyData);

                if (ch > 'Z') {
                    ch = (char) (ch - 'Z' + 'A' - 1);
                }

                encryptedMessage += ch;
            } else {
                encryptedMessage += ch;
            }
        }

        return encryptedMessage;
    }

    @Override
    public String decrypt(String text) {

        String decryptedMessage = "";
        char ch;

        for (int i = 0; i < text.length(); ++i) {
            ch = text.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - keyData);

                if (ch < 'a') {
                    ch = (char) (ch + 'z' - 'a' + 1);
                }

                decryptedMessage += ch;
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - keyData);

                if (ch < 'A') {
                    ch = (char) (ch + 'Z' - 'A' + 1);
                }

                decryptedMessage += ch;
            } else {
                decryptedMessage += ch;
            }
        }

        return decryptedMessage;
    }

    @Override
    public String encodeKey() {
        return "KEY"+ mode.toString() + String.valueOf(keyData);
    }


}
