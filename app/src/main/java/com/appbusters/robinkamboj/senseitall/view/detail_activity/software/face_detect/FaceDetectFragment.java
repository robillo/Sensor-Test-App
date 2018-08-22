package com.appbusters.robinkamboj.senseitall.view.detail_activity.software.face_detect;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaceDetectFragment extends Fragment implements FaceDetectInterface {


    public FaceDetectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_face_detect, container, false);
    }

}
