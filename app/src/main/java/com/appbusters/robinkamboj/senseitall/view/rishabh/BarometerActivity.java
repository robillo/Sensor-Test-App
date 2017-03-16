package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appbusters.robinkamboj.senseitall.R;

import static android.content.pm.PackageManager.*;

public class BarometerActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barometer);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
//        float altitude = SensorManager.getAltitude(FEATURE_SENSOR_BAROMETER, FEATURE_SENSOR_BAROMETER);

    }
}
