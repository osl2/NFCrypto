package edu.kit.nfcrypto.data;

import java.io.Serializable;

public class Message implements Serializable {

    private String plaintext;
    private final String encryptedText;
    private final Mode mode;


    public Message(String plaintext, String encryptedText, Mode mode) {
        this.plaintext = plaintext;
        this.encryptedText = encryptedText;
        this.mode = mode;
    }


    public String getEncryptedText() {
        return encryptedText;
    }

    /**
     * @return die Nachricht mit Präfix MES  und Modus
     */
    public String encodeEncryptedText() {
        return "MES" + mode.toString() + encryptedText;
    }

    public String getPlaintext() {
        return plaintext;
    }

}
