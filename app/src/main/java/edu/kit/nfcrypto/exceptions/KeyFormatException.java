package edu.kit.nfcrypto.exceptions;

public class KeyFormatException extends IllegalArgumentException {
    public static final String KEY_DATA = "KeyData not valid.";
    public static final String WRONG_KEY = "Wrong key.";
    public KeyFormatException(String message){
        super(message);
        //TODO
        String message1 = message;
    }
}
