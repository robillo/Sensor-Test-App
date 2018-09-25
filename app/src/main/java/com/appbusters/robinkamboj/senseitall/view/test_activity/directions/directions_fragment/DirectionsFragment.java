package com.appbusters.robinkamboj.senseitall.view.test_activity.directions.directions_fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectionsFragment extends Fragment implements DirectionsInterface {

    public GenericData intentData = new GenericData();
    public String recyclerName;

    @BindView(R.id.test_direction)
    TextView testDirections;

    @BindView(R.id.test_hint)
    TextView testHint;

    public DirectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
        View v = inflater.inflate(R.layout.fragment_directions, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        if(getActivity() != null) {
            recyclerName = ((TestActivity) getActivity()).recyclerName;
            intentData = ((TestActivity) getActivity()).intentData;
        }

        setDataForSensor();
    }

    @Override
    public void setDataForSensor() {
        testDirections.setText(AppConstants.sensorMapDirections.get(intentData.getName()).toLowerCase());
        testHint.setText(AppConstants.sensorMapHints.get(intentData.getName()).toLowerCase());
    }

    @OnClick(R.id.start_test)
    public void setStartTest() {
        if(getActivity() != null) ((TestActivity) getActivity()).setTestFragment();
    }
}
