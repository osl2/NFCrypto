package edu.kit.nfcrypto.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import edu.kit.nfcrypto.R;

public class ActivityNFCWrite extends ActivityBase {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcwrite);

        AlertDialog.Builder buildWriteMessage = new AlertDialog.Builder(this);
    }
}
