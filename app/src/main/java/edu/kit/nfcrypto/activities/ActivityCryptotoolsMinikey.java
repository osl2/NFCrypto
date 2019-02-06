package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActivityCryptotoolsMinikey extends ActivityBase{

    private String inputtext;
    private final int spinner=2;

    protected void onCreate(Bundle savedInstanceState) {
        inputtext = getIntent().getStringExtra("inputtext");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptotools_minikey);

        final Button applyButton = findViewById(R.id.activity_cryptotools_minikey_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityCryptotoolsMinikey.this, ActivityEve.class);
                i.putExtra("inputtext",inputtext);
                i.putExtra("spinner",spinner);
                startActivity(i);
            }
        });
    }
}
