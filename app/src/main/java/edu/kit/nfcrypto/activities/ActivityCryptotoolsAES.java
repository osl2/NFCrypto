package edu.kit.nfcrypto.activities;

import android.os.Bundle;

public class ActivityCryptotoolsAES extends ActivityBase{
    int spinner =3; //TODO muss wieder zurück geschmissen werden^^

    protected void onCreate(Bundle savedInstanceState) {
        String inputtext = getIntent().getStringExtra("inputtext");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptotools_aes);


    }
}
