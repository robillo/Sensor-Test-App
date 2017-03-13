package com.appbusters.robinkamboj.senseitall.view.robin;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class ECGActivity extends AppCompatActivity {

    private String sensor_name, results[];
    private TextView textView;
    private Sensor sensor;
    private SensorManager sensorManager;
    private TextView name, vendor, version, maximum_range, power, minimum_delay, maximum_delay, resolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        sensor = sensorManager.getDefaultSensor(Sensor.);

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

        };
    }

    private void setTextviews(){

    }
}
