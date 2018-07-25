package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.fingerprint;


import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.FeatureFragment;

import butterknife.ButterKnife;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FingerprintFragment extends FeatureFragment implements FingerprintInterface {

    private KeyguardManager keyguardManager;
    private FingerprintManager fingerprintManager;

    public FingerprintFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fingerprint, container, false);
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

    @SuppressLint("NewApi")
    @Override
    public void initializeSensor() {
        if(getActivity() == null) return;
        keyguardManager = (KeyguardManager) getActivity().getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getActivity().getSystemService(FINGERPRINT_SERVICE);
    }

    @Override
    public void initializeBasicInformation() {
        if(keyguardManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                addToDetailsList(sensorDetails, "Is Keyguard Locked", String.valueOf(keyguardManager.isKeyguardLocked()));
                addToDetailsList(sensorDetails, "Is Keyguard Secure", String.valueOf(keyguardManager.isKeyguardSecure()));
                addToDetailsList(sensorDetails, "Is Device Locked", String.valueOf(keyguardManager.isDeviceLocked()));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                addToDetailsList(sensorDetails, "Is Device Secure", String.valueOf(keyguardManager.isDeviceSecure()));
            }
        }

        if(fingerprintManager == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addToDetailsList(sensorDetails, "Is Hardware Detected", String.valueOf(fingerprintManager.isHardwareDetected()));
            addToDetailsList(sensorDetails, "Has Enrolled Fingerprint", String.valueOf(fingerprintManager.hasEnrolledFingerprints()));
        }

    }
}
