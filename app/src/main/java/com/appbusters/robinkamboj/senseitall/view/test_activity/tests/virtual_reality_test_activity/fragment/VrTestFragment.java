package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.virtual_reality_test_activity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.virtual_reality_test_activity.activity.VirtualRealityTestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VrTestFragment extends Fragment {


    @BindView(R.id.start_test)
    TextView startTest;


    public VrTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vr_test, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.start_test)
    public void setStartTest() {
        if(getActivity() == null) return;
        startActivity(new Intent(getActivity(), VirtualRealityTestActivity.class));
    }
}
