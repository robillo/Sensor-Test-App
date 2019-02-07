package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.linear_acceleration_test;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.test_activity.graph_fragment_abstract.GraphFragment;

import java.text.DecimalFormat;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinearAccTestFragment extends GraphFragment implements LinearAccTestInterface {


    public LinearAccTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_linear_acc_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void initialize() {
        decimalFormat = new DecimalFormat("#.##");

        if(getActivity() == null) return;
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        if(sensorManager == null) return;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event != null) {
                    valueX = event.values[0];
                    valueY = event.values[1];
                    valueZ = event.values[2];
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);
    }
}
