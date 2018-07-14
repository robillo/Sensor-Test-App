package com.appbusters.robinkamboj.senseitall.view.sensors.gravity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class GravityActivity extends AppCompatActivity {

    String sensor_name;
    TextView textView;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    private String results[];
    Button btnTest;

    private TextView name, vendor, version, maximum_range, power, minimum_delay, maximum_delay, resolution, x,y,z;
    float alpha = (float) 0.8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);

        name = (TextView) findViewById(R.id.name);
        vendor = (TextView) findViewById(R.id.vendor);
        version = (TextView) findViewById(R.id.version);
        maximum_range = (TextView) findViewById(R.id.maximum_range);
        power = (TextView) findViewById(R.id.power);
        minimum_delay = (TextView) findViewById(R.id.minimum_delay);
        maximum_delay = (TextView) findViewById(R.id.maximum_delay);
        resolution = (TextView) findViewById(R.id.resolution);
        x = (TextView) findViewById(R.id.xVal);
        y = (TextView) findViewById(R.id.yVal);
        z = (TextView) findViewById(R.id.zVal);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                x.setText("X Axis:    " + event.values[0]+ " m/s²");
                y.setText("Y Axis:    " + event.values[1]+ " m/s²");
                z.setText("Z Axis:    " + event.values[2]+ " m/s²");

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };


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
                "Name:    " + sensor.getName(), "Vendor:    " + sensor.getVendor(),
                "Version:    " + String.valueOf(sensor.getVersion()),
                "Maximum Range:    " + String.valueOf(sensor.getMaximumRange()),
                "Power:    " + String.valueOf(sensor.getPower()),
                "Minimum Delay:    " + String.valueOf(sensor.getMinDelay()),
                "Maximum Delay:    " + String.valueOf(sensor.getMaxDelay()),
                "Resolution:    " + String.valueOf(sensor.getResolution())
        };
    }

    private void setTextviews(){
        name.setText(results[0]);
        vendor.setText(results[1]);
        version.setText(results[2]);
        maximum_range.setText(results[3]);
        power.setText(results[4]);
        minimum_delay.setText(results[5]);
        maximum_delay.setText(results[6]);
        resolution.setText(results[7]);
    }

    @Override
    protected void onResume() {

        super.onResume();

        sensorManager.registerListener(sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}