package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;



public class ActivityWelcome extends AppCompatActivity {
    private boolean[] perm = {false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Knopf für im Unterricht
        final FloatingActionButton buttonGuided = findViewById(R.id.activity_welcome_button_guided);
        buttonGuided.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //standard Permission für im Unterricht
                boolean[] permPre = {true,false,false,false};
                setPerm(permPre);
                User.getInstance().setPermission(perm);
                ActivityWelcome.this.startActivity(new Intent(ActivityWelcome.this, ActivityPersons.class));
            }
        });


        //Knopf für Alleine
        final FloatingActionButton buttonUnguided = findViewById(R.id.Activity_welcome_button_unguided);
        buttonUnguided.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //standard Permission für alleine
                boolean[] permPre = {true,true,true,true};
                setPerm(permPre);
                User.getInstance().setPermission(perm);
                ActivityWelcome.this.startActivity(new Intent(ActivityWelcome.this, ActivityPersons.class));
            }
        });
    }

    /**
     * Setzt die Permission als
     * @param perm von außerhalb
     */

    private void setPerm(boolean[] perm) {
        this.perm = perm;
    }
}
