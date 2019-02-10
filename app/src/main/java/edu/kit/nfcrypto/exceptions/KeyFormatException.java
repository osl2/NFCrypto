package edu.kit.nfcrypto.exceptions;

public class KeyFormatException extends IllegalArgumentException {
    //TODO
    private String message;
    public static final String KEY_DATA = "KeyData not valid.";
    public KeyFormatException(String message){
        super(message);
        this.message = message;
    }
}
