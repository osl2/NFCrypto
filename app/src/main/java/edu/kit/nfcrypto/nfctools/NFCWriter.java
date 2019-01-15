package edu.kit.nfcrypto.nfctools;

import android.nfc.NdefMessage;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

import edu.kit.nfcrypto.keys.Key;
import edu.kit.nfcrypto.data.Message;

public final class NFCWriter {

    /**
     * writeNFC schreibt auf den
     * @param tag die Daten des Keya
     * @param data , NdefMessage, da NFC Gedöns
     */
    public void writeNFC(Tag tag, NdefMessage data) {
        if (tag != null) {
            try {
                Ndef ndefTag = Ndef.get(tag);
                if (ndefTag == null) {
                    // Let's try to format the Tag in NDEF
                    NdefFormatable nForm = NdefFormatable.get(tag);
                    if (nForm != null) {
                        nForm.connect();
                        nForm.format(data);
                        nForm.close();
                    }
                } else {
                    ndefTag.connect();
                    ndefTag.writeNdefMessage(data);
                    ndefTag.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Bereitet die Daten für den NFCTag auf
     * @param datatext
     * @return
     */
    private NdefMessage stringToData(String datatext) {
        byte[] dataBytes = datatext.getBytes();
        try {
            NdefMessage data = new NdefMessage(dataBytes);
            return data;
        } catch (android.nfc.FormatException e) {
            //TODO
            return null;
        }

    }
}
