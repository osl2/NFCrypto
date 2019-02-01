package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.kit.nfcrypto.R;


public class ActivityCryptotoolsCesar extends ActivityBase {
    String inputtext;
    int cesar;
    int spinner =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptotools_cesar);
        inputtext = getIntent().getStringExtra("inputtext");


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


        final Button applyButton = findViewById(R.id.activity_cryptotools_caesar_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityCryptotoolsCesar.this, ActivityEve.class);
                i.putExtra("inputtext",inputtext);
                i.putExtra("cesar",cesar);
                i.putExtra("spinner",spinner);
                startActivity(i);
            }
        });



    }
}
