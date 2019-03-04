package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.CesarKey;
import edu.kit.nfcrypto.keys.Key;

import static edu.kit.nfcrypto.data.Mode.CES;


public class ActivityEncryptDetailsCesar extends ActivityBase {

    private String inputtext; //Relevante Dinge werden zum zurückgeben an Activity Alice zwischengespeichert
    private Key key;
    private int cesar = -1;
    private final Mode spinner = Mode.CES;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_details_cesar);

        //Wir von ActivityAlice zum zwischenspeichern & ggf. anzeigen mit dem Intent übergeben
        inputtext = getIntent().getStringExtra("inputtext");
        if (getIntent().getSerializableExtra("key") != null) {

            key = (Key) getIntent().getSerializableExtra("key");
        }

        //Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorAlice));
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Dropdownmenü vgl. ActivityCryptotoolsCesar
        final Spinner detailsSpinner = findViewById(R.id.activity_encrypt_details_cesar_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cesar_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        detailsSpinner.setAdapter(adapter);
        detailsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cesar = position-1;

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
                if (cesar != -1) {
                    key = new CesarKey("" + cesar);
                }
                Intent i = new Intent(ActivityEncryptDetailsCesar.this, ActivityAlice.class);
                i.putExtra("inputtext", inputtext);
                i.putExtra("key", key);
                i.putExtra("spinner", spinner);
                startActivity(i);
                ActivityEncryptDetailsCesar.this.finish();
            }
        });

        // Zufälligen Schlüssel erzeugen
        final TextView gerneratedKey = findViewById(R.id.activity_encrypt_details_cesar_generatedKey);
        if (cesar != -1) {
            gerneratedKey.setText((char) (cesar + 65));
        }

        //letzten Schlüssel verwenden
        final Button buttonLastKey = findViewById(R.id.activity_encrypt_details_cesar_lastKey);
        buttonLastKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Key lastKey = User.getInstance().getLastKey();
                if (lastKey != null) {
                    if (lastKey.getMode() == CES) {
                        key = lastKey;
                        cesar = ((CesarKey) key).getKeyData();
                        setTextView(""+(char) (cesar + 65));
                    } else {
                        Toast.makeText(getApplicationContext(), "Der letzte Schlüssel war kein Cäsarschlüssel!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Du hast keinen letzten Schlüssel. Erstelle einen neuen.", Toast.LENGTH_LONG).show();
                }
            }
        });




    }
    private void setTextView(String text) {
        final TextView textView = findViewById(R.id.activity_encrypt_details_cesar_generatedKey);
        textView.setText(text);

    }
}
