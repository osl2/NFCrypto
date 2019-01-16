package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static edu.kit.nfcrypto.data.Mode.VIG;


public class VigenereKey extends Key {
    private char keyData[];
    private Mode mode = VIG;

    public VigenereKey(String keyDataString) {
        try {
            keyData = keyDataString.toCharArray();
        } catch (NumberFormatException e) {
            throw new KeyFormatException();
        }
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

    public String getKeyDataString() {
        return String.valueOf(keyData);
    }


}
