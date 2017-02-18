package com.appbusters.robinkamboj.senseitall.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class CPUActivity extends AppCompatActivity {

    String sensor_name;
    TextView textView;
    Context context;
    TextView processor, number_of_cores, max_frequency, min_frequency, current_frequency, cpu_architecture, bogomips,
             features, cpu_implementer, cpu_variant, cpu_part, cpu_revision, hardware, cpu_serial,
             memory_size, memory_free, memory_used,
             internal_size, internal_free, internal_used, external_size, external_free, external_used,
             storage_size, partition_size1, partition_size2, partition_size3, partition_size4, partition_size5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);
    }
}
