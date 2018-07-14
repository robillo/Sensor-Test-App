package com.appbusters.robinkamboj.senseitall.view.sensors.light;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.appbusters.robinkamboj.senseitall.R;
import com.github.yongjhih.mismeter.MisMeter;

import java.util.Locale;

public class LightActivity extends AppCompatActivity {

    String sensor_name, results[];
    TextView textView;
    private Sensor sensor;
    private SensorManager sensorManager;
    private RoundCornerProgressBar progressBar;
    private CardView card;
    private int color;
    TextView maximum, current;
    float max, percentage;
    private TextView vendor, minimum_delay, version, power, resolution, perc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        card = (CardView) findViewById(R.id.card);
        progressBar = (RoundCornerProgressBar) findViewById(R.id.progressBar);
        vendor = (TextView) findViewById(R.id.vendor);
        minimum_delay = (TextView) findViewById(R.id.minimum_delay);
        version = (TextView) findViewById(R.id.version);
        power = (TextView) findViewById(R.id.power);
        resolution = (TextView) findViewById(R.id.resolution);
        perc = (TextView) findViewById(R.id.percentage);

        maximum = (TextView) findViewById(R.id.maximum);
        current = (TextView) findViewById(R.id.current);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setResults();
                setTextViews();
            }
        });

        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        max = lightSensor.getMaximumRange();
        maximum.setText(String.valueOf(max));

        sensorManager.registerListener(lightSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void setResults(){
        results = new String[]{sensor.getVendor(),
                String.valueOf(sensor.getMinDelay()), String.valueOf(sensor.getVersion()),
                String.valueOf(sensor.getPower()), String.valueOf(sensor.getResolution())};
    }

    private void setTextViews(){
        vendor.setText(results[0]);
        minimum_delay.setText(results[1]);
        version.setText(results[2]);
        power.setText(results[3]);
        resolution.setText(results[4]);
    }


    SensorEventListener lightSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){

                float curr = sensorEvent.values[0];
                current.setText(String.valueOf(curr));
                percentage = (curr/max)*100;
                progressBar.setProgress(percentage);
                progressBar.setSecondaryProgress(percentage+3);
                String temp = String.format(Locale.getDefault(), "%.3g", percentage);
                perc.setText(temp);

                if(percentage<=10){
                    color = getResources().getColor(R.color.colorIntensity1);
                }
                else if(percentage<=20){
                    color = getResources().getColor(R.color.colorIntensity2);
                }
                else if(percentage<=30){
                    color = getResources().getColor(R.color.colorIntensity3);
                }
                else if(percentage<=40){
                    color = getResources().getColor(R.color.colorIntensity4);
                }
                else if(percentage<=50){
                    color = getResources().getColor(R.color.colorIntensity5);
                }
                else if(percentage<=60){
                    color = getResources().getColor(R.color.colorIntensity6);
                }
                else if(percentage<=70){
                    color = getResources().getColor(R.color.colorIntensity7);
                }
                else if(percentage<=80){
                    color = getResources().getColor(R.color.colorIntensity8);
                }
                else if(percentage<=90){
                    color = getResources().getColor(R.color.colorIntensity9);
                }
                else if(percentage<=100){
                    color = getResources().getColor(R.color.colorIntensity10);
                }

                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        card.setCardBackgroundColor(color);
                    }
                });
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
