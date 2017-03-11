package com.appbusters.robinkamboj.senseitall.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class StepDetectorActivity extends AppCompatActivity {

    String sensor_name;
    TextView textView;
    TextView step, accuracy, sampling_rate, minimum_delay, name, vendor, version, power, maximum_delay, resolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detector);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

        step = (TextView) findViewById(R.id.step);
        accuracy = (TextView) findViewById(R.id.accuracy);
        sampling_rate = (TextView) findViewById(R.id.sampling_rate);
        minimum_delay = (TextView) findViewById(R.id.minimum_delay);
        name = (TextView) findViewById(R.id.name);
        vendor = (TextView) findViewById(R.id.vendor);
        version = (TextView) findViewById(R.id.version);
        power = (TextView) findViewById(R.id.power);
        maximum_delay = (TextView) findViewById(R.id.maximum_delay);
        resolution = (TextView) findViewById(R.id.resolution);
    }
}
