package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;

import static edu.kit.nfcrypto.data.Mode.AES;
import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;
import static edu.kit.nfcrypto.data.Mode.VIG;


public class ActivityWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final FloatingActionButton buttonGuided = findViewById(R.id.activity_welcome_button_guided);
        buttonGuided.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean[] perm = {true,false,false,false};
                User.setPermission(perm);
                ActivityWelcome.this.startActivity(new Intent(ActivityWelcome.this, ActivityPersons.class));
            }
        });
        final FloatingActionButton buttonUnguided = findViewById(R.id.Activity_welcome_button_unguided);
        buttonUnguided.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean[] perm = {true,true,true,true};
                User.setPermission(perm);
                ActivityWelcome.this.startActivity(new Intent(ActivityWelcome.this, ActivityPersons.class));
            }
        });
    }

}
