package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.wifi_direct;


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
public class WifiDirectFragment extends FeatureFragment implements WifiDirectInterface {

//    private WifiP2pManager wifiManager;

    public WifiDirectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
        View v = inflater.inflate(R.layout.fragment_wifi_direct, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);
        initializeSensor();

        hideGoToTestIfNoTest();

        setupAbout();
    }

    @Override
    public void initializeSensor() {
//        if(getActivity() == null) return;

//        wifiManager = (WifiP2pManager) getActivity().getSystemService(Context.WIFI_P2P_SERVICE);
    }

    @Override
    public void initializeBasicInformation() {
//        if(wifiManager == null) return;

    }
}


