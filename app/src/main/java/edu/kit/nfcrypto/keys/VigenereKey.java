package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.KeyFormatException;
import edu.kit.nfcrypto.exceptions.WrongKeyException;

import static edu.kit.nfcrypto.data.Mode.VIG;
import static java.lang.Math.floor;


public class VigenereKey extends Key {
    private char keyData[];
    private static final int keylength = 6;

    public VigenereKey(String keyDataString) {
        super(VIG, keyDataString);
        keyData = keyDataString.toCharArray();
        /*try {
            keyData = keyDataString.toCharArray();
        } catch (NumberFormatException e) {
            throw new KeyFormatException("CharArray needed instead of " + keyDataString + ".");
        }*/ //Wofür wird diese Exception benötigt? Umwandlung sollte doch hier sicher sein, oder?
    }

    public VigenereKey() {
        super(VIG);
        keyData = createKey();
        setKeyDataString(String.valueOf(keyData));
    }

    @Override
    public String encrypt(String text) {

        String encryptedMessage = "";
        char ch;

        for (int i = 0; i < text.length(); ++i) {
            ch = text.charAt(i);

            if(ch >= 'A' && ch <= 'Z'){
                ch = (char) (ch + (keyData[i % keylength] - 'A'));
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
                ch = (char) (ch - (keyData[i % keylength] - 'A'));

                if (ch < 'A') {
                    ch = (char) (ch + 'Z' - 'A' + 1);
                }
            }
            decryptedMessage += ch;
        }
        return decryptedMessage;

    }

    private char[] createKey() {
        char newKey[] = new char[keylength];

        for (int i = 0; i < keylength; i++) {
            newKey[i] = (char) (floor(Math.random() * 26) + 'A');
        }
        setKeyDataString(String.valueOf(newKey));
        return newKey;
    }

    public static int getKeylength() {
        return keylength;
    }
}

