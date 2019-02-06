package edu.kit.nfcrypto.keys;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import edu.kit.nfcrypto.exceptions.KeyFormatException;

import static edu.kit.nfcrypto.data.Mode.AES;

public class AESKey extends Key {
    private final SecretKeySpec keyData;

    public AESKey() {
        super(AES);
        //zufälliges ByteArray erzeugen
        byte[] keyDataByte = new byte[16];
        new Random().nextBytes(keyDataByte);
        //SecretKey aus keyData erzeugen
        keyData = new SecretKeySpec(keyDataByte, "AES");
        setKeyDataString(new String(keyDataByte));
    }

    public AESKey(String keyDataString) {
        super(AES);
        byte[] keyDataByte = keyDataString.getBytes();
        //SecretKey aus keyData erzeugen
        keyData = new SecretKeySpec(keyDataByte, "AES");
    }


    //TODO detailiertes Exceptionhandling?
    @Override
    public String encrypt(String plainText) {
        try {
            // Chiffre erstellen und initialisieren
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // nur "AES" ist aufgrund des Verfahrens unsicher
            cipher.init(Cipher.ENCRYPT_MODE, keyData);

            // Text in byte[] umwandeln und verschlüsseln
            byte[] encrypted = cipher.doFinal(plainText.getBytes());

            // Verschlüsselte Nachricht als String zurückgeben
            return new String(encrypted);
        } catch (IllegalBlockSizeException e) {
            throw new KeyFormatException(e.getMessage());
        } catch (Exception e) {
            throw new KeyFormatException(KeyFormatException.KEY_DATA + " (" + e.getMessage() + ")");
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        try {
            // Chiffre erstellen und initialisieren
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keyData);

            // encryptedText in byte[] umwandeln und verschlüsseln
            byte[] cipherData = cipher.doFinal(encryptedText.getBytes());

            // Entschlüsselte Nachricht als String zurückgeben
            return new String(cipherData);
        } catch (Exception e) {
            throw new KeyFormatException(KeyFormatException.KEY_DATA + " (" + e.getMessage() + ")");
        }
    }

}
