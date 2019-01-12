package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.exceptions.KeyFormatException;

public class CesarKey extends Key {
    int keyData;

<<<<<<< HEAD
    public CesarKey(String keyDataString){
        //TODO Konstruktor
=======
    public CesarKey(int keyData) {
        this.keyData = keyData;
    }

    public CesarKey() {
        keyData = (int) (Math.random() * 25 + 1);
>>>>>>> general changes
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
        return "KEYCES" + String.valueOf(keyData);
    }

<<<<<<< HEAD
    /*@Override
    public String decodeKey() {
        return null;
    }*/
=======
    public int decodeKey(String keyText) {
        String präfix = keyText.substring(0, 6);
        if (präfix.equals("MESCES")) {
            String suffix = keyText.substring(6);
            try {
                keyData = Integer.valueOf(suffix);
                return keyData;
            } catch (NumberFormatException e) {
                throw new KeyFormatException();
            }
        } else {
            throw new KeyFormatException();
        }
    }
>>>>>>> general changes
}
