package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;


import edu.kit.nfcrypto.R;

public class ActivityEve extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eve);

        final FloatingActionButton buttonCryptotools = findViewById(R.id.activity_eve_button_cryptotools);
        buttonCryptotools.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityEve.this.startActivity(new Intent(ActivityEve.this, ActivityCryptotoolsCesar.class));
            }
        });
    }

}
