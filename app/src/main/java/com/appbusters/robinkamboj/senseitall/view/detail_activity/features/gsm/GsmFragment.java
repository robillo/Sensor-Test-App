package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.gsm;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GsmFragment extends FeatureFragment implements GsmInterface {

    private TelephonyManager telephonyManager;

    public GsmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_gsm, container, false);
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
        if(getActivity() != null)
            telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void initializeBasicInformation() {
        if(telephonyManager == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            addToDetailsList(sensorDetails, "IMEI", telephonyManager.getImei());
            addToDetailsList(sensorDetails, "Network Specifier", telephonyManager.getNetworkSpecifier());
            addToDetailsList(sensorDetails, "Voice Mail Package Name", telephonyManager.getVisualVoicemailPackageName());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addToDetailsList(sensorDetails, "Voice Network Type", String.valueOf(telephonyManager.getVoiceNetworkType()));
            addToDetailsList(sensorDetails, "Phone Count", String.valueOf(telephonyManager.getPhoneCount()));
        }
        addToDetailsList(sensorDetails, "Device Software Version", telephonyManager.getDeviceSoftwareVersion());
        addToDetailsList(sensorDetails, "MMS UA Profile URL", telephonyManager.getMmsUAProfUrl());
        addToDetailsList(sensorDetails, "MMS User Agent", telephonyManager.getMmsUserAgent());
        addToDetailsList(sensorDetails, "Network Country ISO", telephonyManager.getNetworkCountryIso());
        addToDetailsList(sensorDetails, "Network Operator", telephonyManager.getNetworkOperator());
        addToDetailsList(sensorDetails, "Network Operator Name", telephonyManager.getNetworkOperatorName());
        addToDetailsList(sensorDetails, "Network Type", String.valueOf(telephonyManager.getNetworkType()));
        addToDetailsList(sensorDetails, "Sim Country ISO", telephonyManager.getSimCountryIso());
        addToDetailsList(sensorDetails, "Sim State", String.valueOf(telephonyManager.getSimState()));
        addToDetailsList(sensorDetails, "Sim Operator Name", telephonyManager.getSimOperatorName());
        addToDetailsList(sensorDetails, "Voice Mail Alpha Tag", telephonyManager.getVoiceMailAlphaTag());
        addToDetailsList(sensorDetails, "Data State", String.valueOf(telephonyManager.getDataState()));
        addToDetailsList(sensorDetails, "Data Activity", String.valueOf(telephonyManager.getDataActivity()));
        addToDetailsList(sensorDetails, "Phone Type", String.valueOf(telephonyManager.getPhoneType()));
    }
}


