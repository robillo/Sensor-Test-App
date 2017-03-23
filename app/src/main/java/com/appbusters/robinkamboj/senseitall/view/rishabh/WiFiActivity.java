package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.BTInfo;
import com.appbusters.robinkamboj.senseitall.model.ScanResultInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.content.Context.*;

public class WiFiActivity extends AppCompatActivity {

    WifiManager wifiManager;
    ArrayList<ScanResultInfo> scanList;
    private String results[];
    TextView state, ssid, rssi, bssid, hidden, frequency, ip, link, mac,networkid;
    WifiInfo wifiInfo;
    RecyclerView rv;


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
        rv = (RecyclerView) findViewById(R.id.rv);

        scanList = new ArrayList<>();

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();


        final ProgressDialog progress = new ProgressDialog(WiFiActivity.this);
        progress.setMessage("Getting WIFI Info...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
                    state.setText("State:    Disabled");
                } else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING) {
                    state.setText("State:    Disabling");
                } else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
                    state.setText("State:    Enabled");
                } else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
                    state.setText("State:    Enabling");
                } else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_UNKNOWN) {
                    state.setText("State:    Unknown");
                }

                setResults();
                setTextviews();

                List<ScanResult> scanResultList = wifiManager.getScanResults();

                if (scanResultList.size() > 0) {
                    for (ScanResult scanResult : scanResultList) {
                        scanList.add(new ScanResultInfo(
                                scanResult.SSID,
                                scanResult.capabilities,
                                scanResult.level,
                                scanResult.frequency,
                                scanResult.timestamp,
                                scanResult.BSSID));
                    }
                }
                progress.hide();

                RVAdapter ad = new RVAdapter();

                rv.setLayoutManager(new LinearLayoutManager(WiFiActivity.this));

                rv.setAdapter(ad);

            }
        }, 1500);



    }

    class Holder extends RecyclerView.ViewHolder{

        TextView name,timestamp,level,capabilities,freq,mac;

        public Holder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.namesc);
            this.timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            this.level = (TextView) itemView.findViewById(R.id.level);
            this.capabilities = (TextView) itemView.findViewById(R.id.capabilities);
            this.freq = (TextView) itemView.findViewById(R.id.frequency);
            this.mac = (TextView) itemView.findViewById(R.id.mac);
        }
    }
    class RVAdapter extends RecyclerView.Adapter<Holder>{

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View itemView =  li.inflate(R.layout.layout_view_wifi, parent, false);

            return new Holder(itemView);
        }

        @Override
        public void onBindViewHolder(final Holder holder, int position) {

            final ScanResultInfo sri = scanList.get(position);
            holder.name.setText(sri.getName());
            holder.level.setText("Level:    "+sri.getLevel()+" dB");
            holder.capabilities.setText("Capabilities:    "+sri.getCapabilities());
            holder.freq.setText("Frequency:    "+sri.getFrequency()+" MHz");
            holder.timestamp.setText("Timestamp:    "+sri.getTimestamp()+" microsecs");
            holder.mac.setText("MAC:    "+sri.getMac());

        }

        @Override
        public int getItemCount() {
            return scanList.size();
        }
    }



    private void setResults(){
        results = new String[]{
                "SSID:    " + String.valueOf(wifiInfo.getSSID()),
                "Frequency:    " + String.valueOf(wifiInfo.getFrequency()+" MHz"),
                "IP Address:    " + String.valueOf(wifiInfo.getIpAddress()),
                "Link:    " + String.valueOf(wifiInfo.getLinkSpeed()+" Mbps"),
                "Mac Address:    " + String.valueOf(wifiInfo.getMacAddress()),
                "BSSID:    " + String.valueOf(wifiInfo.getBSSID()),
                "Netword ID:    " + String.valueOf(wifiInfo.getNetworkId()),
                "SSID Hidden:    " + String.valueOf(wifiInfo.getHiddenSSID()),
                "RSSI:    " + String.valueOf(wifiInfo.getRssi())

        };
    }

    private void setTextviews(){
        ssid.setText(results[0]);
        frequency.setText(results[1]);
        ip.setText(results[2]);
        link.setText(results[3]);
        mac.setText(results[4]);
        bssid.setText(results[5]);
        networkid.setText(results[6]);
        hidden.setText(results[7]);
        rssi.setText(results[8]);
    }
}
