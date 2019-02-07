package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.midi;


import android.annotation.SuppressLint;
import android.content.Context;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import java.util.Arrays;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MidiFragment extends FeatureFragment implements MidiInterface {

    private MidiManager midiManager;

    public MidiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_midi, container, false);
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

    @SuppressLint("NewApi")
    @Override
    public void initializeSensor() {
        if(getActivity() == null) return;

        midiManager = (MidiManager) getActivity().getSystemService(Context.MIDI_SERVICE);
    }

    @SuppressLint("NewApi")
    @Override
    public void initializeBasicInformation() {
        if(midiManager == null) return;

        if(midiManager.getDevices().length == 0) {
            Toast.makeText(getActivity(), "No Midi Devices Info Found", Toast.LENGTH_LONG).show();
            return;
        }

        int count = 1;
        for(MidiDeviceInfo deviceInfo : midiManager.getDevices()) {
            addToDetailsList(sensorDetails, "Device " + String.valueOf(count), "");
            addToDetailsList(sensorDetails, "Device ID", String.valueOf(deviceInfo.getId()));
            addToDetailsList(sensorDetails, "Input Port Count", String.valueOf(deviceInfo.getInputPortCount()));
            addToDetailsList(sensorDetails, "Output Port Count", String.valueOf(deviceInfo.getOutputPortCount()));
            addToDetailsList(sensorDetails, "Ports", Arrays.toString(deviceInfo.getPorts()));
            addToDetailsList(sensorDetails, "Type", String.valueOf(deviceInfo.getType()));
        }
    }
}

