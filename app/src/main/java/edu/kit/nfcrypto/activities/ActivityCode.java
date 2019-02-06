package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class ActivityCode extends ActivityBase {
    String codeString;
    String codeCes;
    String codeVig;
    String codeAES;
    Class origin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        codeCes = this.getResources().getString(R.string.pwces);
        codeVig = this.getResources().getString(R.string.pwvig);
        codeAES = this.getResources().getString(R.string.pwaes);

        if (getIntent().getStringExtra("origin").equals("alice")) {
             origin = ActivityAlice.class;
        } else if(getIntent().getStringExtra("origin").equals("eve")){
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

                codeString = codeInput.getText().toString();

            }
        });
        final Button button = findViewById(R.id.activity_code_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String unlocked = "";
                Mode mode = PLA;
                if (codeString != null) {
                    if (codeString.equals(codeCes)) {
                        mode = CES;
                        unlocked = "Cesar";
                    } else if (codeString.equals(codeVig)) {
                        mode = VIG;
                        unlocked = "Minikey";
                    } else if (codeString.equals(codeAES)) {
                        mode = AES;
                        unlocked = "AES";
                    } else {
                        Toast.makeText(getApplicationContext(), "Der Code ist leider falsch", Toast.LENGTH_LONG).show();
                    }
                    if (mode != PLA & User.getInstance().addPermission(mode)) {
                        Toast.makeText(getApplicationContext(), unlocked + " erfolgreich freigeschaltet", Toast.LENGTH_LONG).show();
                        ActivityCode.this.startActivity(new Intent(ActivityCode.this, origin));
                    } else {
                        Toast.makeText(getApplicationContext(), unlocked + " ist bereits freigeschaltet", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
