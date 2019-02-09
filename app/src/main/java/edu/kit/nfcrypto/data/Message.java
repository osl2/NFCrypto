package edu.kit.nfcrypto.data;


import java.io.Serializable;

public class Message implements Serializable {

    private String plaintext;
    private String encryptedText;
    private Mode mode;


    public Message(String plaintext, String encryptedText, Mode mode) {
        this.plaintext = plaintext;
        this.encryptedText = encryptedText;
        this.mode = mode;
    }


    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    /**
     *
     * @return die Nachricht mit Präfix MES  und Modus
     */
    public String encodeEncryptedText() {
        return "MES" + mode.toString() + encryptedText;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public Mode getMode() {
        return mode;
    }
}
