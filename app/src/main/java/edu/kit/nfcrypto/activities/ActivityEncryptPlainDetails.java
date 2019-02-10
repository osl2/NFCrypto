package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.kit.nfcrypto.R;


public class ActivityEncryptPlainDetails extends ActivityBase {

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
        final Button applyButton = findViewById(R.id.activity_encrypt_details_plain_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityEncryptPlainDetails.this, ActivityAlice.class);
                startActivity(i);
            }
        });

    }
}
