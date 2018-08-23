package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.mic;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MicFragment extends FeatureFragment implements MicInterface {

    private AudioManager audioManager;
    private MediaRecorder mediaRecorder;

    public MicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mic, container, false);
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
        mediaRecorder = new MediaRecorder();

        if(getActivity() == null) return;
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void initializeBasicInformation() {
        addToDetailsList(sensorDetails, "Max Amplitude", String.valueOf(mediaRecorder.getMaxAmplitude()));

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

