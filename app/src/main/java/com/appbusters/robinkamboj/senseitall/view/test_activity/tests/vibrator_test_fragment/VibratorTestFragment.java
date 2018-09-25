package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.vibrator_test_fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VibratorTestFragment extends Fragment implements VibratorTestInterface {

    @BindView(R.id.click_to_vibrate)
    TextView clickToVibrate;

    private Vibrator vibrator;

    public VibratorTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
        View v = inflater.inflate(R.layout.fragment_vibrator_test, container, false);
        setup(v);
        return  v;
    }


    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        if(getActivity() == null) return;

        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

    @OnClick(R.id.click_to_vibrate)
    public void setClickToVibrate() {
        if(vibrator != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else {
                vibrator.vibrate(500);
            }
    }
}
