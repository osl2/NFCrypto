package edu.kit.nfcrypto.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CompoundButton;

import edu.kit.nfcrypto.Alice;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.nfctools.NFCWriter;
import edu.kit.nfcrypto.nfctools.WriteResponse;

import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.KEY;
import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.MES;
import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.NULL;
import static edu.kit.nfcrypto.nfctools.NFCWriter.supportedTechs;

public class ActivityNFCWrite extends ActivityBase {

    //Ob Nachricht oder Schlüssel geschrieben werden soll
    public enum MessageState {
        MES, KEY, NULL

    }

    private MessageState message = NULL;
    private Alice alice;
    private NfcAdapter mNfcAdapter;
    private IntentFilter[] mWriteTagFilters;
    private PendingIntent mNfcPendingIntent;
    private Context context;


    protected void onCreate(Bundle savedInstanceState) {

        //setzt die Farbe der Toolbar
        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorAlice));
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcwrite);

        //nimmt Zwischenspeicherinstanz Alice entgegen
        alice = (Alice) getIntent().getSerializableExtra("alice");


        context = getApplicationContext();
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);
        IntentFilter discovery = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        // Intentfilter fpür NFCTags
        mWriteTagFilters = new IntentFilter[]{discovery};

        // Umschalter für das Schreiben von Nachricht und Schlüssel
        final Switch switchMessage = findViewById(R.id.activity_nfcwrite_switchMessage);
        final Switch switchKey = findViewById(R.id.activity_nfcwrite_switchKey);
        switchMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    switchKey.setChecked(false);
                    message = MES;
                } else if(!switchMessage.isChecked()) {
                    message = NULL;
                }
            }
        });
        switchKey.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    switchMessage.setChecked(false);
                    message = KEY;
                } else if(!switchKey.isChecked()) {
                    message = NULL;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters, null);
        } else {
            Toast.makeText(context, "Sorry, kein NFC Adapter gefunden", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            // überprüft ob auf den Tag geschreiben werden kann.
            Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (supportedTechs(detectedTag.getTechList())) {
                if (NFCWriter.writableTag(detectedTag)) {
                    String text = getText();
                    if (text != null) {
                        WriteResponse wr = NFCWriter.writeTag(NFCWriter.stringToData(text), detectedTag);

                        String message = (wr.getStatus() == 1 ? "Erfolg: " : "Fehler: ") + wr.getMessage();
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Bitte wähle die Art deiner NFC-Karte.",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Dieser Tag ist nicht beschreibbar", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Diese Art von Tag ist nicht kompatibel ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //Wählt den Text und Setzt den zuschreibenden Text lokal zusammen
    //O muss zuvor geschriebn werden, da dies den Typ definiert, der auf dem Tag steht. 0 => PLAIN
    private String getText() {
        if (message == NULL) {
            Toast.makeText(context, "Bitte drücke zuerst einen Knopf", Toast.LENGTH_LONG).show();
        } else if (message == MES) {
            //suffix ist "ENTSCHLUSSELT" nocheinmal getrennt verschlüsselt.
            String suffix = alice.getKey().suffix();
            return "0"+alice.getCurrentMessage().encodeEncryptedText()+suffix;
        } else if (message == KEY) {
            return "0"+alice.getKey().encodeKey();
        }

        return null;
    }

}