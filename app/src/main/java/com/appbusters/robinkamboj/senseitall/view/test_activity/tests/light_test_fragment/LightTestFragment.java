package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.light_test_fragment;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.appbusters.robinkamboj.senseitall.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightTestFragment extends Fragment implements LightTestInterface {

    private int color;
    float max, percentage;
    private SensorManager sensorManager;

    @BindView(R.id.round_progress)
    RoundCornerProgressBar progressBar;

    @BindView(R.id.card_bar)
    CardView cardBar;

    @BindView(R.id.max_text)
    TextView maxText;

    @BindView(R.id.current_text)
    TextView currentText;

    @BindView(R.id.percent_text)
    TextView percentText;

    public LightTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_light_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        if(getActivity() != null)
            sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        if(sensorManager != null) {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            max = sensor.getMaximumRange();
            maxText.setText(String.valueOf(max));
            sensorManager.registerListener(eventListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    SensorEventListener eventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.sensor.getType()== Sensor.TYPE_LIGHT){

                float curr = sensorEvent.values[0];
                currentText.setText(String.valueOf(curr));
                percentage = (curr/max)*100;
                progressBar.setProgress(percentage);
                progressBar.setSecondaryProgress(percentage + 3);
                String temp = String.format(Locale.getDefault(), "%.3g", percentage);
                percentText.setText(temp);

                if(percentage <= 10){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity1);
                }
                else if(percentage <= 20){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity2);
                }
                else if(percentage <= 30){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity3);
                }
                else if(percentage <= 40){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity4);
                }
                else if(percentage <= 50){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity5);
                }
                else if(percentage <= 60){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity6);
                }
                else if(percentage <= 70){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity7);
                }
                else if(percentage <= 80){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity8);
                }
                else if(percentage <= 90){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity9);
                }
                else if(percentage <= 100){
                    if(getActivity() != null)
                        color = ContextCompat.getColor(getActivity(), R.color.colorIntensity10);
                }

                cardBar.setCardBackgroundColor(color);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
