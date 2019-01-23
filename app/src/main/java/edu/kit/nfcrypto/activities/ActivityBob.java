package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.kit.nfcrypto.Bob;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.data.Mode;

import static edu.kit.nfcrypto.data.Mode.CES;


public class ActivityBob extends ActivityBase {
    Bob bob;
    String text = "TEST"; //TODO Testweise, muss von ReadNFC kommen
    Mode mode = CES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bob = new Bob();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bob);


        Button keyButton = findViewById(R.id.activity_bob_button_readKey);
        keyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityBob.this.startActivity(new Intent(ActivityBob.this, ActivityNFCRead.class)); //TODO der Intent muss mitnehmen ob MES oder KEY, würde bool nehmen
            }
        });
        Button messageButton = findViewById(R.id.activity_bob_button_readMessage);
        messageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityBob.this.startActivity(new Intent(ActivityBob.this, ActivityNFCRead.class));
            }
        });

        FloatingActionButton decryptButton = findViewById(R.id.activity_bob_button_decrypt);
        decryptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                transfer();
            }
        });

    }

    public void setTextViewInput(String inputNFCTag) {
        final TextView textView = findViewById(R.id.activity_bob_text_messagecrypted);
        textView.setText(inputNFCTag);

    }

    public void setTextViewDecrypted(String decrypted) {
        final TextView textView = findViewById(R.id.activity_bob_text_plainmessage);
        textView.setText(decrypted);

    }

    private void transfer() {
        if (mode != null && text != null) {
            bob.bobPreview(text, mode, this); //text, mode muss von NFC kommen

        }else{
            //TODO Fehlermeldung
        }
    }

}
