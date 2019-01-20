package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.kit.nfcrypto.Alice;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.InputFormatException;


public class ActivityAlice extends ActivityBase {


    Alice alice = new Alice();
    String messageString = null;
    Mode mode = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alice);

        final FloatingActionButton buttonInfo = findViewById(R.id.activity_alice_button_info);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityAlice.this.startActivity(new Intent(ActivityAlice.this, ActivityInfo.class));
            }
        });

        final FloatingActionButton buttonDetails = findViewById(R.id.activity_alice_button_details);
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityAlice.this.startActivity(new Intent(ActivityAlice.this, ActivityEncryptDetails.class));
            }
        });

        final FloatingActionButton buttonEncrypt = findViewById(R.id.activity_alice_button_encrypt);
        buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTextUpdate(messageString,mode);
            }
        });

        final EditText inputText = findViewById(R.id.activity_alice_text_message);
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    messageString  = inputText.getText().toString();
                    User.checkMessage(messageString);

                }catch (InputFormatException e){
                    //TODO Fehlermeldung
                }

            }
        });

        final Spinner modeSpinner  = findViewById(R.id.activity_alice_spinner);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO mode setzen, wie funktionieren Spinner?

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //TODO Button zum Schreiben der Nachricht/Schlüssel
    }

    private void onTextUpdate(String messageString, Mode mode){

        if(this.mode != null && this.messageString != null) {
            if (mode == Mode.CES) {

                alice.alicePreview(messageString, mode, getCesarDetails(), this); //TODO aus der Encryptdetails activity


            } else {
                alice.alicePreview(messageString, mode, 0, this);
            }
        } else {
            //TODO Fehlermeldung
        }
    }

    public void setTextView(String encrypted){
        final TextView textView = findViewById(R.id.activity_alice_text_crypted);
        textView.setText(encrypted);

    }
}
