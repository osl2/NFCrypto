package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import edu.kit.nfcrypto.R;


public class ActivityEncryptCesarDetails extends ActivityBase {

    private String inputtext; //Relevante Dinge werden zum zurückgeben an Activity Alice zwischengespeichert
    private int cesar;


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


        //Dropdownmenü vgl. ActivityCryptotoolsCesar
        final Spinner detailsSpinner = findViewById(R.id.activity_encrypt_details_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cesar_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        detailsSpinner.setAdapter(adapter);
        detailsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               cesar = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cesar = -1;
            }
        });

        //"Anwenden" Knopf vgl. ActivityCryptotoolsCesar
        final Button applyButton = findViewById(R.id.activity_encrypt_details_cesar_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityEncryptCesarDetails.this, ActivityAlice.class);
                i.putExtra("inputtext",inputtext);
                i.putExtra("cesar",cesar);
                startActivity(i);
            }
        });

        // Zufälligen Schlüssel erzeugen
        final TextView gerneratedKey = findViewById(R.id.activity_encrypt_details_cesar_generatedKey);
        final Button buttonRandomKey = findViewById(R.id.actvity_encrypt_details_cesar_randomKey);
        buttonRandomKey.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO zufälligen Schlüssel erzeugen und in generatedKey anzeigen
            }
        });

        //letzten Schlüssel verwenden
        final Button buttonLastKey = findViewById(R.id.actvity_encrypt_details_cesar_lastKey);
        buttonRandomKey.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO letzten Schlüssel verwenden und in generatedKey anzeigen
            }
        });



    }
}
