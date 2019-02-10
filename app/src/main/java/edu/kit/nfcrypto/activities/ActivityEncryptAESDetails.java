package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.kit.nfcrypto.R;


public class ActivityEncryptAESDetails extends ActivityBase {

    private String inputtext; //Relevante Dinge werden zum zurückgeben an Activity Alice zwischengespeichert
    private String aes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_details);

        //Wir von ActivityAlice zum zwischenspeichern mit dem Intent übergeben
        inputtext = getIntent().getStringExtra("inputtext");

        //Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorAlice));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //"Anwenden" Knopf vgl. ActivityCryptotoolsCesar
        final Button applyButton = findViewById(R.id.activity_encrypt_details_aes_button);
        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityEncryptAESDetails.this, ActivityAlice.class);
                i.putExtra("inputtext",inputtext);
                i.putExtra("aes",aes);
                startActivity(i);
            }
        });

        // Zufälligen Schlüssel erzeugen
        final TextView generatedKey = findViewById(R.id.activity_encrypt_details_aes_generatedKey);
        final Button buttonRandomKey = findViewById(R.id.activity_encrypt_details_aes_randomKey);
        buttonRandomKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO zufälligen Schlüssel generieren (statt automatisch in ActivityAlice) und in generatedKey ausgeben
            }
        });

    }
}
