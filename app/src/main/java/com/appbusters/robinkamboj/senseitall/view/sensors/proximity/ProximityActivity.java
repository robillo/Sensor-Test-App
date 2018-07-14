package com.appbusters.robinkamboj.senseitall.view.sensors.proximity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.github.yongjhih.mismeter.MisMeter;

public class ProximityActivity extends AppCompatActivity {

    private static final String TAG = "PROX";
    private MisMeter meter;
    private SensorManager sensorManager;
    private Sensor sensor;
    private String results[];

    private TextView name, vendor, wake_up, minimum_delay, version, power, maximum_range, resolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        meter = (MisMeter) findViewById(R.id.meter);
        meter.setProgress(1);

        name = (TextView) findViewById(R.id.name);
        vendor = (TextView) findViewById(R.id.vendor);

        minimum_delay = (TextView) findViewById(R.id.minimum_delay);
        version = (TextView) findViewById(R.id.version);
        power = (TextView) findViewById(R.id.power);
        maximum_range = (TextView) findViewById(R.id.maximum_range);
        resolution = (TextView) findViewById(R.id.resolution);
        wake_up = (TextView) findViewById(R.id.wake_up);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setResults();
                setTextViews();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sensorManager.registerListener(proximitySensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void setResults(){
        results = new String[]{sensor.getName(), sensor.getVendor(), (sensor.isWakeUpSensor())?"true":"false",
                String.valueOf(sensor.getMinDelay()), String.valueOf(sensor.getVersion()),
                String.valueOf(sensor.getPower()), String.valueOf(sensor.getMaximumRange()),
                String.valueOf(sensor.getResolution())};
    }

    private void setTextViews(){
        name.setText(results[0]);
        vendor.setText(results[1]);
        wake_up.setText(results[2]);
        minimum_delay.setText(results[3]);
        version.setText(results[4]);
        power.setText(results[5]);
        maximum_range.setText(results[6]);
        resolution.setText(results[7]);
    }

    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
                float currentReading = sensorEvent.values[0];
                Log.e(TAG, "onSensorChanged: "+currentReading);

                if(currentReading==0||currentReading==3){
                    meter.setProgress(0);
                }
                else {
                    meter.setProgress(1);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

}
