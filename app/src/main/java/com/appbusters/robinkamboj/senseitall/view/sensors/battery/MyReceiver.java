package com.appbusters.robinkamboj.senseitall.view.sensors.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity;

import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.imageView;
import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.level;
import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.maxcl;
import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.plugged;
import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.present;
import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.status;
import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.tech;
import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.temp;
import static com.appbusters.robinkamboj.senseitall.view.sensors.battery.BatteryActivity.vol;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    BatteryManager bm;

    int value;

    @Override
    public void onReceive(Context context, Intent intent) {

        int rawlevel = intent.getIntExtra("level", -1);
        int scale = intent.getIntExtra("scale", -1);
        int levell = -1;
        if (rawlevel >= 0 && scale > 0) {
            levell = (rawlevel * 100) / scale;
        }
        Log.e("TEMP","Battery Level in % is:: " + level + "%");

        Log.d(TAG, "onReceive: received broadcast");
        bm = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        value = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
//            Toast.makeText(context, "Battery Changed "+intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1), Toast.LENGTH_SHORT).show();
                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
                if(usbCharge){
                    if(plugged!=null) {
                        BatteryActivity.plugged.setText("USB");
                    }
                }else if(acCharge){
                    if(plugged!=null) {
                        BatteryActivity.plugged.setText("AC");
                    }
                }
            value = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            if(tech!=null) {
                BatteryActivity.tech.setText(intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
            }
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            if(vol!=null) {
                vol.setText(String.valueOf(voltage));
            }
            int tempe = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int dec = tempe % 10;
            tempe /= 10;
            if(temp!=null) {
                temp.setText(String.valueOf(tempe) + "." + String.valueOf(dec) + "°");
            }
            if(level!=null) {
                BatteryActivity.level.setText(String.valueOf(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)));
                BatteryActivity.setProgressBar(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0));
            }
            if(maxcl!=null) {
                BatteryActivity.maxcl.setText(String.valueOf(intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0)));
            }
            if(present!=null) {
                BatteryActivity.present.setText("True");
            }
        }

        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            //tv.setText("POWER CONNECTED ");
            if(imageView!=null) {
//                BatteryActivity.imageView.setBackgroundResource(R.drawable.gube);
            }
            value = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

//            if(ani!=null) {
//                BatteryActivity.ani = (AnimationDrawable) BatteryActivity.imageView.getBackground();
//                BatteryActivity.ani.start();
//            }
            if(status!=null) {
                BatteryActivity.status.setText("Charging");
            }
            Log.d(TAG, "onReceive: ");
            //ani.run();


        }else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            value = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            if(status!=null) {
                BatteryActivity.status.setText("Not Charging");
            }
            if(plugged!=null) {
                BatteryActivity.plugged.setText("___");
            }
//            if(value<=50){
//                if(imageView!=null) {
//                    BatteryActivity.imageView.setBackgroundResource(R.drawable.bless);
//                }
//            }else if(value>=75&&value<95){
//                if(imageView!=null) {
//                    BatteryActivity.imageView.setBackgroundResource(R.drawable.nninty);
//                }
//            }else if(value>=50&&value<75){
//                if(imageView!=null) {
//                    BatteryActivity.imageView.setBackgroundResource(R.drawable.nsevenfive);
//                }
//            }else if(value>=95){
//                if(imageView!=null) {
//                    BatteryActivity.imageView.setBackgroundResource(R.drawable.bfull);
//                }
//            }
//            if(batteryperc!=null) {
//                batteryperc.setText(value + "%");
//                batteryperc.setVisibility(View.VISIBLE);
//            }
            intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.ACTION_CHARGING, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.ACTION_DISCHARGING, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_PRESENT, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_TECHNOLOGY, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0));
//            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0));
//
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_PLUGGED));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.ACTION_CHARGING));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_LEVEL));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.ACTION_DISCHARGING));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_HEALTH));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_PRESENT));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_STATUS));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_SCALE));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_TEMPERATURE));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_PLUGGED));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_ICON_SMALL));
//            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_VOLTAGE));

//

    }
}}
