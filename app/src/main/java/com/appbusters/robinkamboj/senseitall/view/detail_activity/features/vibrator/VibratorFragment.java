package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.vibrator;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class VibratorFragment extends FeatureFragment implements VibratorInterface {

    private Vibrator vibrator;

    public VibratorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vibrator, container, false);
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

        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void initializeBasicInformation() {
        if(vibrator == null) return;

        addToDetailsList(sensorDetails, "Has Vibrator", String.valueOf(vibrator.hasVibrator()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            addToDetailsList(sensorDetails, "Has Amplitude Control", String.valueOf(vibrator.hasAmplitudeControl()));
        }
    }
}
