package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.gyroscope_test;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class GyroscopeTestFragment extends Fragment implements GyroscopeTestInterface {

    private Sensor sensor;
    private SensorManager sensorManager;
    SensorEventListener sensorEventListener;

    @BindView(R.id.x_val)
    TextView xValue;

    @BindView(R.id.y_val)
    TextView yValue;

    @BindView(R.id.z_val)
    TextView zValue;

    private float valueX = 0;
    private float valueY = 0;
    private float valueZ = 0;

    public GyroscopeTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gyroscope_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initialize();
    }

    @Override
    public void initialize() {
        if(getActivity() == null) return;
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        if(sensorManager == null) return;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event != null) {
                    valueX = event.values[0];
                    valueY = event.values[1];
                    valueZ = event.values[2];
                    xValue.setText(String.valueOf(valueX));
                    yValue.setText(String.valueOf(valueY));
                    zValue.setText(String.valueOf(valueZ));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        if(sensorManager != null && sensorEventListener != null)
            sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sensorManager != null && sensor != null && sensorEventListener != null)
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);
    }
}
