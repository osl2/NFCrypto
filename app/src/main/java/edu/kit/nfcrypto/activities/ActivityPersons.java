package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

import edu.kit.nfcrypto.R;


public class ActivityPersons extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        final Button buttonAlice = findViewById(R.id.activity_persons_button_alice);
        buttonAlice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityPersons.this.startActivity(new Intent(ActivityPersons.this, ActivityAlice.class));
            }
        });
        final Button buttonBob = findViewById(R.id.activity_persons_button_bob);
        buttonBob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityPersons.this.startActivity(new Intent(ActivityPersons.this, ActivityBob.class));
            }
        });
        final Button buttonEve = findViewById(R.id.activity_persons_button_eve);
        buttonEve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityPersons.this.startActivity(new Intent(ActivityPersons.this, ActivityEve.class));
            }
        });
    }
}
