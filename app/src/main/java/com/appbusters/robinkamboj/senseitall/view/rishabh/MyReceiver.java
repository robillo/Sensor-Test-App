package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.BatteryManager;
import android.util.Log;
import android.view.View;

import com.appbusters.robinkamboj.senseitall.R;

/**
 * Created by rishabhshukla on 10/02/17.
 */


public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    BatteryManager bm;

    int value;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: received broadcast");
        bm = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        value = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
//            Toast.makeText(context, "Battery Changed "+intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1), Toast.LENGTH_SHORT).show();
                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
                if(usbCharge){
                    BatteryActivity.plugged.setText("USB");
                }else if(acCharge){
                    BatteryActivity.plugged.setText("AC");
                }
            value = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            BatteryActivity.tech.setText(intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            BatteryActivity.vol.setText(String.valueOf(voltage));
            int tempe = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int dec = tempe % 10;
            tempe /= 10;
            BatteryActivity.temp.setText(String.valueOf(tempe) + "." + String.valueOf(dec)+"°");
            BatteryActivity.level.setText(String.valueOf(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)));
            BatteryActivity.maxcl.setText(String.valueOf(intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0)));
            BatteryActivity.present.setText("True");
        }

        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            //tv.setText("POWER CONNECTED ");
                BatteryActivity.imageView.setBackgroundResource(R.drawable.gube);
            value = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            BatteryActivity.ani = (AnimationDrawable) BatteryActivity.imageView.getBackground();
                BatteryActivity.ani.start();
                BatteryActivity.status.setText("Charging");

            //ani.run();
            BatteryActivity.batteryperc.setText(value+"%");


        }else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            value = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            BatteryActivity.status.setText("Not Charging");
            BatteryActivity.plugged.setText("___");
            if(value<=50){
                BatteryActivity.imageView.setBackgroundResource(R.drawable.bless);
            }else if(value>=75&&value<95){
                BatteryActivity.imageView.setBackgroundResource(R.drawable.nninty);
            }else if(value>=50&&value<75){
                BatteryActivity.imageView.setBackgroundResource(R.drawable.nsevenfive);
            }else if(value>=95){
                BatteryActivity.imageView.setBackgroundResource(R.drawable.bfull);
            }
            BatteryActivity.batteryperc.setText(value+"%");
            BatteryActivity.batteryperc.setVisibility(View.VISIBLE);

            intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.ACTION_CHARGING, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.ACTION_DISCHARGING, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_PRESENT, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_TECHNOLOGY, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0));
            Log.d(TAG, "onReceive: " + intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0));

            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_PLUGGED));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.ACTION_CHARGING));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_LEVEL));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.ACTION_DISCHARGING));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_HEALTH));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_PRESENT));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_STATUS));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_SCALE));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_TEMPERATURE));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_PLUGGED));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_ICON_SMALL));
            Log.d(TAG, "onReceive: " + intent.getStringExtra(BatteryManager.EXTRA_VOLTAGE));

//

    }
}}
