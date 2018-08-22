package com.appbusters.robinkamboj.senseitall.view.detail_activity.software.motion_detect;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AiMotionDetectFragment extends Fragment implements AiMotionDetectInterface {


    public AiMotionDetectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ai_motion_detect, container, false);
    }

}
