package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.bluetooth;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BluetoothFragment extends FeatureFragment implements BluetoothInterface {

    private BluetoothAdapter bluetoothAdapter;

    public BluetoothFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bluetooth, container, false);
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
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @SuppressLint("HardwareIds")
    @Override
    public void initializeBasicInformation() {
        if(bluetoothAdapter != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            addToDetailsList(sensorDetails, "Is LE 2M PHY Feature Supported", String.valueOf(bluetoothAdapter.isLe2MPhySupported()));
            addToDetailsList(sensorDetails, "Is LE CODED PHY Feature Supported", String.valueOf(bluetoothAdapter.isLeCodedPhySupported()));
            addToDetailsList(sensorDetails, "Is Extended Advertising Feature Supported", String.valueOf(bluetoothAdapter.isLeExtendedAdvertisingSupported()));
            addToDetailsList(sensorDetails, "Is Periodic Advertising Feature Supported", String.valueOf(bluetoothAdapter.isLePeriodicAdvertisingSupported()));
        }
            addToDetailsList(sensorDetails, "Name", bluetoothAdapter.getName());
            addToDetailsList(sensorDetails, "Address", bluetoothAdapter.getAddress());
            addToDetailsList(sensorDetails, "Bluetooth LE Scanner", String.valueOf(bluetoothAdapter.getBluetoothLeScanner()));
            addToDetailsList(sensorDetails, "Bluetooth LE Advertiser", String.valueOf(bluetoothAdapter.getBluetoothLeAdvertiser()));
            addToDetailsList(sensorDetails, "Is Enabled", String.valueOf(bluetoothAdapter.isEnabled()));
            addToDetailsList(sensorDetails, "Is Discovering", String.valueOf(bluetoothAdapter.isDiscovering()));
            addToDetailsList(sensorDetails, "Is Multiple Advertising Supported", String.valueOf(bluetoothAdapter.isMultipleAdvertisementSupported()));
            addToDetailsList(sensorDetails, "Is Offloaded Filtering Supported", String.valueOf(bluetoothAdapter.isOffloadedFilteringSupported()));
            addToDetailsList(sensorDetails, "Is Offloaded Scan Batching Supported", String.valueOf(bluetoothAdapter.isOffloadedScanBatchingSupported()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
