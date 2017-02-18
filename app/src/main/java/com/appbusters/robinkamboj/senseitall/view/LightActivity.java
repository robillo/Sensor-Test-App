package com.appbusters.robinkamboj.senseitall.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.github.yongjhih.mismeter.MisMeter;

public class LightActivity extends AppCompatActivity {

    MisMeter meter;
    TextView maximum, current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        maximum = (TextView) findViewById(R.id.maximum);
        current = (TextView) findViewById(R.id.current);
        meter = (MisMeter) findViewById(R.id.meter);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        float max = lightSensor.getMaximumRange();
        maximum.setText(String.valueOf(max));
    }
}
