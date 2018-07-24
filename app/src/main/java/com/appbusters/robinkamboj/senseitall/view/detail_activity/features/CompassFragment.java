package com.appbusters.robinkamboj.senseitall.view.detail_activity.features;


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
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.screen_test.CompassInterface;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompassFragment extends FeatureFragment implements CompassInterface {

    public CompassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_compass, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);
        initializeSensor();
//        if(sensor == null) {
//            Toast.makeText(getActivity(), "Failed to load sensor.", Toast.LENGTH_SHORT).show();
//            if(getActivity() != null) getActivity().onBackPressed();
//        }
//        else {
//            showSensorDetails();
//        }

        hideGoToTestIfNoTest();

        setupAbout();
    }

    @Override
    public void initializeSensor() {

    }

    @Override
    public void initializeBasicInformation() {

    }
}
