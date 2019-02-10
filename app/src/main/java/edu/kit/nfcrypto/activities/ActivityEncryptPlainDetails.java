package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.data.Mode;


public class ActivityEncryptPlainDetails extends ActivityBase {

    private final Mode spinner = Mode.PLA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_details_plain);

        //Wir von ActivityAlice zum zwischenspeichern mit dem Intent übergeben
        //Relevante Dinge werden zum zurückgeben an Activity Alice zwischengespeichert
        String inputtext = getIntent().getStringExtra("inputtext");

        //Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorAlice));
        } catch (Exception e) {
            e.printStackTrace();
        }


        //"Anwenden" Knopf vgl. ActivityCryptotoolsCesar
        final Button applyButton = findViewById(R.id.activity_encrypt_details_plain_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityEncryptPlainDetails.this, ActivityAlice.class);
                i.putExtra("spinner",spinner);
                startActivity(i);
            }
        });

    }
}
