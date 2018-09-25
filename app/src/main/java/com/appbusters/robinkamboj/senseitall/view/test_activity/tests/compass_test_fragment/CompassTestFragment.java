package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.compass_test_fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.others.Compass;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompassTestFragment extends Fragment implements CompassTestInterface {

    @BindView(R.id.compass_dial)
    ImageView compassDial;

    @BindView(R.id.compass_hands)
    ImageView compassHands;

    private Compass compass;

    public CompassTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
        View v = inflater.inflate(R.layout.fragment_compass_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        if(getActivity() == null) return;

        compass = new Compass(getActivity());
        compass.arrowView = compassHands;

        compass.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        compass.start();
    }
}
