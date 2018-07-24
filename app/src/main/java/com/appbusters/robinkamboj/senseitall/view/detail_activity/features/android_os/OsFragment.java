package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.android_os;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.FeatureFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.av_test.JackInterface;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OsFragment extends FeatureFragment implements OsInterface {


    public OsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_os, container, false);
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

    }

    @Override
    public void initializeBasicInformation() {

    }
}

