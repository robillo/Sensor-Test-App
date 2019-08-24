package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.multi_touch_fragment;


import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.MultiTouchCanvas;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultiTestFragment extends Fragment
        implements MultiTestInterface, MultiTouchCanvas.MultiTouchStatusListener {


    public MultiTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_multi_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ((MultiTouchCanvas) v.findViewById(R.id.multiTouchView)).setStatusListener(this);
    }

    @Override
    public void onStatus(List<Point> pointerLocations, int numPoints) {

    }
}
