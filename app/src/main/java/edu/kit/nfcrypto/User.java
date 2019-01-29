package edu.kit.nfcrypto;

import edu.kit.nfcrypto.exceptions.InputFormatException;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.data.Message;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;


public final class User {
    //Speichert die Daten im Hintergrund für alle Activities
    private Key lastKey;
    private static boolean[] permission; //0 PLA,
    private static User instance;

    private User(){}

    public static User getInstance() {
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public static void setPermission(boolean[] permission) {
        permission = permission;
    }

    public static boolean[] getPermission() {
        return permission;
    }
}
