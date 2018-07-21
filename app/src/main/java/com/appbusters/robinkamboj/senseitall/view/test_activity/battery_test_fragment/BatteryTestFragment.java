package com.appbusters.robinkamboj.senseitall.view.test_activity.battery_test_fragment;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

    public BatteryTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        if(getActivity() != null)
            getActivity().registerReceiver(receiver, intentFilter);
    }
}
