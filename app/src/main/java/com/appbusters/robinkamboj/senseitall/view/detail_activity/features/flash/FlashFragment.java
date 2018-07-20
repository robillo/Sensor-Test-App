package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.flash;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class FlashFragment extends FeatureFragment implements FlashFragmentInterface {


    public FlashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_flash, container, false);
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
        if (getActivity() != null)
            about.setText(AppConstants.sensorMapAbout.get(((DetailActivity) getActivity()).intentData.getName()));
    }

    @Override
    public void initializeSensor() {

    }

    @Override
    public void initializeBasicInformation() {

    }
}
