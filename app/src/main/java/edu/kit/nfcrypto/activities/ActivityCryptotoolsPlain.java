package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.kit.nfcrypto.R;


public class ActivityCryptotoolsPlain extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptotools_plain);

        //Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorEve));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
