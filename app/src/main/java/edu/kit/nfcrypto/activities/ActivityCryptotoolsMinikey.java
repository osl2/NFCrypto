package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.keys.CesarKey;


public class ActivityCryptotoolsMinikey extends ActivityBase {
    private String inputtext;
    //Speichert die relevanten Variablen zwiscehn um sie an Eve zurüchzugeben.
    private String help;
    private final int spinner = 2; //Spinner muss auf CES gesetzt werden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptotools_minikey);
        //Setzt die mit dem Intent übergebenen Variablen
        inputtext = getIntent().getStringExtra("inputtext");
        help = getIntent().getStringExtra("help");


        final Button bruteforceButton = findViewById(R.id.activity_cryptotools_minikey_button);
        bruteforceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Alles relevante wird an ActivityAlice zurück gegeben und ActivityEve starten.
                Intent i = new Intent(ActivityCryptotoolsMinikey.this, ActivityEve.class);
                i.putExtra("inputtext", inputtext);
                i.putExtra("spinner", spinner);
                i.putExtra("help", help);
                startActivity(i);

            }
        });

        //Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorEve));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
