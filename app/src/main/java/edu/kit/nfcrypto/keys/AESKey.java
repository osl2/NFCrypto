package edu.kit.nfcrypto.keys;

import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESKey extends Key {
    private String keyDataString;
    private SecretKeySpec keyData;

    public AESKey() {
        //zufälliges ByteArray erzeugen
        byte[] keyDataByte = new byte[16];
        new Random().nextBytes(keyDataByte);
        //SecretKey aus keyData erzeugen
        keyData = new SecretKeySpec(keyDataByte, "AES");
        keyDataString = new String(keyDataByte);
    }


    //TODO Exceptionhandling
    @Override
    public String encrypt(String plainText) throws Exception {
        // Chiffre erstellen und initialisieren
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // nur "AES" ist aufgrund des Verfahrens unsicher
        cipher.init(Cipher.ENCRYPT_MODE, keyData);

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
        cipher.init(Cipher.DECRYPT_MODE, keyData);

        // encryptedText in byte[] umwandeln und verschlüsseln
        byte[] cipherData = cipher.doFinal(encryptedText.getBytes());

        // Entschlüsselte Nachricht als String zurückgeben
        return new String(cipherData);
    }

    public String getKeyDataString() {
        return keyDataString;
    }

}
