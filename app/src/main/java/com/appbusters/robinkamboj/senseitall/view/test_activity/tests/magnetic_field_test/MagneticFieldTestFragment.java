package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.magnetic_field_test;


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
public class MagneticFieldTestFragment extends Fragment implements MagneticFieldTestInterface {


    public MagneticFieldTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_magnetic_field_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {

    }
}
