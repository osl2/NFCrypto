package edu.kit.nfcrypto.data;


public class Message {

    private String plaintext;
    private String encryptedText;
    private Mode mode;


    public Message(String plaintext, String encryptedText, Mode mode) {
        this.plaintext = plaintext;
        this.encryptedText = encryptedText;
        this.mode = mode;
    }


    public Message(String encryptedText) {
        this.encryptedText = encryptedText;
    }


    public String getEncryptedText() {
        return encryptedText;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    public Mode getMode() {
        return mode;
    }
}
