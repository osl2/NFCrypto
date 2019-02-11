package edu.kit.nfcrypto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.*;

import edu.kit.nfcrypto.Alice;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;

import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;

import android.text.InputFilter;
import android.text.Spanned;
import edu.kit.nfcrypto.keys.Key;

import java.util.ArrayList;


public class ActivityAlice extends ActivityBase {

    private ArrayList<String> arrayPermissionString; // Permission Elemente
    private ArrayList<Mode> arrayPermissionMode;
    private final Alice alice = new Alice();
    private Key key = null; //Nur zur besseren Datenübertragung
    private String messageString = null;
    private Mode mode = PLA;    // mode wird standardmäßig als PLA inizialisiert, da dies dem NULL objekt entspricht

    private static final String FORBIDDEN_CHARS = "[^A-Z0-9 ,.?!():;#*\\-]"; //Negation (^) der erlaubten Zeichen

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alice);

        //Setzt den Key zurück
        key = null;
        alice.setKey(null);

        // Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorAlice));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Läd die aktuellen Permissions
        Pair<ArrayList<String>, ArrayList<Mode>> p = User.getInstance().getPermissionArray(this);
        arrayPermissionString = p.first;
        arrayPermissionMode = p.second;

        // Info Knopf
        final FloatingActionButton buttonInfo = findViewById(R.id.activity_alice_button_info);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityAlice.this.startActivity(new Intent(ActivityAlice.this, ActivityAliceInfo.class));
            }
        });

        //Knopf der die details aufruft
        final FloatingActionButton buttonDetails = findViewById(R.id.activity_alice_button_details);
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Class destination = ActivityEncryptDetailsPlain.class;
                switch (mode){
                    case PLA: destination=ActivityEncryptDetailsPlain.class;break;
                    case CES: destination = ActivityEncryptDetailsCesar.class;break;
                    case VIG:destination = ActivityEncryptDetailsMinikey.class;break;
                    case AES:destination = ActivityEncryptDetailsAES.class;break;
                }

                //dem Intent werden alle wichtigen indos zum Speichern übergeben.
                Intent i = new Intent(ActivityAlice.this, destination);
                if (messageString != null) {
                    i.putExtra("inputtext", messageString);
                } if (key != null){
                    i.putExtra("key",key);
                }
                ActivityAlice.this.startActivity(i);
            }
        });

        //Knopf der die Verschlüsselung und die Aktualisierung des Textfeldes initialisiert
        final FloatingActionButton buttonEncrypt = findViewById(R.id.activity_alice_button_encrypt);
        buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTextUpdate(messageString, mode);
            }
        });

        //Knopf der die NFCWrite aufruft
        final FloatingActionButton buttonNFCWrite = findViewById(R.id.activity_alice_button_nfcWrite);
        buttonNFCWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dem Intent wird Alice mitgegeben, diese speichert alle lokal wichtigen Daten
                Intent i = new Intent(ActivityAlice.this, ActivityNFCWrite.class);
                i.putExtra("alice", alice);
                ActivityAlice.this.startActivity(i);
            }
        });


        // Eingabefeld
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
                //wenn sich der Text geändert hat
                messageString = inputText.getText().toString();
            }
        });

        //Dropdownmenü
        final Spinner modeSpinner = findViewById(R.id.activity_alice_spinner);
        //Nutzt die Permissions um alles erlaubte anzuzeigen
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayPermissionString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(adapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alice.setKey(null);

                if (arrayPermissionMode.size() < arrayPermissionString.size()) { //Wenn der Mode Array kleiner dem String Array ist muss nachträglich ein String hinzugefügt worden sein -> letztes Item ist Code
                    if (position == arrayPermissionString.size() - 1) {
                        Intent i = new Intent(ActivityAlice.this, ActivityCode.class);
                        i.putExtra("origin", "alice");
                        ActivityAlice.this.startActivity(i);
                    }else{
                        mode = arrayPermissionMode.get(position);
                    }
                } else {
                    mode = arrayPermissionMode.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (getIntent().getIntExtra("cesar", -1) != -1) {
                    mode = CES;
                } else {
                    mode = PLA;
                }

            }
        });


        // Falls die Activity beim Starten einen Eingabetext erhält, soll dieser angezeigt werden
        if (getIntent().getStringExtra("inputtext") != null) {
            messageString = getIntent().getStringExtra("inputtext");
            inputText.setText(messageString);
        }

        //Falls die Activity einen Key erhält, soll dieser genutz werden
        if (getIntent().getSerializableExtra("key")!=null) {
            key =(Key)getIntent().getSerializableExtra("key");

        }

        //Falls vorher eine Encrypt Details aufgerufen wurde.
        if(getIntent().getSerializableExtra("spinner")!= null){
            Mode spinner = (Mode) getIntent().getSerializableExtra("spinner");
            modeSpinner.setSelection(spinner.toInt());
            mode = spinner;
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
        inputText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(100)});

    }


    //Hilfsmethode
    private void onTextUpdate(String messageString, Mode mode) {

        if (this.mode != null && this.messageString != null) {
            if(key != null){
                alice.setKey(key);
            }
            alice.alicePreview(messageString, mode, this);
        } else {
            Toast.makeText(this, "Bitte gebe einen Text ein!", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Setzt von außerhalb den encryptedtext
     *
     * @param encrypted Text der angezeigt werden soll
     */
    public void setTextView(String encrypted) {
        final TextView textView = findViewById(R.id.activity_alice_text_encrypted);
        textView.setText(encrypted);

    }
}
