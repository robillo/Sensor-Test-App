package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.android_os;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import java.util.Arrays;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OsFragment extends FeatureFragment implements OsInterface {


    public OsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_os, container, false);
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
    public void initializeSensor() {}

    @Override
    public void initializeBasicInformation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addToDetailsList(sensorDetails, "Base OS", Build.VERSION.BASE_OS);
            addToDetailsList(sensorDetails, "Security Patch", Build.VERSION.SECURITY_PATCH);
        }
        addToDetailsList(sensorDetails, "ID", Build.ID);
        addToDetailsList(sensorDetails, "Display", Build.DISPLAY);
        addToDetailsList(sensorDetails, "Product", Build.PRODUCT);
        addToDetailsList(sensorDetails, "Device", Build.DEVICE);
        addToDetailsList(sensorDetails, "Board", Build.BOARD);
        addToDetailsList(sensorDetails, "Manufacturer", Build.MANUFACTURER);
        addToDetailsList(sensorDetails, "Brand", Build.BRAND);
        addToDetailsList(sensorDetails, "Model", Build.MODEL);
        addToDetailsList(sensorDetails, "Bootloader", Build.BOOTLOADER);
        addToDetailsList(sensorDetails, "Hardware", Build.HARDWARE);
        addToDetailsList(sensorDetails, "User", Build.USER);
        addToDetailsList(sensorDetails, "Codename", Build.VERSION.CODENAME);
        addToDetailsList(sensorDetails, "Incremental", Build.VERSION.INCREMENTAL);
        addToDetailsList(sensorDetails, "Release", Build.VERSION.RELEASE);
        addToDetailsList(sensorDetails, "SDK Integer", String.valueOf(Build.VERSION.SDK_INT));
        addToDetailsList(sensorDetails, "Supported ABIs", Arrays.toString(Build.SUPPORTED_ABIS));
        addToDetailsList(sensorDetails, "Supported 32 Bit ABIs", Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
        addToDetailsList(sensorDetails, "Supported 64 Bit ABIs", Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));
    }
}

