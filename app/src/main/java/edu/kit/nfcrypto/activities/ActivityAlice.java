package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.kit.nfcrypto.Alice;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.InputFormatException;

import static edu.kit.nfcrypto.data.Mode.AES;
import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;
import static edu.kit.nfcrypto.data.Mode.VIG;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.ArrayList;


public class ActivityAlice extends ActivityBase {

    ArrayList <String> arrayPermissionString;
    ArrayList <Mode> arrayPermissionMode;
    Alice alice = new Alice();
    String messageString = null;
    Mode mode = PLA;
    int cesar;

    private static String FORBIDDEN_CHARS = "[^A-Z0-9 ,.?!():;#*\\-]"; //Negation (^) der erlaubten Zeichen

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alice);

        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorAlice));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Pair <ArrayList<String>,ArrayList<Mode>> p = User.getInstance().getPermissionArray(this);
        arrayPermissionString = p.first;
        arrayPermissionMode = p.second;

        final FloatingActionButton buttonInfo = findViewById(R.id.activity_alice_button_info);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityAlice.this.startActivity(new Intent(ActivityAlice.this, ActivityInfo.class));
            }
        });

        final FloatingActionButton buttonDetails = findViewById(R.id.activity_alice_button_details);
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ActivityAlice.this, ActivityEncryptDetails.class);
                if (messageString != null) {
                    i.putExtra("inputtext", messageString);
                }
                ActivityAlice.this.startActivity(i);
            }
        });

        final FloatingActionButton buttonEncrypt = findViewById(R.id.activity_alice_button_encrypt);
        buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTextUpdate(messageString, mode);
            }
        });

        final FloatingActionButton buttonNFCWrite = findViewById(R.id.activity_alice_button_nfcWrite);
        buttonNFCWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityAlice.this, ActivityNFCWrite.class);
                i.putExtra("alice", alice);
                ActivityAlice.this.startActivity(i);
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

                messageString = inputText.getText().toString();


            }
        });


        final Spinner modeSpinner = findViewById(R.id.activity_alice_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, arrayPermissionString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(adapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (arrayPermissionMode.size() < arrayPermissionString.size()) { //Wenn der Mode Array kleiner dem String Array ist muss nachträglich ein String hinzugefügt worden sein -> letztes Item ist Code
                    if (position == arrayPermissionString.size() - 1) {
                        ActivityAlice.this.startActivity(new Intent(ActivityAlice.this, ActivityCode.class));
                    }
                }else{
                    mode = arrayPermissionMode.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (getIntent().getStringExtra("inputtext") != null) {
            messageString = getIntent().getStringExtra("inputtext");
            inputText.setText(messageString);
        }

        if (getIntent().getIntExtra("cesar",-1) != -1) {
            cesar = getIntent().getIntExtra("cesar", -1);
            alice.setCesar(cesar);
            modeSpinner.setSelection(1);
        }


        //InputFilter um nur bestimmte Buchstaben zuzulassen
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                String sourceString = source.toString();
                sourceString = sourceString.toUpperCase();
                sourceString = sourceString.replaceAll("Ä", "AE");
                sourceString = sourceString.replaceAll("Ö", "OE");
                sourceString = sourceString.replaceAll("Ü", "UE");
                sourceString = sourceString.replaceAll(FORBIDDEN_CHARS, "");
                return sourceString;
            }
        };
        inputText.setFilters(new InputFilter[]{filter});

    }

    private void onTextUpdate(String messageString, Mode mode) {

        if (this.mode != null && this.messageString != null) {
            alice.alicePreview(messageString, mode, this);
        } else {
            //TODO Fehlermeldung
        }
    }

    public void setTextView(String encrypted) {
        final TextView textView = findViewById(R.id.activity_alice_text_encrypted);
        textView.setText(encrypted);

    }
}
