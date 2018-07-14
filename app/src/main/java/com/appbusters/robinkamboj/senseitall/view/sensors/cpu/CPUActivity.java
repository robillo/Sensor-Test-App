package com.appbusters.robinkamboj.senseitall.view.sensors.cpu;

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
    String[] DATA = {"/system/bin/cat", "/proc/cpuinfo", "/proc/partitions"};
    InputStream inputStream;
    Process process ;
    byte[] byteArry ;

    TextView memory_size, memory_free, memory_used,
             internal_size, internal_free, internal_used,
            external_size, external_free, external_used,
             storage_size, partition_size1, partition_size2, partition_size3, partition_size4, partition_size5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu);

        cpu_all = findViewById(R.id.cpu_all);
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
    }
}
