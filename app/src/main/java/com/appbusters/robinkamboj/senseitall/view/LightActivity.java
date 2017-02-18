package com.appbusters.robinkamboj.senseitall.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import cn.fanrunqi.waveprogress.WaveProgressView;

public class LightActivity extends AppCompatActivity {

    WaveProgressView meter;
    TextView maximum, current;
    float max;
//    float temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        maximum = (TextView) findViewById(R.id.maximum);
        current = (TextView) findViewById(R.id.current);
        meter = (WaveProgressView) findViewById(R.id.meter);

//        temp = (float) 0.5;
//        meter.setProgress(temp);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        max = lightSensor.getMaximumRange();
        maximum.setText(String.valueOf(max));
        meter.setMaxProgress((int) max);

        sensorManager.registerListener(lightSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    SensorEventListener lightSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
                float currentReading = sensorEvent.values[0];
                current.setText(String.valueOf(currentReading));
                String set = String.valueOf(String.format("%.2f",(currentReading/max)*100));
                meter.setCurrent((int) currentReading, set);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
