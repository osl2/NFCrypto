package edu.kit.nfcrypto.cryptotools;

import edu.kit.nfcrypto.keys.VigenereKey;

public class CryptotoolMinikey extends Cryptotool {
    private String text;

    @Override
    public String crack(String text, String help) {
        String decryptedMessage = "";

        //sorry, Lösung ist ziemlich schrottig, aber so gibt#s wenigstens schon mal ne Notlösung :D
        char[] key = new char[6];
        char ch;
        boolean b0 = false, b1 = false, b2 = false , b3 = false , b4 = false, b5 = false;

        for (int i = 0; i < 26; i++) {
            if(help.charAt(8) == 'E') key[0] = (char) (i + 65); b0 = true;
            if(help.charAt(9) == 'S') key[1] = (char) (i + 65); b1 = true;
            if(help.charAt(10) == 'S') key[2] = (char) (i + 65); b2 = true;
            if(help.charAt(11) == 'E') key[3] = (char) (i + 65); b3 = true;
            if(help.charAt(12) == 'L') key[4] = (char) (i + 65); b4 = true;
            if(help.charAt(13) == 'T') key[5] = (char) (i + 65); b5 = true;

            if (b0 && b1 && b2 && b3 && b4 && b5) break;
        }


        //hier wird mit dem oben ermittelten key der text entschlüsselt
        for (int i = 0; i < text.length(); i++) {
            ch = text.charAt(i);
            if (ch >= ('A') && ch <= ('Z')) {
                ch = (char) (ch - key[i % 6]);
                if (ch < 'A') {
                    ch = (char) (ch + 26);
                }
            }
            decryptedMessage += ch;
        }
        return decryptedMessage;
    }


}
