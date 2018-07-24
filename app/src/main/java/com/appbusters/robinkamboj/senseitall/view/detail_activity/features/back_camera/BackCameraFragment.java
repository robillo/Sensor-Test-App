package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.back_camera;


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
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.back_camera_test_fragment.BackCamTestInterface;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackCameraFragment extends FeatureFragment implements BackCamTestInterface {

    public BackCameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_back_camera, container, false);
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
        //initialise
    }

    @Override
    public void initializeBasicInformation() {
//        addToDetailsList(sensorDetails, "Vendor", sensor.getVendor());
    }
}
