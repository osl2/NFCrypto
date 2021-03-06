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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import edu.kit.nfcrypto.Bob;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.exceptions.WrongKeyException;
import edu.kit.nfcrypto.keys.Key;

import static edu.kit.nfcrypto.data.Mode.PLA;


public class ActivityBob extends ActivityBase {
    private enum MessageState {  //Typ der Karte, die gelesen werden soll ; NULL = nichts ausgewählt
        MES, KEY, NULL

    }

    private MessageState message = MessageState.NULL; //s.o.
    private NfcAdapter mNfcAdapter;
    private IntentFilter[] mNdefExchangeFilters;
    private PendingIntent mNfcPendingIntent;


    private Bob bob;
    private String text; //Text auf der NFC karte
    private Mode modeNFCMES;
    private Mode modeNFCKEY;//Mode auf der NFC Karte
    private Mode modeLastKey = null;
    private String keyString;
    private Key key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialisiert bob zum lokalen speichern
        bob = new Bob();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bob);

        //Setzt die Farbe der Toolbar
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

        //Knopf der das Entschlüsseln triggert
        final FloatingActionButton decryptButton = findViewById(R.id.activity_bob_button_decrypt);
        decryptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (keyString != null && text != null && !(
                        (modeNFCKEY == modeNFCMES | modeLastKey == modeNFCMES)
                                && modeNFCMES != null)) {
                    Toast.makeText(getApplicationContext(),
                            "Nachricht und Schlüssel passen nicht zusammen!",
                            Toast.LENGTH_LONG).show();
                } else if (modeNFCKEY == PLA || keyString != null && !keyString.equals("") && !keyString.equals(" ")) {
                    transfer();
                }
            }
        });

        final FloatingActionButton lastKeyButton = findViewById(R.id.activity_bob_button_lastkey);
        lastKeyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                key = User.getInstance().getLastKey();
                if (key != null) {
                    modeLastKey = key.getMode();
                    modeNFCKEY = null;
                    keyString = key.getKeyDataString();

                    if (keyString != null && text != null) {
                        if (modeLastKey == modeNFCMES) {
                            Toast.makeText(getApplicationContext(),
                                    "Du kannst auf Enschlüsseln drücken!",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            key = null;
                            Toast.makeText(getApplicationContext(),
                                    "Die Nachricht und Schlüssel passen nicht zusammen!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        // Umschalter für das Schreiben von Nachricht und Schlüssel
        final Switch switchMessage = findViewById(R.id.activity_bob_switchMessage);
        final Switch switchKey = findViewById(R.id.activity_bob_switchKey);
        switchMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchKey.setChecked(false);
                    message = MessageState.MES;
                } else if (!switchMessage.isChecked()) {
                    message = MessageState.NULL;
                }
            }
        });
        switchKey.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchMessage.setChecked(false);
                    message = MessageState.KEY;
                    key = null;
                } else if (!switchKey.isChecked()) {
                    message = MessageState.NULL;
                }
            }
        });

        // Info Knopf
        final FloatingActionButton buttonInfo = findViewById(R.id.activity_bob_button_info);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityBob.this.startActivity(new Intent(ActivityBob.this, ActivityBobInfo.class));
            }
        });
    }


    /**
     * Ändert den Inputtext
     *
     * @param inputNFCTag zeigt den Text der vom NFC Tag kommt im Inputtextfeld an.
     */
    private void setTextViewInput(String inputNFCTag) {
        final EditText textView = findViewById(R.id.activity_bob_text_encrypted);
        textView.setText(inputNFCTag);
    }


    /**
     * Ändert das entschlüsselte Textfeld
     *
     * @param decrypted text, der im Textfeld für den entschlüsselten Text angezeigt wird.
     */
    public void setTextViewDecrypted(String decrypted) {
        final TextView textView = findViewById(R.id.activity_bob_text_decrypted);
        textView.setText(decrypted);

    }

    @Override
    protected void onResume() { //TODO: Test ob NFC angeschalten ist
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent,
                    mNdefExchangeFilters, null);

        } else {
            Toast.makeText(getApplicationContext(), "Es wurde kein NFC-Adapter gefunden",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) mNfcAdapter.disableForegroundDispatch(this);
    }


    //Intent - aufgerufen durch NFC
    @Override
    protected void onNewIntent(Intent intent) {

        key = null;
        //liest Nachrichtenkarte
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


                //Abfrage des Präfixes auf der Karte
                if (message == MessageState.KEY) {
                    if (resultSplit[0].equals("KEY")) {

                        key = null;
                        modeLastKey = null;
                        modeNFCKEY = Mode.toMode(resultSplit[1]);
                        keyString = resultSplit[2];
                        Toast.makeText(getApplicationContext(),
                                "Du hast eine Schlüsselkarte eingelesen!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Das ist keine Schlüsselkarte.",
                                Toast.LENGTH_LONG).show();
                    }
                } else if (message == MessageState.MES) {
                    if (resultSplit[0].equals("MES")) {
                        modeNFCMES = Mode.toMode(resultSplit[1]);
                        text = resultSplit[2];
                        setTextViewInput(text);
                        Toast.makeText(getApplicationContext(),
                                "Du hast eine Nachrichtenkarte eingelesen!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Das ist keine Nachrichtenkarte.",
                                Toast.LENGTH_LONG).show();
                    }

                } else if (message == MessageState.NULL) {
                    Toast.makeText(getApplicationContext(),
                            "Bitte wähle zuerst ob du eine Nachricht oder einen Schlüssel "
                                    + "einlesen möchtest.",
                            Toast.LENGTH_LONG).show();
                }

                if (keyString != null && text != null && (modeNFCKEY == modeNFCMES
                        | modeLastKey == modeNFCMES) && modeNFCMES != null) {
                    Toast.makeText(getApplicationContext(),
                            "Du kannst nun auf Enschlüsseln drücken!", Toast.LENGTH_LONG).show();
                }

            }
        }
    }


    //Hilfsfunktion, überträgt die Daten zu Bob
    private void transfer() {
        if (text != null && modeNFCMES != null) {
            if (modeNFCKEY == PLA || keyString != null && !keyString.equals("") && !keyString.equals(" ")) {
          /*  if (modeNFCMES == PLA && keyString == null) {
                bob.bobPreview(text, PLA, null, this); //text, mode muss von NFC kommen
            } else if (keyString != null && modeNFCMES != PLA) { */
                try {
                    bob.bobPreview(text, modeNFCMES, keyString, this);
                } catch (WrongKeyException e)
                {
                    Toast.makeText(getApplicationContext(), "Dieser Schlüssel passt nicht.", Toast.LENGTH_LONG).show();
                }
            }
            // }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Du musst zuerst einen Schlüssel und eine Nachrichtenkarte einlesen oder "
                            + "auswählen",
                    Toast.LENGTH_LONG).show();
        }
    }


}
