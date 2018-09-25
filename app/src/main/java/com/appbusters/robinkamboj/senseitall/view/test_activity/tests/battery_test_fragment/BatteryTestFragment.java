package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.battery_test_fragment;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.github.anastr.speedviewlib.TubeSpeedometer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BatteryTestFragment extends Fragment implements BatteryTestInterface {

    @BindView(R.id.tube_speedometer)
    TubeSpeedometer speedometer;

    @BindView(R.id.status_battery)
    TextView statusBatteryText;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    public BatteryTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
        View v = inflater.inflate(R.layout.fragment_battery_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initialize();
    }

    @Override
    public void initialize() {
        BatteryReceiver receiver = new BatteryReceiver(this);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        if(getActivity() != null) {
            try {
                getActivity().registerReceiver(receiver, intentFilter);
            }
            catch (Exception e) {
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "some error has occurred", 400);
                View view = snackbar.getView();
                TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }
}
