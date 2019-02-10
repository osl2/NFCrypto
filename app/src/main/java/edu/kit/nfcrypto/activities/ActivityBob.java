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
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.User;
import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.Key;

import static edu.kit.nfcrypto.data.Mode.AES;
import static edu.kit.nfcrypto.data.Mode.CES;
import static edu.kit.nfcrypto.data.Mode.PLA;
import static edu.kit.nfcrypto.data.Mode.VIG;


public class ActivityBob extends ActivityBase {
    private enum MessageState {  //Typ der Karte, die gelesen werden soll ; NULL = nichts ausgewählt
        MES, KEY, NULL

    }

    private MessageState message = MessageState.NULL; //s.o.
    private static final String TAG = "NFCReadTag";
    private NfcAdapter mNfcAdapter;
    private IntentFilter[] mNdefExchangeFilters;
    private PendingIntent mNfcPendingIntent;


    private Bob bob;
    private String text; //Text auf der NFC karte
    private Mode mode; //Mode auf der NFC Karte
    private String keyString;

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

        //Knopf zum Einlesen des Schlüssels
        final Button keyButton = findViewById(R.id.activity_bob_button_readKey);
        keyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = MessageState.KEY;
            }
        });

        //Knopf zum Einlesen der Nachricht
        final Button messageButton = findViewById(R.id.activity_bob_button_readMessage);
        messageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = MessageState.MES;
            }
        });

        //Knopf der das Entschlüsseln triggert
        final FloatingActionButton decryptButton = findViewById(R.id.activity_bob_button_decrypt);
        decryptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                transfer();
            }
        });

        final Button lastKeyButton = findViewById(R.id.activity_bob_button_lastkey);
        lastKeyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Key key = User.getInstance().getLastKey();
                if (key != null) {
                    setMode(key.getMode().toString());
                    keyString = key.getKeyDataString();

                    if (keyString != null && text != null) {
                        Toast.makeText(getApplicationContext(), "Du kannst nun auf Enschlüsseln drücken!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    /**
     * Ändert den Inputtext
     *
     * @param inputNFCTag zeigt den Text der vom NFC Tag kommt im Inputtextfeld an.
     */
    public void setTextViewInput(String inputNFCTag) {
        final TextView textView = findViewById(R.id.activity_bob_text_encrypted);
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
            Toast.makeText(getApplicationContext(), "Es wurde kein NFC-Adapter gefunden", Toast.LENGTH_SHORT).show();
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
                // Toast.makeText(getApplicationContext(), "Tag Contains " + result, Toast.LENGTH_SHORT).show();


                //Abfrage des Präfixes auf der Karte
                if (message == MessageState.KEY) {
                    if (resultSplit[0].equals("KEY")) {
                        setMode(resultSplit[1]);
                        keyString = resultSplit[2];
                        //Toast.makeText(getApplicationContext(), keyString, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplicationContext(), "Bitte wähle zuerst ob du eine Nachrichten- oder eine Schlüsselkarte lesen möchtest.", Toast.LENGTH_LONG).show();
                }

                if (keyString != null && text != null) {
                    Toast.makeText(getApplicationContext(), "Du kannst nun auf Enschlüsseln drücken!", Toast.LENGTH_LONG).show();
                }

            }
        }
    }


    //Hilfsfunktion, überträgt die Daten zu Bob
    private void transfer() {
        if (text != null && mode != null) {
            if (mode == PLA && keyString == null) {
                bob.bobPreview(text, PLA, null, this); //text, mode muss von NFC kommen
            } else if (keyString != null && mode != PLA) {
                bob.bobPreview(text, mode, keyString, this);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Du musst zuerst einen Schlüssel und eine Nachtichtenkarte einlesen oder auswählen", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Wandelt Strings in Modi um.
     *
     * @param string String der zu Mode umgewandelt werden soll (wird dann gleich in Bob gesetzt.
     */
    public void setMode(String string) {

        //Wenn Schlüssel und Nachrichtenkarte nicht zusammen passen
        if (mode != null && !string.equals(mode.toString())) {
            Toast.makeText(getApplicationContext(), "Diese Karten gehören nicht zusammen", Toast.LENGTH_LONG).show();
        } else {
            this.mode = Mode.toMode(string);

        }

    }

}
