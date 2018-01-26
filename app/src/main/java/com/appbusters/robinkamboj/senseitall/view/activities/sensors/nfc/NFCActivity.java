package com.appbusters.robinkamboj.senseitall.view.activities.sensors.nfc;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.os.HardwarePropertiesManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class NFCActivity extends AppCompatActivity {
    private static final String TAG = "NFC";
    NfcAdapter nfcAdapter;
    TextView ndef,enable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ndef= (TextView) findViewById(R.id.ndef);
        enable= (TextView) findViewById(R.id.enable);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        nfcAdapter = new NfcAdapter();

       NfcManager nfcManager = (NfcManager) getSystemService(NFC_SERVICE);
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        boolean isen = nfcAdapter.isEnabled();
        Log.d(TAG, "onCreate: "+nfcManager.toString());
        Log.d(TAG, "onCreate: "+nfcAdapter.isNdefPushEnabled());
        Log.d(TAG, "onCreate: "+nfcAdapter.isEnabled());
//        Log.d(TAG, "onCreate: "+nfcAdapter.invokeBeam(this));
        Log.d(TAG, "onCreate: "+nfcAdapter.toString());

        enable.setText(nfcAdapter.isEnabled()?"Yes":"No");
        ndef.setText(nfcAdapter.isNdefPushEnabled()?"Yes":"No");
    }
}
