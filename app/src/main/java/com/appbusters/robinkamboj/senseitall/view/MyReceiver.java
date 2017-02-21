package com.appbusters.robinkamboj.senseitall.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.BatteryManager;
import android.util.Log;
import android.view.View;

import com.appbusters.robinkamboj.senseitall.R;

import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.ani;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.batteryperc;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.imageView;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.level;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.maxcl;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.plugged;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.present;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.status;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.tech;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.temp;
import static com.appbusters.robinkamboj.senseitall.view.BatteryActivity.vol;

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
                    plugged.setText("USB");
                }else if(acCharge){
                    plugged.setText("AC");
                }

            tech.setText(intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            vol.setText(String.valueOf(voltage));
            int tempe = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int dec = tempe % 10;
            tempe /= 10;
            temp.setText(String.valueOf(tempe) + "." + String.valueOf(dec)+"°");
            level.setText(String.valueOf(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)));
            maxcl.setText(String.valueOf(intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0)));
            present.setText("True");
        }

        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)||intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
            //tv.setText("POWER CONNECTED ");
            imageView.setBackgroundResource(R.drawable.gube);

            ani = (AnimationDrawable) imageView.getBackground();
            ani.start();
            //ani.run();
            batteryperc.setText(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)+"%");
            status.setText("Charging");

        }else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            status.setText("Not Charging");
            if(value<=50){
                imageView.setBackgroundResource(R.drawable.bless);
            }else if(value>=75&&value<95){
                imageView.setBackgroundResource(R.drawable.nninty);
            }else if(value>=50&&value<75){
                imageView.setBackgroundResource(R.drawable.nsevenfive);
            }else if(value>=95){
                imageView.setBackgroundResource(R.drawable.bfull);
            }
            batteryperc.setText(value+"%");
            batteryperc.setVisibility(View.VISIBLE);

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
