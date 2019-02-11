package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;

import static edu.kit.nfcrypto.data.Mode.AES;
import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;
import static edu.kit.nfcrypto.data.Mode.VIG;

public class ActivityCode extends ActivityBase { //Activity in der dann die Modi freigeschaltet werden können.
    private String codeString;  //Speichert den input
    private String codeCes;  //Speichert das Passwort für CES, VIG, AES
    private String codeVig;
    private String codeAES;
    private Class origin; // Speichert die Activity, von der diese Activity aufgerufen wurde


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        codeCes = this.getResources().getString(R.string.pwces);
        codeVig = this.getResources().getString(R.string.pwvig);
        codeAES = this.getResources().getString(R.string.pwaes);

        if (getIntent().getStringExtra("origin").equals("alice")) {
            origin = ActivityAlice.class;
        } else if (getIntent().getStringExtra("origin").equals("eve")) {
            origin = ActivityEve.class;
        }
        final EditText codeInput = findViewById(R.id.activity_code_edittext);
        codeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                codeString = codeInput.getText().toString(); //Speichert die Codeeingabe

            }
        });

        //"anwenden" knopf
        final Button button = findViewById(R.id.activity_code_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String unlocked = "";
                Mode mode = PLA;
                boolean unlock = false;

                //Es wird überprüft, ob ein Code stimmt
                if (codeString != null) {
                    if (codeString.equals(codeCes)) {
                        mode = CES;
                        unlocked = "Cesar";
                        unlock = true;
                    } else if (codeString.equals(codeVig)) {
                        mode = VIG;
                        unlocked = "Minikey";
                        unlock = true;
                    } else if (codeString.equals(codeAES)) {
                        mode = AES;
                        unlocked = "AES";
                        unlock = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Der Code ist leider falsch", Toast.LENGTH_LONG).show();
                    }

                    //Bei Freischaltung
                    if (mode != PLA & User.getInstance().addPermission(mode)) {
                        Toast.makeText(getApplicationContext(), unlocked + " erfolgreich freigeschaltet", Toast.LENGTH_LONG).show();

                        //Kehre bei Freischaltung auf vorherige Activity zurück.
                        ActivityCode.this.startActivity(new Intent(ActivityCode.this, origin));
                    } else {
                        if(unlock) {
                            Toast.makeText(getApplicationContext(), unlocked + " das wurde bereits freigeschaltet", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
        });
    }
}
