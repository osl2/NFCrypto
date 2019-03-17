package edu.kit.nfcrypto.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.cryptotools.Cryptotool;
import edu.kit.nfcrypto.cryptotools.CryptotoolCesar;
import edu.kit.nfcrypto.cryptotools.CryptotoolMinikey;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.Key;

import static edu.kit.nfcrypto.data.Mode.AES;
import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;
import static edu.kit.nfcrypto.data.Mode.toMode;

public class ActivityEve extends ActivityBase {
    private NfcAdapter mNfcAdapter;
    private IntentFilter[] mNdefExchangeFilters;
    private PendingIntent mNfcPendingIntent;

    private String help;
    // Verschlüsselter "ENTSCHLUESSELT" String, der zur Hilfe beim Knacken genutzt werden
    // soll.
    private Key key;
    private Cryptotool crypto; //zuverwendendes Cryptotool
    private ArrayList<String> arrayPermissionString; //Permissions
    private ArrayList<Mode> arrayPermissionMode;

    //Keine instanz zum zwischenspeichern, da direkt von der Activity aus operiert werden kann
    //und Eve weniger komplex ist, dafür passiert das in den Cryptotools

    private String text; //Text vom NFC Tag
    private Mode modeSelected; //Mode des Spinners
    private int spinner; //Position des Spinners
    private String decrypted; //Entschlüsselter Text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eve);

        key = null;
        //Setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorEve));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Setzt die Permissions
        Pair<ArrayList<String>, ArrayList<Mode>> p = User.getInstance().getPermissionArray(this);
        arrayPermissionString = p.first;
        arrayPermissionMode = p.second;

        //NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);

        mNdefExchangeFilters = new IntentFilter[]{};

        //Spinner vgl. ActivityAlice
        final Spinner modeSpinner = findViewById(R.id.activity_eve_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayPermissionString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(adapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinner = position;
                if (arrayPermissionMode.size()
                        < arrayPermissionString.size()) { //Wenn der Mode Array kleiner dem
                    // String Array ist muss nachträglich ein String hinzugefügt worden sein ->
                    // letztes Item ist Code
                    if (position == arrayPermissionString.size() - 1) {
                        Intent i = new Intent(ActivityEve.this, ActivityCode.class);
                        i.putExtra("origin", "eve");
                        ActivityEve.this.startActivity(i);

                    } else {
                        modeSelected = arrayPermissionMode.get(position);
                    }
                } else {
                    modeSelected = arrayPermissionMode.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Wenn von einer Anderen Activity ein Text für die Eingabe / zur hilfe / für Cesar/ als
        // Spinnerposition übergeben wird,
        //setzte diese & passe ggf. die Anzeige an.

        if (getIntent().getStringExtra("inputtext") != null) {
            text = getIntent().getStringExtra("inputtext");
            setTextViewInput(text);

        }


        if (getIntent().getIntExtra("spinner", spinner) != -1) {
            spinner = getIntent().getIntExtra("spinner", spinner);
            modeSpinner.setSelection(spinner);
        }
        if (getIntent().getSerializableExtra("key") != null) {
            key = (Key) getIntent().getSerializableExtra("key");
        }

        if (getIntent().getStringExtra("help") != null) {
            help = getIntent().getStringExtra("help");
            if (text != null & spinner != -1 && key == null) {
                //Stellung des Spinners wird ausgelesen, kann/sollte noch ggf. ausgelagert
                // werden, Toasts sind dann schwerer
                switch (spinner) {

                    //Plaintext
                    default:
                        break;

                    //Cesar
                    case 1:
                        crypto = new CryptotoolCesar();
                        decrypted = crypto.crack(text, help);
                        break;

                    //Minikey
                    case 2: //Knacken automatisch
                        crypto = new CryptotoolMinikey();
                        decrypted = crypto.crack(text, help);
                        break;

                }
                key = null;
            } else  if (key == null){
                Toast.makeText(getApplicationContext(),
                        "Du brauchst zuerst eine Nachrichtenkarte.", Toast.LENGTH_LONG).show();
            }

        }


        //Knopf der die Varschlüsselung triggert

        final FloatingActionButton buttonDecrypt = findViewById(R.id.activity_eve_button_decrypt);
        buttonDecrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (text != null & (key != null || (help != null & decrypted != null))) {
                    if (key != null) {
                        setTextViewDecrypted(key.decrypt(text));
                    } else {
                        setTextViewDecrypted(decrypted);
                    }
                } else {
                    if (text == null) {
                        Toast.makeText(getApplicationContext(),
                                "Du brauchst zuerst eine Nachrichtenkarte.",
                                Toast.LENGTH_LONG).show();
                    } else if (modeSelected == PLA) {
                        Toast.makeText(getApplicationContext(),
                                "Klartext ist nicht verschlüsselt",
                                Toast.LENGTH_LONG).show();
                        setTextViewDecrypted(text);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Bitte klicke vorher auf den Schraubenschlüssel",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        // Info Knopf
        final FloatingActionButton buttonInfo = findViewById(R.id.activity_eve_button_info);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityEve.this.startActivity(new Intent(ActivityEve.this, ActivityEveInfo.class));
            }
        });

        // Knopf der zur passenden Cryptotoolactivity weiterleitet
        final FloatingActionButton buttonCryptotool = findViewById(
                R.id.activity_eve_button_cryptotools);
        buttonCryptotool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Class destination;
                Intent i;
                if (text != null) {
                    switch (modeSelected) {
                        case PLA:
                            destination = ActivityCryptotoolsPlain.class;
                            i = new Intent(ActivityEve.this, destination);
                            ActivityEve.this.startActivity(i);
                            break;
                        case CES:
                            destination = ActivityCryptotoolsCesar.class;
                            i = new Intent(ActivityEve.this, destination);
                            i.putExtra("inputtext", text);
                            i.putExtra("spinner", modeSelected.toInt());
                            i.putExtra("help", help);
                            ActivityEve.this.startActivity(i);
                            break;
                        case VIG:
                            destination = ActivityCryptotoolsMinikey.class;
                            i = new Intent(ActivityEve.this, destination);
                            i.putExtra("inputtext", text);
                            i.putExtra("spinner", modeSelected.toInt());
                            i.putExtra("help", help);
                            ActivityEve.this.startActivity(i);
                            break;
                        case AES:
                            destination = ActivityCryptotoolsAES.class;
                            i = new Intent(ActivityEve.this, destination);
                            ActivityEve.this.startActivity(i);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(),
                                    "Kein Cryptotool ausgewählt!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Du brauchst zuerst eine Nachrichtenkarte.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    //Vgl. ActivityBob
    public void setTextViewInput(String text) {
        final EditText textView = findViewById(R.id.activity_eve_text_encrypted);
        textView.setText(text);

    }

    //Vgl. ActivityBob
    private void setTextViewDecrypted(String decrypted) {
        final TextView textView = findViewById(R.id.activity_eve_text_decrypted);
        textView.setText(decrypted);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent,
                    mNdefExchangeFilters, null);

        } else {
            Toast.makeText(getApplicationContext(), "Sorry, No NFC Adapter found.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //NFC
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {


            NdefMessage[] messages = null;
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                messages = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    messages[i] = (NdefMessage) rawMsgs[i];
                }
            }
            if (messages[0] != null) {
                String result = "";
                byte[] payload = messages[0].getRecords()[0].getPayload();
                for (int b = 1; b < payload.length; b++) {
                    result += (char) payload[b];
                }


                String[] resultSplit = User.splitInput(result);
                // Toast.makeText(getApplicationContext(), "Tag Contains " + result, Toast
                // .LENGTH_SHORT).show();


                if (resultSplit[0].equals("MES")) {
                    //Mode von NFCTag
                    Mode modeNFC = Mode.toMode(resultSplit[1]);
                    text = resultSplit[2];
                    help = resultSplit[3];
                    setTextViewInput(text);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Dies ist keine Nachrichtenkarte, Eve kann nur Nachrichtenkarten lesen",
                            Toast.LENGTH_LONG).show();
                }


            }
        }
    }


}
