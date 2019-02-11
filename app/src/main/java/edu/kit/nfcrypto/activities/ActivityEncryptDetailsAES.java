package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.AESKey;
import edu.kit.nfcrypto.keys.Key;

import static edu.kit.nfcrypto.data.Mode.AES;


public class ActivityEncryptDetailsAES extends ActivityBase {

    private String inputtext; //Relevante Dinge werden zum zurückgeben an Activity Alice zwischengespeichert
    private String aes;
    private final Mode spinner = AES;
    private Key key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_details_aes);

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

        //"Anwenden" Knopf vgl. ActivityCryptotoolsCesar
        final Button applyButton = findViewById(R.id.activity_encrypt_details_aes_button);
        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (aes != null) {
                    key = new AESKey(aes);
                }
                Intent i = new Intent(ActivityEncryptDetailsAES.this, ActivityAlice.class);
                i.putExtra("inputtext", inputtext);
                i.putExtra("key", key);
                i.putExtra("spinner", spinner);
                startActivity(i);
            }
        });

        final TextView generatedKey = findViewById(R.id.activity_encrypt_details_aes_generatedKey);

        //letzten Schlüssel verwenden
        final Button buttonLastKey = findViewById(R.id.activity_encrypt_details_aes_lastKey);
        buttonLastKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Key lastKey = User.getInstance().getLastKey();
                if (lastKey != null) {
                    if (lastKey.getMode() == AES) {
                        key = lastKey;
                        aes = key.getKeyDataString();
                        setTextView(aes);

                    } else {
                        Toast.makeText(getApplicationContext(), "Der letzte Schlüssel war kein AESschlüssel!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Du hast keinen letzten Schlüssel. Erstelle einen neuen.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void setTextView(String text) {
        final TextView textView = findViewById(R.id.activity_encrypt_details_aes_generatedKey);
        textView.setText(text);

    }
}
