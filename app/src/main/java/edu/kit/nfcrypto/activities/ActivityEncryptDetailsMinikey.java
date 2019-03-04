package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.*;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.keys.VigenereKey;

import static edu.kit.nfcrypto.data.Mode.VIG;


public class ActivityEncryptDetailsMinikey extends ActivityBase {

    private String inputtext; //Relevante Dinge werden zum zurückgeben an Activity Alice zwischengespeichert
    private String minikey;
    private final Mode spinner = VIG;
    private Key key;
    private final String FORBIDDEN_CHARS = "[^A-Z]";


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

        //InputFilter um nur bestimmte Buchstaben zuzulassen
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                String sourceString = source.toString();
                sourceString = sourceString.toUpperCase();
                sourceString = sourceString.replaceAll(FORBIDDEN_CHARS, "");
                return sourceString;
            }
        };
        inputKey.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(6)});

        //"Anwenden" Knopf vgl. ActivityCryptotoolsCesar
        final Button applyButton = findViewById(R.id.activity_encrypt_details_minikey_button);
        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                minikey = inputKey.getText().toString();
                if (minikey != null & !minikey.equals("")) {
                    key = new VigenereKey(minikey);
                } else {
                    key = new VigenereKey();
                }
                Intent i = new Intent(ActivityEncryptDetailsMinikey.this, ActivityAlice.class);
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
                } else {
                    Toast.makeText(getApplicationContext(), "Du hast keinen letzten Schlüssel. Erstelle einen neuen.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setTextView(String text) {
        final TextView textView = findViewById(R.id.activity_encrypt_details_minikey_generatedKey);
        textView.setText(text);

    }
}
