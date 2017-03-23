package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.appbusters.robinkamboj.senseitall.R;

import static android.content.Context.*;

public class WiFiActivity extends AppCompatActivity {

    WifiManager wifiManager;
    private String results[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setResults();
                setTextviews();
            }
        });
    }


    private void setResults(){
        results = new String[]{
//                "Name:    " + sensor.getName(), "Vendor:    " + sensor.getVendor(),
//                "Version:    " + String.valueOf(sensor.getVersion()),
//                "Maximum Range:    " + String.valueOf(sensor.getMaximumRange()),
//                "Power:    " + String.valueOf(sensor.getPower()),
//                "Minimum Delay:    " + String.valueOf(sensor.getMinDelay()),
//                "Maximum Delay:    " + String.valueOf(sensor.getMaxDelay()),
//                "Resolution:    " + String.valueOf(sensor.getResolution())
        };
    }

    private void setTextviews(){
//        name.setText(results[0]);
//        vendor.setText(results[1]);
//        version.setText(results[2]);
//        maximum_range.setText(results[3]);
//        power.setText(results[4]);
//        minimum_delay.setText(results[5]);
//        maximum_delay.setText(results[6]);
//        resolution.setText(results[7]);
    }
}
