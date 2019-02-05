package edu.kit.nfcrypto.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import static edu.kit.nfcrypto.data.Mode.AES;
import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;
import static edu.kit.nfcrypto.data.Mode.VIG;

public class ActivityEve extends ActivityBase {

    private boolean pressed = false;
    private static final String TAG = "NFCReadTag";
    private NfcAdapter mNfcAdapter;
    private IntentFilter[] mNdefExchangeFilters;
    private PendingIntent mNfcPendingIntent;
    private String help;
    private int cesar;
    private Cryptotool crypto;
    ArrayList<String> arrayPermissionString;
    ArrayList<Mode> arrayPermissionMode;

    String text;
    Mode modeSelected;
    Mode modeNFC;
    int spinner;
    String decrypted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eve);

        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorEve));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pair<ArrayList<String>, ArrayList<Mode>> p = User.getInstance().getPermissionArray(this);
        arrayPermissionString = p.first;
        arrayPermissionMode = p.second;


        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);

        mNdefExchangeFilters = new IntentFilter[]{};

        final Spinner modeSpinner = findViewById(R.id.activity_eve_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayPermissionString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(adapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modeSelected = arrayPermissionMode.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (getIntent().getStringExtra("inputtext") != null) {
            text = getIntent().getStringExtra("inputtext");
            setTextViewInput(text);
        }

        if (getIntent().getIntExtra("cesar", -1) != -1) {
            cesar = getIntent().getIntExtra("cesar", -1);
        }

        if (getIntent().getIntExtra("spinner", -1) != -1) {
            spinner = getIntent().getIntExtra("spinner", -1);
            modeSpinner.setSelection(spinner);
        }

        final FloatingActionButton buttonCryptotools = findViewById(R.id.activity_eve_button_cryptotools);
        buttonCryptotools.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Class destination = ActivityEve.class;
                Intent i;
                switch (modeSelected) {
                    case PLA:
                        Toast.makeText(getApplicationContext(), "Klartext ist nicht verschlüsselt", Toast.LENGTH_LONG).show();
                        break;
                    case CES:
                        destination = ActivityCryptotoolsCesar.class;
                        break;
                    case VIG:
                        destination = ActivityCryptotoolsMinikey.class;
                        break;
                    case AES:
                        destination = ActivityCryptotoolsAES.class;

                }
                if (modeSelected != PLA) {
                    i = new Intent(ActivityEve.this, destination);
                    i.putExtra("inputtext", text);
                    i.putExtra("spinner", modeSelected.toInt());
                    ActivityEve.this.startActivity(i);
                }
            }
        });

        final Button buttonMessage = findViewById(R.id.activity_eve_button_readMessage);
        buttonCryptotools.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pressed = true;
            }
        });

        //TODO auslagern?

        if (spinner != -1 & text != null & help != null) {
            decrypted = "";
            switch (spinner) {
                case 0:
                    decrypted = text;
                    break;
                case 1:
                    if (cesar != -1) {
                        crypto = new CryptotoolCesar(cesar);
                        decrypted = crypto.crack(text, help);
                    } else {
                        //TODO Fehler
                    }
                    break;
                case 2:
                    crypto = new CryptotoolMinikey();
                    decrypted = crypto.crack(text, help);
                    break;
                case 3:
                    decrypted = "Das Knacken von AES dauert mehrere Jahre";
                    break;
            }
            setTextViewDecrypted(decrypted);


        }
    }


    public void setTextViewInput(String text) {
        final TextView textView = findViewById(R.id.activity_eve_text_encrypted);
        textView.setText(text);

    }

    public void setTextViewDecrypted(String decrypted) {
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
            Toast.makeText(getApplicationContext(), "Sorry, No NFC Adapter found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
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
                // this assumes that we get back am SOH followed by host/code
                for (int b = 1; b < payload.length; b++) { // skip SOH
                    result += (char) payload[b];
                }


                String[] resultSplit = User.splitInput(result);
                Toast.makeText(getApplicationContext(), "Tag Contains " + result, Toast.LENGTH_SHORT).show();

                if (pressed) {
                    if (resultSplit[0].equals("MES")) {
                        modeNFC = toMode(resultSplit[1]);
                        text = resultSplit[2];
                        help = resultSplit[3];
                        setTextViewInput(text);
                    } else {
                        Toast.makeText(getApplicationContext(), "Dies ist keine Nachrichtenkarte, Eve kann nur Nachrichtenkarten lesen", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Bitte drücke zuerst den Knopf", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    private Mode toMode(String string) {
        switch (string) {
            case "CES":
                return CES;
            case "PLA":
                return PLA;

            case "VIG":
                return VIG;

            case "AES":
                return AES;


        }
        return null;

    }


}
