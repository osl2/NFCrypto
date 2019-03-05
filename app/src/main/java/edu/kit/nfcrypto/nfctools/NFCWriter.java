package edu.kit.nfcrypto.nfctools;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

import java.io.IOException;

public final class NFCWriter {

    public static WriteResponse writeTag(NdefMessage message, Tag tag) {

        int size = message.toByteArray().length;
        String mess;
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                    return new WriteResponse(0,"Dieser Tag ist nicht beschreibbar");
                }
                if (ndef.getMaxSize() < size) {
                    mess = "Tag Speicherplatz ist " + ndef.getMaxSize() + " bytes, Inhalt ist " + size
                            + " bytes. Dies ist zu groß.";
                    return new WriteResponse(0,mess);
                }
                ndef.writeNdefMessage(message);
                mess = "der Schlüssel oder die Nachricht wurde auf den Tag geschrieben";
                return new WriteResponse(1,mess);
            } else {
                NdefFormatable format = NdefFormatable.get(tag);
                if (format != null) {
                    try {
                        format.connect();
                        format.format(message);
                        mess = "der Schlüssel oder die Nachricht wurde auf den Tag geschrieben";
                        return new WriteResponse(1,mess);
                    } catch (IOException e) {
                        mess = "Es hat nicht geklappt";
                        return new WriteResponse(0,mess);
                    }
                } else {
                    mess = "Dieser Tag wird nicht unterstützt.";
                    return new WriteResponse(0,mess);
                }
            }
        } catch (Exception e) {
            mess = "Etwas ist schiefgelaufen";
            return new WriteResponse(0,mess);
        }
    }


    /**
     * Schaut ob der Typ des Tags unterstützt wird
     * @param techs Typen des Tags
     * @return ob techs unterstützt werden.
     */
    public static boolean supportedTechs(String[] techs) {
        boolean ultralight=false;
        boolean nfcA=false;
        boolean ndef=false;
        for(String tech:techs) {
            if(tech.equals("android.nfc.tech.MifareUltralight")) {
                ultralight=true;
            }else if(tech.equals("android.nfc.tech.NfcA")) {
                nfcA=true;
            } else if(tech.equals("android.nfc.tech.Ndef") || tech.equals("android.nfc.tech.NdefFormatable")) {
                ndef=true;
            }
        }
        return ultralight && nfcA && ndef;
    }

    /**
     * gibt zurück ob
     * @param tag beschreibbar ist.
     * @return beschreibbar
     */
    public static boolean writableTag(Tag tag) {
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                    //TODO Fehlermeldung
                    return false;
                }
                ndef.close();
                return true;
            }
        } catch (Exception e) {
           //TODO
        }
        return false;
    }

    /**
     * Bereitet
     * @param datatext  zu
     * @return  NFCData auf und gibt diese zurück
     */
    public static NdefMessage stringToData(String datatext) {
            NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], datatext.getBytes());
            return new NdefMessage(new NdefRecord[]{record});

    }
}
