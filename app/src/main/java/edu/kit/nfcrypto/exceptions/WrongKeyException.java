package edu.kit.nfcrypto.exceptions;

public class WrongKeyException extends KeyFormatException {
    private static final String WRONG_KEY = "Wrong key! ";
    public WrongKeyException(String message){
        super(WRONG_KEY + message);
    }
}
