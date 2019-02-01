package edu.kit.nfcrypto.cryptotools;

public class CryptotoolCesar extends Cryptotool {
    int cesar;
    String text;

    public CryptotoolCesar(int cesar){
        this.cesar = cesar;

    }

    @Override
    public String crack(String text, String help) {
        return null;
    }
}
