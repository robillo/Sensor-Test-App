package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.screen_test_fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agsw.FabricView.FabricView;
import com.appbusters.robinkamboj.senseitall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenTestFragment extends Fragment implements ScreenTestInterface {

    @BindView(R.id.fabric_view)
    FabricView fabricView;

    public ScreenTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_screen_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        fabricView.setBackgroundMode(FabricView.BACKGROUND_STYLE_NOTEBOOK_PAPER);
    }
}
