package edu.kit.nfcrypto.data;

import java.io.Serializable;

import edu.kit.nfcrypto.exceptions.WrongIdentifierException;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.PlainKey;
import edu.kit.nfcrypto.keys.VigenereKey;

/**
 * PLA: Plain
 * CES: Cesar
 * VIG: Vigenere aka Minikey
 * AES: trivial
 */
public enum Mode implements Serializable {
    PLA, CES, VIG, AES;

    public static Key createKey(String modeString, String keyString) {
        switch(modeString) {
            case "PLA": return new PlainKey();
            case "CES": return new CesarKey(keyString);
            case "VIG": return new VigenereKey(keyString);
            case "AES": return new AESKey(keyString);
            default: throw new WrongIdentifierException("Identifier " + modeString + " unknown.");
        }
    }
}
