package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.battery;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
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
public class BatteryFragment extends FeatureFragment implements BatteryInterface {

    private BatteryManager batteryManager;
    private Intent batteryStatus;

    public BatteryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_battery, container, false);
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
        if(getActivity() != null) {
            batteryManager = (BatteryManager) getActivity().getSystemService(Context.BATTERY_SERVICE);

            IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            batteryStatus = getActivity().registerReceiver(null, filter);
        }
    }

    @Override
    public void initializeBasicInformation() {
        if(batteryManager == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addToDetailsList(sensorDetails, "Is Charging", String.valueOf(batteryManager.isCharging()));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            addToDetailsList(sensorDetails, "Battery Status", String.valueOf(
                    batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS
                    )));
        }
        addToDetailsList(sensorDetails, "Charge Counter", String.valueOf(
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER
                )));
        addToDetailsList(sensorDetails, "Current", String.valueOf(
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW
                )));
        addToDetailsList(sensorDetails, "Capacity", String.valueOf(
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY
                )));

        if(batteryStatus == null) return;

        addToDetailsList(sensorDetails, "Health",
                String.valueOf(batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, -1))
        );
        addToDetailsList(sensorDetails, "Level",
                String.valueOf(batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1))
        );
        addToDetailsList(sensorDetails, "Scale",
                String.valueOf(batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1))
        );
        addToDetailsList(sensorDetails, "Temperature",
                String.valueOf(batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1))
        );
        addToDetailsList(sensorDetails, "Voltage",
                String.valueOf(batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1))
        );
    }
}
