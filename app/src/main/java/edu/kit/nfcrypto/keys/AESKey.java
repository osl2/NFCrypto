package edu.kit.nfcrypto.keys;

import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESKey extends Key {
    private byte[] keyData;
    private SecretKeySpec secretKey;

    public AESKey(String keyDataString){
        //keyDataString in byte[] umwandeln
        keyData = keyDataString.getBytes();

        //falls Länge (128 Bit) nicht stimmt, anpassen
        if(keyData.length != 16){
            //TODO Fehlermeldung? Weil nicht 128 Bit = 16 Byte
            keyData = Arrays.copyOf(keyData,16);
        }

        //SecretKey aus keyData erzeugen
        secretKey = new SecretKeySpec(keyData, "AES");

    }

    //TODO Exceptionhandling
    @Override
    public String encrypt(String plainText) throws Exception {
        // Chiffre erstellen und initialisieren
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // nur "AES" ist aufgrund des Verfahrens unsicher
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Text in byte[] umwandeln und verschlüsseln
        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        // Verschlüsselte Nachricht als String zurückgeben
        return new String(encrypted);
    }

    //TODO Exceptionhandling
    @Override
    public String decrypt(String encryptedText) throws Exception {
        // Chiffre erstellen und initialisieren
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // encryptedText in byte[] umwandeln und verschlüsseln
        byte[] cipherData = cipher.doFinal(encryptedText.getBytes());

        // Entschlüsselte Nachricht als String zurückgeben
        return new String(cipherData);
    }

    @Override
    public String encodeKey() {
        return "KEY" + this.getMode().toString() + new String(keyData);
    }

}
