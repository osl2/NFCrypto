package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.kit.nfcrypto.R;


public class ActivityEncryptMinikeyDetails extends ActivityBase {

    private String inputtext; //Relevante Dinge werden zum zurückgeben an Activity Alice zwischengespeichert
    private String minikey;


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

        // Feld zur Eingabe eines eigenen Minikey-Schlüssels
        final EditText inputKey = findViewById(R.id.activity_encrypt_details_minikey_inputKey);

        //"Anwenden" Knopf vgl. ActivityCryptotoolsCesar
        final Button applyButton = findViewById(R.id.activity_encrypt_details_minikey_button);
        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                minikey = inputKey.getText().toString();
                Intent i = new Intent(ActivityEncryptMinikeyDetails.this, ActivityAlice.class);
                i.putExtra("inputtext",inputtext);
                i.putExtra("minikey",minikey);
                startActivity(i);
            }
        });

        // Zufälligen Schlüssel erzeugen
        final TextView generatedKey = findViewById(R.id.activity_encrypt_details_minikey_generatedKey);
        final Button buttonRandomKey = findViewById(R.id.activity_encrypt_details_minikey_randomKey);
        buttonRandomKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO zufälligen Schlüssel generieren (statt automatisch in ActivityAlice) und in generatedKey ausgeben
            }
        });

    }
}
