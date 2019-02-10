package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.VigenereKey;

import static edu.kit.nfcrypto.data.Mode.VIG;


public class ActivityEncryptMinikeyDetails extends ActivityBase {

    private String inputtext; //Relevante Dinge werden zum zurückgeben an Activity Alice zwischengespeichert
    private String minikey;
    private Mode spinner = VIG;
    private Key key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_details_minikey);

        //Wir von ActivityAlice zum zwischenspeichern mit dem Intent übergeben
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

        // Feld zur Eingabe eines eigenen Minikey-Schlüssels
        final EditText inputKey = findViewById(R.id.activity_encrypt_details_minikey_inputKey);

        //"Anwenden" Knopf vgl. ActivityCryptotoolsCesar
        final Button applyButton = findViewById(R.id.activity_encrypt_details_minikey_button);
        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                minikey = inputKey.getText().toString();
                if (minikey != null) {
                    key = new VigenereKey(minikey);
                }
                Intent i = new Intent(ActivityEncryptMinikeyDetails.this, ActivityAlice.class);
                i.putExtra("inputtext", inputtext);
                i.putExtra("key", key);
                i.putExtra("spinner", spinner);
                startActivity(i);
            }
        });

        final TextView generatedKey = findViewById(R.id.activity_encrypt_details_minikey_generatedKey);

        //letzten Schlüssel verwenden
        final Button buttonLastKey = findViewById(R.id.activity_encrypt_details_minikey_lastKey);
        buttonLastKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Key lastKey = User.getInstance().getLastKey();
                if (lastKey != null) {
                    if (lastKey.getMode() == VIG) {
                        key = lastKey;
                        minikey = key.getKeyDataString();
                        setTextView(minikey);

                    } else {
                        Toast.makeText(getApplicationContext(), "Der letzte Schlüssel war kein Minikeyschlüssel!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void setTextView(String text) {
        final TextView textView = findViewById(R.id.activity_encrypt_details_minikey_generatedKey);
        textView.setText(text);

    }
}
