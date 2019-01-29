package edu.kit.nfcrypto.keys;

import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static edu.kit.nfcrypto.data.Mode.VIG;
import static edu.kit.nfcrypto.data.Mode.createKey;
import static java.lang.Math.floor;


public class VigenereKey extends Key {
    private char keyData[];
    private int keylength = 14;

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
        keyData = null;


    }

    @Override
    public String encrypt(String text) {
        char msg[] = text.toCharArray();
        int msgLen = msg.length, i;
        char encryptedMsg[] = new char[msgLen];
        if (keyData == null){ keyData = createKey(msgLen);}

        //encryption
        for (i = 0; i < msgLen; ++i)
            encryptedMsg[i] = (char) (((msg[i] + keyData[i%keylength]) % 26) + 'A');

        return String.valueOf(encryptedMsg);
    }

    @Override
    public String decrypt(String text) {

        if (keyData != null) {
            char encryptedMsg[] = text.toCharArray();
            int msgLen = encryptedMsg.length, i, j;
            char decryptedMsg[] = new char[msgLen];

            //decryption
            for (i = 0; i < msgLen; ++i)
                decryptedMsg[i] = (char) ((((encryptedMsg[i] - keyData[i%keylength]) + 26) % 26) + 'A');


            return String.valueOf(decryptedMsg);
        }

        return null; //TODO Exception, dass noch KeyKarte gebrauch wird
    }

    private char[] createKey(int msgLength){
        char newKey[] = new char[keylength];

        for (int i = 0; i < keylength;i++){
            newKey[i] =(char) (floor(Math.random()*26)+'A');
        }
        setKeyDataString(newKey.toString());
        return newKey;
    }
}

