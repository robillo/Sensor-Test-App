package com.appbusters.robinkamboj.senseitall.ui.detail_activity.android.version_pie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.ui.detail_activity.abstract_stuff.android.AndroidFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PieFragment extends AndroidFragment implements PieInterface {

    public PieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_pie, container, false);
        setupView(v);
        return v;
    }

}
