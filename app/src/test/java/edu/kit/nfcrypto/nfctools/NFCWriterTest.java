package edu.kit.nfcrypto.nfctools;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

public class NFCWriterTest {


    @Test
    public void stringToData() {

        String datatext = "MESPLATESTENTSCHLUESSELT";

        NdefMessage testMessage = NFCWriter.stringToData(datatext);
        NdefMessage testSpy = Mockito.spy(testMessage);

        NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], datatext.getBytes());
        NdefMessage compareMessage = new NdefMessage(new NdefRecord[]{record});
        NdefMessage compareSpy = Mockito.spy(compareMessage);


        byte[] testSpyBytes;
        byte[] compareSpyBytes;

        //testSpyBytes = testSpy.toByteArray();
        //compareSpyBytes = compareSpy.toByteArray();

        //testSpyBytes = Mockito.verify(testSpy).toByteArray();
        //compareSpyBytes = Mockito.verify(compareSpy).toByteArray();


        //assertEquals(testSpyBytes, compareSpyBytes);

    }
}