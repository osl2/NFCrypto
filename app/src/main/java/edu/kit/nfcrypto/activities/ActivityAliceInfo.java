package edu.kit.nfcrypto.activities;

import android.os.Bundle;

import edu.kit.nfcrypto.R;


public class ActivityAliceInfo extends ActivityBase {

    //Zeigt Infotexte an

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alice_info);

        // Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorAlice));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
