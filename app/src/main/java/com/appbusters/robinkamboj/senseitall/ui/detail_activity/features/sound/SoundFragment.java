package com.appbusters.robinkamboj.senseitall.ui.detail_activity.features.sound;


import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.ui.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoundFragment extends FeatureFragment implements SoundInterface {

    private AudioManager audioManager;

    public SoundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_sound, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);
        initializeSensor();

        hideGoToTestIfNoTest();

        setupAbout();

        showSensorDetails();
    }

    @Override
    public void initializeSensor() {
        if(getActivity() == null) return;

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void initializeBasicInformation() {
        if(audioManager == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addToDetailsList(sensorDetails, "Support Mic Near Ultrasound", String.valueOf(audioManager.getProperty(AudioManager.PROPERTY_SUPPORT_MIC_NEAR_ULTRASOUND)));
            addToDetailsList(sensorDetails, "Support Speaker Near Ultrasound", String.valueOf(audioManager.getProperty(AudioManager.PROPERTY_SUPPORT_SPEAKER_NEAR_ULTRASOUND)));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addToDetailsList(sensorDetails, "Support Audio Source Unprocessed", String.valueOf(audioManager.getProperty(AudioManager.PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED)));
        }
        addToDetailsList(sensorDetails, "Current Mode", String.valueOf(audioManager.getMode()));
        addToDetailsList(sensorDetails, "Current Stream Volume", String.valueOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)));
        addToDetailsList(sensorDetails, "Max Stream Volume", String.valueOf(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)));
        addToDetailsList(sensorDetails, "Output Frames Per Buffer", String.valueOf(audioManager.getProperty(AudioManager.PROPERTY_OUTPUT_FRAMES_PER_BUFFER)));
        addToDetailsList(sensorDetails, "Output Sample Rate", String.valueOf(audioManager.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE)));

    }
}
