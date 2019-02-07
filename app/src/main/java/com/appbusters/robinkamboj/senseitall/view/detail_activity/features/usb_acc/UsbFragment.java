package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.usb_acc;


import android.content.Context;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsbFragment extends FeatureFragment implements UsbInterface {

    private UsbManager usbManager;

    public UsbFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_usb, container, false);
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
        if(getActivity() == null) return;

        usbManager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
    }

    @Override
    public void initializeBasicInformation() {
        if(usbManager == null) return;

        addToDetailsList(sensorDetails, "USB Accessories:", "");

        if(usbManager.getAccessoryList() == null || usbManager.getAccessoryList().length == 0) {
            Toast.makeText(getActivity(), "No USB Accessories Found", Toast.LENGTH_LONG).show();
            return;
        }

        int count = 1;
        for(UsbAccessory accessory : usbManager.getAccessoryList()) {
            addToDetailsList(sensorDetails, "Accessory " + count, "");
            addToDetailsList(sensorDetails, "Model", accessory.getModel());
            addToDetailsList(sensorDetails, "Description", accessory.getDescription());
            addToDetailsList(sensorDetails, "Manufacturer", accessory.getManufacturer());
            addToDetailsList(sensorDetails, "Serial", accessory.getSerial());
            addToDetailsList(sensorDetails, "Version", accessory.getVersion());
        }
    }
}


