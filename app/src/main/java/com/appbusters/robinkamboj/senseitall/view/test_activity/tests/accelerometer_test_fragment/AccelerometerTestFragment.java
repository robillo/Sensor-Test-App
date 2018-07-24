package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.accelerometer_test_fragment;


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
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccelerometerTestFragment extends Fragment
        implements AccelerometerTestInterface, SensorEventListener {

    private int[] colorGen;

    @BindView(R.id.background)
    View background;

    private long lastUpdate;
    private SensorManager sensorManager;

    public AccelerometerTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_accelerometer_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        colorGen = new int[] {
                getResources().getColor(R.color.colorMajor),
                getResources().getColor(R.color.colorMajorDark),
                getResources().getColor(R.color.colorPrimaryLight),
                getResources().getColor(R.color.colorPrimaryLightLight)};

        lastUpdate = System.currentTimeMillis();
        if(getActivity() != null)
            sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        if(sensorManager != null)
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        else {
            Toast.makeText(getActivity(), "Unable to load sensor", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            getAccelerometer(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        Random r = new Random();
        float accelerationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if (accelerationSquareRoot >= 2) {
            if (actualTime - lastUpdate < 200) return;

            lastUpdate = actualTime;
            background.setBackgroundColor(colorGen[Math.abs(r.nextInt())%colorGen.length]);
        }
    }
}
