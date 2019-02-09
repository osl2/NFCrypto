package edu.kit.nfcrypto.cryptotools;

import edu.kit.nfcrypto.keys.VigenereKey;

public class CryptotoolMinikey extends Cryptotool {

    @Override
    public String crack(String text, String help) {

        char[] helpArray = help.toCharArray();
        char[] referenceArray = "ENTSCHLUESSELT".toCharArray();
        char[] keyArray = new char[VigenereKey.getKeylength()];

        for (int j = 0; j < keyArray.length; j++) {
            keyArray[j] = (char) ((((helpArray[j] + 26) - referenceArray[j]) % 26)+'A');
        }

        VigenereKey key = new VigenereKey(String.valueOf(keyArray));
        return key.decrypt(text);


    }


}
