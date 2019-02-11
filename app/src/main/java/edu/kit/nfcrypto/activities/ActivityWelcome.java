package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        //Buttons für Weiter nur einblenden, wenn NFC angeschaltet ist.
        final CheckBox checkBoxNFCOn = findViewById(R.id.activity_welcome_checkBox_nfcOn);
        final LinearLayout linearLayout = findViewById(R.id.activity_welcome_buttonsGo);
        final TextView textUse = findViewById(R.id.activity_welcome_text_use);
        checkBoxNFCOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linearLayout.setVisibility(View.VISIBLE);
                    textUse.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.INVISIBLE);
                    textUse.setVisibility(View.INVISIBLE);
                }
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
