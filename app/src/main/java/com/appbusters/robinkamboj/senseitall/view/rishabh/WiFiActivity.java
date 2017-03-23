package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.BTInfo;

import java.util.List;
import java.util.Set;

import static android.content.Context.*;

public class WiFiActivity extends AppCompatActivity {

    WifiManager wifiManager;
    private String results[];
    TextView state, ssid, rssi, bssid, hidden, frequency, ip, link, mac,networkid;
    WifiInfo wifiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);

        ssid = (TextView) findViewById(R.id.ssid);
        state = (TextView) findViewById(R.id.state);
        rssi = (TextView) findViewById(R.id.rssi);
        bssid = (TextView) findViewById(R.id.bssid);
        hidden = (TextView) findViewById(R.id.hidden);
        frequency = (TextView) findViewById(R.id.frequency);
        ip = (TextView) findViewById(R.id.ip);
        link = (TextView) findViewById(R.id.link);
        mac = (TextView) findViewById(R.id.mac);
        networkid = (TextView) findViewById(R.id.networkid);

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        final ProgressDialog progress = new ProgressDialog(WiFiActivity.this);
        progress.setMessage("Getting WIFI Info...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                setResults();
                setTextviews();

                List<WifiConfiguration> configDevices = wifiManager.getConfiguredNetworks();


                if (configDevices.size() > 0) {
                    for (WifiConfiguration device : configDevices) {


//                        String deviceName = device.getName();
//                        String deviceHardwareAddress = device.getAddress(); // MAC address
//
//                        Log.d(TAG, "run: "+deviceName);
//                        Log.d(TAG, "run: "+deviceHardwareAddress);

                    }
                }
                progress.hide();

//                BluetoothActivity.RVAdapter ad = new RVAdapter();
//
//                rv.setLayoutManager(new LinearLayoutManager(WiFiActivity.this));
//
//                rv.setAdapter(ad);

            }
        }, 1500);

        if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
            state.setText("State:    Disabled");
        } else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING) {
            state.setText("State:    Disabling");
        } else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            state.setText("State:    Enabled");
        } else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
            state.setText("State:    Enabling");
        } else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_UNKNOWN) {
            state.setText("State:    UNKNOWN");
        }

        wifiInfo = wifiManager.getConnectionInfo();


    }


    private void setResults(){
        results = new String[]{
                "SSID:    " + String.valueOf(wifiInfo.getSSID()),
                "Frequency:    " + String.valueOf(wifiInfo.getFrequency()),
                "IP Address:    " + String.valueOf(wifiInfo.getIpAddress()),
                "Link:    " + String.valueOf(wifiInfo.getLinkSpeed()),
                "Mac Address:    " + String.valueOf(wifiInfo.getMacAddress()),
                "BSSID:    " + String.valueOf(wifiInfo.getBSSID()),
                "Netword ID:    " + String.valueOf(wifiInfo.getNetworkId()),
                "SSID Hidden:    " + String.valueOf(wifiInfo.getHiddenSSID()),
                "RSSI:    " + String.valueOf(wifiInfo.getRssi())

        };
    }

    private void setTextviews(){
        state.setText(results[0]);
        frequency.setText(results[1]);
        ip.setText(results[2]);
        link.setText(results[3]);
        mac.setText(results[4]);
        bssid.setText(results[5]);
        networkid.setText(results[6]);
        hidden.setText(results[7]);
        rssi.setText(results[7]);
    }
}
