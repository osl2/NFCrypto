package edu.kit.nfcrypto.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import edu.kit.nfcrypto.Bob;
import edu.kit.nfcrypto.Eve;
import edu.kit.nfcrypto.R;
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

    Eve eve;
    String text;
    Mode mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eve);

        eve = new Eve();
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);

        mNdefExchangeFilters = new IntentFilter[]{};

        final FloatingActionButton buttonCryptotools = findViewById(R.id.activity_eve_button_cryptotools);
        buttonCryptotools.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityEve.this.startActivity(new Intent(ActivityEve.this, ActivityCryptotoolsCesar.class));
            }
        });

        final Button buttonMessage = findViewById(R.id.activity_eve_button_readMessage);
        buttonCryptotools.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pressed = true;
            }
        });

    }

    public void setTextViewInput(String inputNFCTag) {
        final TextView textView = findViewById(R.id.activity_eve_text_encrypted);
        textView.setText(inputNFCTag);

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


                String[] resultSplit = eve.splitInput(result);
                Toast.makeText(getApplicationContext(), "Tag Contains " + result, Toast.LENGTH_SHORT).show();

                if (pressed) {
                    if (resultSplit[0].equals("MES")) {
                        mode = toMode(resultSplit[1]);
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
