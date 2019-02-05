package edu.kit.nfcrypto.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.kit.nfcrypto.Alice;
import edu.kit.nfcrypto.R;
import edu.kit.nfcrypto.nfctools.NFCWriter;
import edu.kit.nfcrypto.nfctools.WriteResponse;

import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.KEY;
import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.MES;
import static edu.kit.nfcrypto.activities.ActivityNFCWrite.MessageState.NULL;
import static edu.kit.nfcrypto.nfctools.NFCWriter.supportedTechs;

public class ActivityNFCWrite extends ActivityBase {

    public enum MessageState {
        MES, KEY, NULL

    }

    private MessageState message = NULL;
    private Alice alice;
    private static final String TAG = "NFCWriteTag";
    private NfcAdapter mNfcAdapter;
    private IntentFilter[] mWriteTagFilters;
    private PendingIntent mNfcPendingIntent;
    private boolean silent = false;
    private boolean writeProtect = false;
    private Context context;


    public boolean isWriteProtect() {
        return writeProtect;
    }

    protected void onCreate(Bundle savedInstanceState) {

        try {
            getToolbar().setBackgroundColor(this.getResources().getColor(R.color.colorAlice));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcwrite);
        alice = (Alice) getIntent().getSerializableExtra("alice");

        final Button buttonMessage = findViewById(R.id.activity_nfcwrite_button_message);
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = MES;
            }
        });

        final Button buttonKey = findViewById(R.id.activity_nfcwrite_button_key);
        buttonKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = KEY;
            }
        });

        context = getApplicationContext();
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);
        IntentFilter discovery = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        // Intent filters for writing to a tag
        mWriteTagFilters = new IntentFilter[]{discovery};
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters, null);
        } else {
            Toast.makeText(context, "Sorry, No NFC Adapter found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //.getCurrentMessage().getEncryptedText();
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            // validate that this tag can be written
            Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (supportedTechs(detectedTag.getTechList())) {
                // check if tag is writable (to the extent that we can
                if (NFCWriter.writableTag(detectedTag)) {
                    //writeTag here
                    String text = getText();
                    if (text != null) {
                        WriteResponse wr = NFCWriter.writeTag(NFCWriter.stringToData(text), detectedTag);

                        String message = (wr.getStatus() == 1 ? "Success: " : "Failed: ") + wr.getMessage();
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Bitte wähle einen Knopf aus",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "This tag is not writable", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "This tag type is not supported", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getText() {
        if (message == NULL) {
            Toast.makeText(context, "Bitte drücke zuerst einen Knopf", Toast.LENGTH_LONG).show();
        } else if (message == MES) {
            String suffix = alice.getKey().suffix();
            return "0"+alice.getCurrentMessage().encodeEncryptedText()+suffix;
        } else if (message == KEY) {
            return "0"+alice.getKey().encodeKey();
        }

        return null;
    }

}