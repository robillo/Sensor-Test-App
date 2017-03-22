package com.appbusters.robinkamboj.senseitall.view.robin;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import java.io.IOException;
import java.io.InputStream;

public class CPUActivity extends AppCompatActivity {

    String sensor_name, results[];
    TextView textView, cpu_all;

    ProcessBuilder processBuilder;
    String Holder = "";
    String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
    InputStream inputStream;
    Process process ;
    byte[] byteArry ;

    TextView processor, number_of_cores, max_frequency, min_frequency, current_frequency, cpu_architecture, bogomips,
             features, cpu_implementer, cpu_variant, cpu_part, cpu_revision, hardware, cpu_serial,
             memory_size, memory_free, memory_used,
             internal_size, internal_free, internal_used, external_size, external_free, external_used,
             storage_size, partition_size1, partition_size2, partition_size3, partition_size4, partition_size5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu);

        cpu_all = (TextView) findViewById(R.id.cpu_all);
        byteArry = new byte[1024];
        try{
            processBuilder = new ProcessBuilder(DATA);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            while(inputStream.read(byteArry) != -1){
                Holder = Holder + new String(byteArry);
            }
            inputStream.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        cpu_all.setText(Holder);

        processor = (TextView) findViewById(R.id.processor);
        number_of_cores = (TextView) findViewById(R.id.cores);
        max_frequency = (TextView) findViewById(R.id.max_frequency);
        min_frequency = (TextView) findViewById(R.id.min_frequency);
        current_frequency = (TextView) findViewById(R.id.current_frequency);
        cpu_architecture = (TextView) findViewById(R.id.cpu_architecture);
        bogomips = (TextView) findViewById(R.id.bogomips);
        features = (TextView) findViewById(R.id.features);
        cpu_implementer = (TextView) findViewById(R.id.cpu_implementer);
        cpu_variant = (TextView) findViewById(R.id.cpu_variant);
        cpu_part = (TextView) findViewById(R.id.cpu_part);
        cpu_revision = (TextView) findViewById(R.id.cpu_revision);
        hardware = (TextView) findViewById(R.id.hardware);
        cpu_serial = (TextView) findViewById(R.id.serial);
        memory_size = (TextView) findViewById(R.id.memory_size);
        memory_free = (TextView) findViewById(R.id.memory_free);
        memory_used = (TextView) findViewById(R.id.memory_used);
        internal_size = (TextView) findViewById(R.id.internal_storage_size);
        internal_free = (TextView) findViewById(R.id.internal_storage_free);
        internal_used = (TextView) findViewById(R.id.internal_storage_used);
        external_size = (TextView) findViewById(R.id.external_storage_size);
        external_free = (TextView) findViewById(R.id.external_storage_free);
        external_used = (TextView) findViewById(R.id.external_storage_used);
        storage_size = (TextView) findViewById(R.id.storage_size);
        partition_size1 = (TextView) findViewById(R.id.partition_size1);
        partition_size2 = (TextView) findViewById(R.id.partition_size2);
        partition_size3 = (TextView) findViewById(R.id.partition_size3);
        partition_size4 = (TextView) findViewById(R.id.partition_size4);
        partition_size5 = (TextView) findViewById(R.id.partition_size5);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setResults();
                setTextviews();
            }
        });
    }

    private void setResults(){
        results = new String[]{
                


        };
    }

    private void setTextviews(){

    }
}
