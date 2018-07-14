package com.appbusters.robinkamboj.senseitall.view.sensors.radio;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class RadioActivity extends AppCompatActivity {

    String sensor_name;
    TextView textView, radio_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        radio_version = findViewById(R.id.radio_version);
        radio_version.setText(Build.getRadioVersion());
    }
}
