package edu.kit.nfcrypto.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import edu.kit.nfcrypto.R;


public class ActivityBob extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bob);

        final FloatingActionButton buttonInfo = findViewById(R.id.activity_bob_button_info);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityBob.this.startActivity(new Intent(ActivityBob.this, ActivityInfo.class));
            }
        });
    }
}
