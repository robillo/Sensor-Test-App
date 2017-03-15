package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appbusters.robinkamboj.senseitall.R;

public class RotationActivity extends AppCompatActivity {


    private SensorManager mSensorManager;
    private Sensor mSensor;
    TriggerEventListener mTriggerEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        mTriggerEventListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent event) {
                // Do work
            }
        };

        mSensorManager.requestTriggerSensor(mTriggerEventListener, mSensor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.requestTriggerSensor(mTriggerEventListener, mSensor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.cancelTriggerSensor(mTriggerEventListener, mSensor);
    }
}
