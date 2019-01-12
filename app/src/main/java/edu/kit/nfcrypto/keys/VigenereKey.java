package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.exceptions.KeyFormatException;


public class VigenereKey extends Key {
    private char keyData[];

    public VigenereKey(String keyDataString) {
        //TODO Konstruktor
    }
    @Override
    public String encrypt(String text) {

        char msg[] = text.toCharArray();
        int msgLen = msg.length, i, j;

        char newKey[] = new char[msgLen];
        char encryptedMsg[] = new char[msgLen];

        //generate new key in cyclic manner equal to the length of original message
        for(i = 0, j = 0; i < msgLen; ++i, ++j){
            if(j == keyData.length)
                j = 0;

            newKey[i] = keyData[j];
        }

        //encryption
        for(i = 0; i < msgLen; ++i)
            encryptedMsg[i] = (char)(((msg[i] + newKey[i]) % 26) + 'A');

        return String.valueOf(encryptedMsg);
    }

    @Override
    public String decrypt(String text) {
        char encryptedMsg[] = text.toCharArray();
        int msgLen = encryptedMsg.length, i, j;
        char newKey[] = new char[msgLen];
        char decryptedMsg[] = new char[msgLen];

        //generate new key in cyclic manner equal to the length of original message
        for(i = 0, j = 0; i < msgLen; ++i, ++j){
            if(j == keyData.length)
                j = 0;

            newKey[i] = keyData[j];
        }

        //decryption
        for(i = 0; i < msgLen; ++i)
            decryptedMsg[i] = (char)((((encryptedMsg[i] - newKey[i]) + 26) % 26) + 'A');


        return String.valueOf(decryptedMsg);
    }

    @Override
    public String encodeKey() {
        return "MESVIG" + String.valueOf(keyData);
    }

<<<<<<< HEAD
    /*@Override
    public String decodeKey(String keyString) {
        return null;
    }*/
=======
    public char[] decodeKey(String keyText) {
        String präfix = keyText.substring(0, 6);
        if (präfix.equals("MESCES")) {
            String suffix = keyText.substring(6);
            try {
                keyData = suffix.toCharArray();
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
