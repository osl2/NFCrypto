package edu.kit.nfcrypto.exceptions;

public class KeyFormatException extends IllegalArgumentException {
    public static final String KEY_DATA = "KeyData not valid.";
    public KeyFormatException(String message){
        super(message);
    }
}
