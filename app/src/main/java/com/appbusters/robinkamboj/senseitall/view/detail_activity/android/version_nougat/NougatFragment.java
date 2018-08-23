package com.appbusters.robinkamboj.senseitall.view.detail_activity.android.version_nougat;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.android.AndroidFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NougatFragment extends AndroidFragment implements NougatInterface {

    public NougatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nougat, container, false);
        setup(v);
        return v;
    }

}
