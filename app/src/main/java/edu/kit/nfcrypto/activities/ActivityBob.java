package edu.kit.nfcrypto.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PatternMatcher;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.kit.nfcrypto.Alice;
import edu.kit.nfcrypto.Bob;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.Key;

import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.KEY;
import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.MES;
import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.NULL;
import static edu.kit.nfcrypto.data.Mode.AES;
import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;
import static edu.kit.nfcrypto.data.Mode.VIG;


public class ActivityBob extends ActivityBase {
    public enum MessageState {
        MES, KEY, NULL

    }

    private MessageState message = MessageState.NULL;
    private static final String TAG = "NFCReadTag";
    private NfcAdapter mNfcAdapter;
    private IntentFilter[] mNdefExchangeFilters;
    private PendingIntent mNfcPendingIntent;


    private Bob bob;
    String text;
    Mode mode;
    String keyString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bob = new Bob();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bob);

        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorBob));
        } catch (Exception e) {
            e.printStackTrace();
        }


        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);

        mNdefExchangeFilters = new IntentFilter[]{};


        Button keyButton = findViewById(R.id.activity_bob_button_readKey);
        keyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = MessageState.KEY;
            }
        });
        final Button messageButton = findViewById(R.id.activity_bob_button_readMessage);
        messageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = MessageState.MES;
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
        final TextView textView = findViewById(R.id.activity_bob_text_encrypted);
        textView.setText(inputNFCTag);

    }

    public void setTextViewDecrypted(String decrypted) {
        final TextView textView = findViewById(R.id.activity_bob_text_decrypted);
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

                if (message == MessageState.KEY) {
                    if (resultSplit[0].equals("KEY")) {
                        setMode(resultSplit[1]);
                        keyString = resultSplit[2];
                        Toast.makeText(getApplicationContext(), keyString, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Dies ist keine Schlüsselkarte.", Toast.LENGTH_LONG).show();
                    }
                } else if (message == MessageState.MES) {
                    if (resultSplit[0].equals("MES")) {
                        setMode(resultSplit[1]);
                        text = resultSplit[2];
                        setTextViewInput(text);
                    } else {
                        Toast.makeText(getApplicationContext(), "Dies ist keine Nachrichtenkarte.", Toast.LENGTH_LONG).show();
                    }

                } else if (message == MessageState.NULL) {
                    Toast.makeText(getApplicationContext(), "Bitte wähle zuerst einen Knopf", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    private void transfer() {
        if (text != null && mode != null) {
            if (mode == PLA && keyString == null) {
                bob.bobPreview(text, PLA, null, this); //text, mode muss von NFC kommen
            } else if (keyString != null && mode != PLA) {
                bob.bobPreview(text, mode, keyString, this);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Alles muss gesetzt sein", Toast.LENGTH_LONG).show();
        }
    }

    public void setMode(String string) {
        if (mode != null && !string.equals(mode.toString())) {
            Toast.makeText(getApplicationContext(), "Diese Karten gehören nicht zusammen", Toast.LENGTH_LONG).show();
        } else {
            switch (string) {
                case "CES":
                    this.mode = CES;
                    break;
                case "PLA":
                    this.mode = PLA;
                    break;
                case "VIG":
                    this.mode = VIG;
                    break;
                case "AES":
                    this.mode = AES;
                    break;

            }
        }

    }

}
