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

    public static String[] splitInput(String input) {
        String[] output = new String[4];
        output[0] = input.substring(0, 3); //KEY oder MES
        output[1] = input.substring(3, 6); //TYP
        if (output[0].equals("MES")) {
            output[2] = input.substring(6, input.length() - 14);
            output[3] = input.substring(input.length() - 14);
        } else if (output[0].equals("KEY")) {
            output[2] = input.substring(6);
        }

        return output;

    }

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
