package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class BatteryActivity extends AppCompatActivity{

    public static ImageView imageView;
    public static AnimationDrawable ani;
    public static TextView batteryperc;
    BatteryManager bm;
    MyReceiver receiver;

    public static TextView level,plugged,present,maxcl,status,tech,temp,vol;

    String sensor_name;
    int bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");

        imageView = (ImageView) findViewById(R.id.imagee);
        level = (TextView) findViewById(R.id.level);
        plugged = (TextView) findViewById(R.id.plugged);
        present = (TextView) findViewById(R.id.present);
        maxcl = (TextView) findViewById(R.id.maxcl);
        status = (TextView) findViewById(R.id.status);
        tech = (TextView) findViewById(R.id.tech);
        temp = (TextView) findViewById(R.id.temp);
        vol = (TextView) findViewById(R.id.voltage);

        bm = (BatteryManager) getSystemService(BATTERY_SERVICE);

        batteryperc = (TextView) findViewById(R.id.batteryperc);

        bt = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        Log.e("% is", BatteryManager.EXTRA_LEVEL);

//        if(bm.isCharging()){
//            imageView.setBackgroundResource(R.drawable.gube);
//            ani = (AnimationDrawable) imageView.getBackground();
//            ani.start();
//
//        }else if(bt<=15){
//            Log.e("PERCENTAGE LEVEL CHECK", "TRUE" + bt);
//            imageView.setBackgroundResource(R.drawable.bless);
//            batteryperc.setText(bt+"%");
//        }else if(bt<=75 && bt<95){
//            Log.e("PERCENTAGE LEVEL CHECK", "TRUE" + bt);
//            imageView.setBackgroundResource(R.drawable.nninty);
//            batteryperc.setText(bt+"%");
//        }else if(bt>=50&&bt<75){
//            Log.e("PERCENTAGE LEVEL CHECK", "TRUE" + bt);
//            imageView.setBackgroundResource(R.drawable.nsevenfive);
//            batteryperc.setText(bt+"%");
//        }else if(bt>=95){
//            Log.e("PERCENTAGE LEVEL CHECK", "TRUE" + bt);
//            batteryperc.setText(bt+"%");
//            imageView.setBackgroundResource(R.drawable.bfull);
//        }

        batteryperc.setVisibility(View.VISIBLE);

       // batLev= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,bt);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
//                setResults();
//                for(String s:results){
//                    Log.d(TAG, "run: "+s);
//                }
            }
        });

    }
//    private void setResults(){
//        results = new String[]{"%",BatteryManager.EXTRA_TEMPERATURE,
//        BatteryManager.EXTRA_SCALE,BatteryManager.EXTRA_VOLTAGE,BatteryManager.ACTION_CHARGING,BatteryManager.ACTION_DISCHARGING,
//        BatteryManager.EXTRA_HEALTH,BatteryManager.EXTRA_STATUS,BatteryManager.EXTRA_TECHNOLOGY,BatteryManager.EXTRA_LEVEL};
//
//    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
