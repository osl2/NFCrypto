package edu.kit.nfcrypto.activities;
import android.app.AlertDialog;
import android.os.Bundle;

import edu.kit.nfcrypto.R;

public class ActivityNFCRead extends ActivityBase{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcread);

        AlertDialog.Builder buildReadMessage = new AlertDialog.Builder(this);


    }
}
