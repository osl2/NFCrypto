package edu.kit.nfcrypto.cryptotools;

import edu.kit.nfcrypto.keys.VigenereKey;

public class CryptotoolMinikey extends Cryptotool {
    private String text;
    private VigenereKey key;

    @Override
    public String crack(String text, String help) {

        char[] helpArray = help.toCharArray();
        char[] referenceArray = "ENTSCHLUESSELT".toCharArray();
        char[] keyArray = new char[VigenereKey.getKeylength()];

        //Sollte so gehen kann nicht Testen ohne das Key gefixt wurde

        for (int j = 0; j < keyArray.length; j++) {
            keyArray[j] = (char) ((((helpArray[j] + 26) - referenceArray[j]) % 26)+'A');
        }

        key = new VigenereKey(String.valueOf(keyArray));
        return key.decrypt(text);


    }


}
