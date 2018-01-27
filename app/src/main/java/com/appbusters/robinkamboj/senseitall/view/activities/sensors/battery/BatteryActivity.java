package com.appbusters.robinkamboj.senseitall.view.activities.sensors.battery;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.appbusters.robinkamboj.senseitall.R;

public class BatteryActivity extends AppCompatActivity {

    public static ImageView imageView;
    public static AnimationDrawable ani;
    public static TextView batteryperc;
    BatteryManager bm;
    MyReceiver receiver;
    private static RoundCornerProgressBar progressBar;

    public static TextView level, plugged, present, maxcl, status, tech, temp, vol;

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
        progressBar = (RoundCornerProgressBar) findViewById(R.id.progressBar);


        level = (TextView) findViewById(R.id.level);
        plugged = (TextView) findViewById(R.id.plugged);
        present = (TextView) findViewById(R.id.present);
        maxcl = (TextView) findViewById(R.id.maxcl);
        status = (TextView) findViewById(R.id.status);
        tech = (TextView) findViewById(R.id.tech);
        temp = (TextView) findViewById(R.id.temp);
        vol = (TextView) findViewById(R.id.voltage);

        bm = (BatteryManager) getSystemService(BATTERY_SERVICE);


        bt = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        Log.e("% is", BatteryManager.EXTRA_LEVEL);
    }

    public static void setProgressBar(int percentage) {
        progressBar.setProgress(percentage);
        progressBar.setSecondaryProgress(percentage + 3);
    }
}
