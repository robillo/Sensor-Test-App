package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.barometer_test_fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarometerTestFragment extends Fragment implements BarometerTestInterface {


    public BarometerTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_barometer_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {

    }
}
