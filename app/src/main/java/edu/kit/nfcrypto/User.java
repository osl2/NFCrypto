package edu.kit.nfcrypto;

import android.content.Context;
import android.util.Pair;

import java.util.ArrayList;

import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.data.Mode;

import static edu.kit.nfcrypto.data.Mode.AES;
import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;
import static edu.kit.nfcrypto.data.Mode.VIG;


public final class User {
    private boolean[] permission; //0 PLA,
    private static User instance;

    private User() {
    }

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
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setPermission(boolean[] permissionInput) {
        permission = permissionInput;
    }

    public Pair<ArrayList<String>, ArrayList<Mode>> getPermissionArray(Context c) {
        ArrayList<String> outputString = new ArrayList<>();
        ArrayList<Mode> outputMode = new ArrayList<>();
        if (permission[0]) {
            outputString.add(c.getResources().getString(R.string.plaintext));
            outputMode.add(PLA);
        }
        if (permission[1]) {
            outputString.add(c.getResources().getString(R.string.cesar));
            outputMode.add(CES);
        }
        if (permission[2]) {
            outputString.add(c.getResources().getString(R.string.vigenere));
            outputMode.add(VIG);
        }
        if (permission[3]) {
            outputString.add(c.getResources().getString(R.string.aes));
            outputMode.add(AES);
        }
        if (outputString.size() < 4 ){
            outputString.add("Neues freischalten!");
        }
        return new Pair(outputString, outputMode);
    }

    public boolean addPermission(Mode mode){
        if(permission[mode.toInt()]){
            return false;
        }else {
            permission[mode.toInt()] = true;
            return true;
        }

    }

    public void setLastKey(Key lastKey) {
        //Speichert die Daten im Hintergrund für alle Activities
    }
}
