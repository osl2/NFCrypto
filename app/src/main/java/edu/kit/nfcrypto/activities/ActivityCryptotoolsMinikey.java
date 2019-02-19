package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.kit.nfcrypto.R;


public class ActivityCryptotoolsMinikey extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptotools_minikey);

        //Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorEve));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //"Anwenden" Knopf
        //TODO stimmt das so? Ansonsten OnClickListener anpassen
        final Button applyButton = findViewById(R.id.activity_cryptotools_minikey_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Alles relevante wird an ActivityAlice zurück gegeben und ActivityEve starten.
                Intent i = new Intent(ActivityCryptotoolsMinikey.this, ActivityEve.class);
                startActivity(i);
            }
        });


    }
}
