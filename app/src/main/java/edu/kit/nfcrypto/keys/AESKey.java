package edu.kit.nfcrypto.keys;

import java.util.Arrays;
import java.util.Random;

import android.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import edu.kit.nfcrypto.exceptions.KeyFormatException;
import edu.kit.nfcrypto.exceptions.WrongKeyException;

import static edu.kit.nfcrypto.data.Mode.AES;

public class AESKey extends Key {
    private final SecretKeySpec keyData;
    private final byte[] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  //  final transient private IvParameterSpec ivspec = new IvParameterSpec(iv);//transient wird benötigt, da sonst Probleme mit Serializable von Key

    public AESKey() {
        super(AES);
        //zufälliges ByteArray erzeugen
        byte[] keyDataByte = new byte[16];
        new Random().nextBytes(keyDataByte);

        // Schlüssel als Base64 kodieren und Länge anpassen
        keyDataByte = Base64.encode(keyDataByte,Base64.DEFAULT);
        keyDataByte = Arrays.copyOf(keyDataByte,16);

        //SecretKey aus keyData erzeugen
        keyData = new SecretKeySpec(keyDataByte, "AES");
        setKeyDataString(Base64.encodeToString(keyDataByte, Base64.DEFAULT));
    }

    public AESKey(String keyDataString) {
        super(AES);
        // KeyDataString decodieren
        if(keyDataString == null) {
            throw new KeyFormatException("KeyData is null.");
        }
        byte[] keyDataByte = new byte[0];
        try {
            keyDataByte = Base64.decode(keyDataString, Base64.DEFAULT);
        } catch (IllegalArgumentException e) {
            throw new KeyFormatException("Faulty Key. (" + e.getMessage() + ")");
        }
        //SecretKey aus keyData erzeugen
        keyData = new SecretKeySpec(keyDataByte, "AES");
        setKeyDataString(Base64.encodeToString(keyDataByte, Base64.DEFAULT));
    }

    // Suffix soll nicht mitverschlüsselt werden.
    @Override
    public String suffix(){
        return "ENTSCHLUESSELT";
    }

    //TODO detailiertes Exceptionhandling?
    @Override
    public String encrypt(String plainText) {
        try {
            // Chiffre erstellen und initialisieren
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // nur "AES" ist aufgrund des Verfahrens unsicher
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keyData, ivspec);

            // Text in byte[] umwandeln und verschlüsseln
            byte[] encrypted = cipher.doFinal(plainText.getBytes());

            // Verschlüsselte Nachricht als String zurückgeben
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception e) {
            throw new KeyFormatException(KeyFormatException.KEY_DATA + " (" + e.getMessage() + ")");
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        try {
            // Chiffre erstellen und initialisieren
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keyData, ivspec);

            // encryptedText in byte[] umwandeln und entschlüsseln
            byte[] encryptedBytes = Base64.decode(encryptedText, Base64.DEFAULT);
            byte[] cipherData = cipher.doFinal(encryptedBytes);

            // Entschlüsselte Nachricht als String zurückgeben
            return new String (cipherData);
        } catch (BadPaddingException e) {
            throw new WrongKeyException(e.getMessage());
        } catch (Exception e) {
            throw new KeyFormatException(KeyFormatException.KEY_DATA + " (" + e.getMessage() + ")");
        }
    }

}
