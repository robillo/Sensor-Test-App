package com.appbusters.robinkamboj.senseitall.ui.detail_activity.features.cpu;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.ui.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("FieldCanBeLocal")
public class CpuFragment extends FeatureFragment implements CpuInterface {

    private String[] DATA = {"/system/bin/cat", "/proc/cpuinfo", "/proc/partitions"};
    private InputStream inputStream;
    private ProcessBuilder processBuilder;
    private Process process;
    private byte[] byteArray;

    public CpuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_cpu, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);
        initializeSensor();

        hideGoToTestIfNoTest();

        setupAbout();

        showSensorDetails();
    }

    @Override
    public void initializeSensor() {
        byteArray = new byte[1024];
    }

    @Override
    public void initializeBasicInformation() {
        try{
            processBuilder = new ProcessBuilder(DATA);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            while(inputStream.read(byteArray) != -1){
                addToDetailsList(sensorDetails, "", new String(byteArray));
            }
            inputStream.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}


