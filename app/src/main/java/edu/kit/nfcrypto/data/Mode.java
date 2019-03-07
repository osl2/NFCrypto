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

    /**
     * Erstellt einen neuen Key mit
     * @param modeString als Modus
     * @param keyString als KeyString
     * @return den Schlüssel
     */
    public static Key createKey(String modeString, String keyString) {
        switch (modeString) {
            case "PLA":
                return new PlainKey();
            case "CES":
                return new CesarKey(keyString);
            case "VIG":
                return new VigenereKey(keyString);
            case "AES":
                return new AESKey(keyString);
            default:
                throw new WrongIdentifierException("Identifier " + modeString + " unknown.");
        }
    }

    /**
     * Modus zu
     * @return int für Spinner
     */
    public int toInt() {
        switch (this) {
            case PLA:
                return 0;

            case CES:
                return 1;

            case VIG:
                return 2;

            case AES:
                return 3;

            default:
                return -1;
        }
    }

    /**
     * String
     * @param string to
     * @return mode
     */

    public static Mode toMode(String string) {
        switch (string) {
            case "CES":
                return CES;
            case "PLA":
                return PLA;

            case "VIG":
                return VIG;

            case "AES":
                return AES;
        }
        return null;
    }
}
