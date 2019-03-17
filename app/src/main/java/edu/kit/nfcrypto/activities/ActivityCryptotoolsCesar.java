package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.keys.CesarKey;


public class ActivityCryptotoolsCesar extends ActivityBase {
    private String inputtext;
    //Speichert die relevanten Variablen zwiscehn um sie an Eve zurüchzugeben.
    private String help;
    private int cesar = -1;
    private final int spinner = 1; //Spinner muss auf CES gesetzt werden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptotools_cesar);

        //Setzt die mit dem Intent übergebenen Variablen
        inputtext = getIntent().getStringExtra("inputtext");
        help = getIntent().getStringExtra("help");

        //Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorEve));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Dropdownmenü für das Alphabet
        final Spinner detailsSpinner = findViewById(R.id.activity_cryptotools_caesar_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cesar_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        detailsSpinner.setAdapter(adapter);
        detailsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cesar = position - 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//"Anwenden" Knopf
        final Button bruteforceButton = findViewById(R.id.activity_cryptotools_caesar_buttonbf);
        bruteforceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //Alles relevante wird an ActivityAlice zurück gegeben und ActivityEve starten.
                    Intent i = new Intent(ActivityCryptotoolsCesar.this, ActivityEve.class);
                    i.putExtra("inputtext", inputtext);
                    i.putExtra("spinner", spinner);
                    i.putExtra("help", help);
                    startActivity(i);

            }
        });
        //"Anwenden" Knopf
        final Button applyButton = findViewById(R.id.activity_cryptotools_caesar_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cesar != -1) {
                    //Alles relevante wird an ActivityAlice zurück gegeben und ActivityEve starten.
                    Intent i = new Intent(ActivityCryptotoolsCesar.this, ActivityEve.class);
                    i.putExtra("inputtext", inputtext);
                    i.putExtra("key", new CesarKey("" + cesar));
                    i.putExtra("spinner", spinner);
                    i.putExtra("help", help);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Dafür musst du erst ein Buchstaben auswählen!",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
