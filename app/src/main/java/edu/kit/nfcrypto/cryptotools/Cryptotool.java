package edu.kit.nfcrypto.cryptotools;

public abstract class Cryptotool {

    /**
     * Methode zum Knacken/Bruteforcen der Verschlüsselung
     * @param text Der zu entschlüsselnde Text
     * @param help Der passende verschlüsselte "ENTSCHLUESSELT" String
     * @return den entschlüsselten Text
     */
    public abstract String crack(String text, String help);
}
