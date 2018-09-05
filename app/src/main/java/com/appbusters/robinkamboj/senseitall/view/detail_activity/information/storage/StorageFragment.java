package com.appbusters.robinkamboj.senseitall.view.detail_activity.information.storage;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StorageFragment extends FeatureFragment implements StorageInterface {

    long freeBytesInternal;
    long freeBytesExternal;

    public StorageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_storage, container, false);
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
        freeBytesInternal = new File(getActivity().getFilesDir().getAbsoluteFile().toString()).getFreeSpace();
        File file = getActivity().getExternalFilesDir(null);
        if(file != null) freeBytesExternal = new File(file.toString()).getFreeSpace();
    }

    @Override
    public void initializeBasicInformation() {

        addToDetailsList(sensorDetails, "Free Internal Storage", getFreeInternalMemorySize());
        addToDetailsList(sensorDetails, "Total Internal Storage (excluding system used memory)", getTotalInternalMemorySize());
        addToDetailsList(sensorDetails, "Is SD Card Mounted", isSdCardOnDevice(getActivity()) + "");
        addToDetailsList(sensorDetails, "Free External Storage", getFreeExternalMemorySize());
        addToDetailsList(sensorDetails, "Total External Storage (excluding card specific memory)", getTotalExternalMemorySize());
    }

    public static boolean isSdCardOnDevice(Context context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        return storages.length > 1 && storages[0] != null && storages[1] != null;
    }

    public String getFreeInternalMemorySize() {
        File path = Environment.getDataDirectory();
        return formatSize(path.getFreeSpace());
    }

    public String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        return formatSize(path.getTotalSpace());
    }

    public String getFreeExternalMemorySize() {
        if (isSdCardOnDevice(getActivity())) {
            if(getActivity() == null) return null;
            File[] storages = ContextCompat.getExternalFilesDirs(getActivity(), null);
            File path = storages[1];
            return formatSize(path.getFreeSpace());
        } else {
            return "no SD card detected";
        }
    }

    public String getTotalExternalMemorySize() {
        if (isSdCardOnDevice(getActivity())) {
            if(getActivity() == null) return null;
            File[] storages = ContextCompat.getExternalFilesDirs(getActivity(), null);
            File path = storages[1];
            return formatSize(path.getTotalSpace());
        } else {
            return "no SD card detected";
        }
    }

    public String formatSize(double size) {

        size = size/(1024.0*1024.0*1024.0);

        return new DecimalFormat("#.##").format(size) + " GB";
    }
}
