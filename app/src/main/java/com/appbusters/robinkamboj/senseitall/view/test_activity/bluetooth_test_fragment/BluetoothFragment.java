package com.appbusters.robinkamboj.senseitall.view.test_activity.bluetooth_test_fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BluetoothFragment extends Fragment implements BluetoothTestInterface {


    public BluetoothFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bluetooth2, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);
    }
}
