package com.appbusters.robinkamboj.senseitall.view;

import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appbusters.robinkamboj.senseitall.R;

public class NFCActivity extends AppCompatActivity {
    NfcAdapter nfcAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

//        nfcAdapter = new NfcAdapter();
        String pm = PackageManager.FEATURE_NFC;

       NfcManager nfcManager = (NfcManager) getSystemService(NFC_SERVICE);
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        boolean isen = nfcAdapter.isEnabled();
    }
}
