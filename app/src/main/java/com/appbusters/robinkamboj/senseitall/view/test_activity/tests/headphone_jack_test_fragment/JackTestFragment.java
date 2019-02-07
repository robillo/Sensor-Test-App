package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.headphone_jack_test_fragment;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class JackTestFragment extends Fragment implements JackTestInterface {

    @BindView(R.id.jack_text_status)
    TextView jackTextStatus;

    @BindView(R.id.jack_image)
    ImageView jackImage;

    private ReceiverAudio receiver;

    public JackTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_jack_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        if(getActivity() == null) return;

        Glide.with(getActivity()).load(R.drawable.headphone_jack).into(jackImage);
        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        String state;
        if(audioManager == null) return;
        //noinspection deprecation
        if(audioManager.isWiredHeadsetOn()) {
            state = "STATE: PLUGGED";
            jackImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.green_shade_three));
        }
        else {
            state = "STATE: UNPLUGGED";
            jackImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_shade_four));
        }
        jackTextStatus.setText(state);

        initialize();
    }

    @Override
    public void initialize() {
        if (getActivity() == null) return;

        receiver = new ReceiverAudio(this);

        IntentFilter receiveFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);

        if(getActivity() != null)
            getActivity().registerReceiver(receiver, receiveFilter);
    }

    @Override
    public void onPause() {
        if (getActivity() != null && receiver != null) {
            try {getActivity().unregisterReceiver(receiver);}
            catch (IllegalArgumentException ignored) {}
        }

        super.onPause();
    }

    @Override public void onResume() {
        if(getActivity() != null && receiver == null) {
            IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
            getActivity().registerReceiver(receiver, filter);
        }

        super.onResume();
    }
}
