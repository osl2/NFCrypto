package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.kit.nfcrypto.R;

public class ActivityCryptotoolsAES extends ActivityBase{
    String inputtext;
    int spinner =3; //TODO muss wieder zurück geschmissen werden^^

    protected void onCreate(Bundle savedInstanceState) {
        inputtext = getIntent().getStringExtra("inputtext");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptotools_aes);


    }
}
