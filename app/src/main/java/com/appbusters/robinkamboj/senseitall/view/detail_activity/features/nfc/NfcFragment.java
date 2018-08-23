package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.nfc;


import android.nfc.NfcAdapter;
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
public class NfcFragment extends FeatureFragment implements NfcInterface {

    private NfcAdapter nfcAdapter;

    public NfcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nfc, container, false);
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
        nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
    }

    @Override
    public void initializeBasicInformation() {
        if(nfcAdapter == null) return;

        addToDetailsList(sensorDetails, "Is Enabled", String.valueOf(nfcAdapter.isEnabled()));
        addToDetailsList(sensorDetails, "Is NDEF Push Enabled", String.valueOf(nfcAdapter.isNdefPushEnabled()));
    }
}


