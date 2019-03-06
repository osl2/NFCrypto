package edu.kit.nfcrypto.nfctools;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class NFCWriterTest {

    @Test
    public void stringToData() {

        String datatext = "MESPLATESTENTSCHLUESSELT";

        NdefMessage testMock = PowerMockito.mock(NdefMessage.class);
        testMock = NFCWriter.stringToData(datatext);

        NdefMessage compareMock = PowerMockito.mock(NdefMessage.class);

        NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], datatext.getBytes());
        compareMock = new NdefMessage(new NdefRecord[]{record});

        when(testMock.toString()).thenReturn("NdefMessage [NdefRecord tnf=1 type=54 payload=4D4553504C4154455354454E545343484C55455353454C54]");
        when(compareMock.toString()).thenReturn("NdefMessage [NdefRecord tnf=1 type=54 payload=4D4553504C4154455354454E545343484C55455353454C54]");

        assertEquals(testMock.toString(), compareMock.toString());

    }
}