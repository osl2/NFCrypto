package edu.kit.nfcrypto;

import java.io.Serializable;


public class Eve implements Serializable {
    public String[] splitInput(String input) {
        String[] output = new String[4];
        output[0] = input.substring(0, 3); //KEY oder MES
        output[1] = input.substring(3, 6); //TYP
        if (output[0].equals("MES")) {
            output[2] = input.substring(6, input.length() - 14);
            output[3] = input.substring(input.length() - 14);
        }

        return output;

    }
}
