package com.appbusters.robinkamboj.senseitall.ui.detail_activity.features.wifi;


import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.ui.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WifiFragment extends FeatureFragment implements WifiInterface {

    private WifiManager wifiManager;

    public WifiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_wifi, container, false);
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

        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    public void initializeBasicInformation() {
        if(wifiManager != null) {
            addToDetailsList(sensorDetails, "Wi-Fi State", String.valueOf(wifiManager.getWifiState()));
            addToDetailsList(sensorDetails, "Is 5GHz Band Supported", String.valueOf(wifiManager.is5GHzBandSupported()));
            addToDetailsList(sensorDetails, "Is Device-To-AP RTT Supported", String.valueOf(wifiManager.isDeviceToApRttSupported()));
            addToDetailsList(sensorDetails, "Is Enhanced Power Reportig Supported", String.valueOf(wifiManager.isEnhancedPowerReportingSupported()));
            addToDetailsList(sensorDetails, "Is P2P Supported", String.valueOf(wifiManager.isP2pSupported()));
            addToDetailsList(sensorDetails, "Is Preferred Network Offload Sopported", String.valueOf(wifiManager.isPreferredNetworkOffloadSupported()));
            addToDetailsList(sensorDetails, "Is Scan Always Available", String.valueOf(wifiManager.isScanAlwaysAvailable()));
            addToDetailsList(sensorDetails, "Is TDLS Supported", String.valueOf(wifiManager.isTdlsSupported()));
            addToDetailsList(sensorDetails, "Is Wi-Fi Enabled", String.valueOf(wifiManager.isWifiEnabled()));
        }
    }
}
