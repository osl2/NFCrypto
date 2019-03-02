package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static edu.kit.nfcrypto.data.Mode.VIG;
import static java.lang.Math.floor;


public class VigenereKey extends Key {
    private char keyData[];
    private static final int keylength = 6;

    public VigenereKey(String keyDataString) {
        super(VIG, keyDataString);
        try {
            keyData = keyDataString.toCharArray();
        } catch (NumberFormatException e) {
            throw new KeyFormatException("CharArray needed instead of " + keyDataString + ".");
        }
    }

    public VigenereKey() {
        super(VIG);
        keyData = createKey();
        setKeyDataString(String.valueOf(keyData));
    }

    @Override
    public String encrypt(String text) {
        /*char msg[] = text.toCharArray();
        int msgLen = msg.length;
        char encryptedMsg[] = new char[msgLen];
        //encryption
        for (int i = 0; i < msgLen; ++i) {

            if (msg[i] == ' ') {
                encryptedMsg[i] = ' ';
            } else {
                encryptedMsg[i] = (char) (((msg[i] + keyData[i % keylength]) % 26) + 'A');
            }
        }
        return String.valueOf(encryptedMsg);*/

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

        if (keyData != null) {
            /*char encryptedMsg[] = text.toCharArray();
            int msgLen = encryptedMsg.length, i;
            char decryptedMsg[] = new char[msgLen];

            //decryption
            for (i = 0; i < msgLen; ++i) {
                if (encryptedMsg[i] == ' ') {
                    decryptedMsg[i] = ' ';
                } else {
                    decryptedMsg[i] = (char) ((((encryptedMsg[i] - keyData[i % keylength]) + 26) % 26) + 'A');
                }
            }

            return String.valueOf(decryptedMsg);*/
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

        return null; //TODO Exception, dass noch KeyKarte gebrauch wird
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

