package com.appbusters.robinkamboj.senseitall.view.detail_activity.information.ram;


import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import java.text.DecimalFormat;
import java.util.Locale;

import butterknife.ButterKnife;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class RamFragment extends FeatureFragment implements RamInterface {

    private ActivityManager.MemoryInfo memoryInfo;

    public RamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ram, container, false);
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
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
        memoryInfo = new ActivityManager.MemoryInfo();

        if(activityManager == null) return;
        activityManager.getMemoryInfo(memoryInfo);
    }

    @Override
    public void initializeBasicInformation() {
        if(memoryInfo == null) return;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        addToDetailsList(
                sensorDetails,
                "Percent Memory Available",
                decimalFormat.format((float) memoryInfo.availMem * 100/memoryInfo.totalMem) + " %"
        );
        addToDetailsList(sensorDetails, "Available Memory", formatSize(memoryInfo.availMem));
        addToDetailsList(sensorDetails, "Total Memory (excluding system used memory)", formatSize(memoryInfo.totalMem));
        addToDetailsList(sensorDetails, "Memory Threshold",  formatSize(memoryInfo.threshold));
        addToDetailsList(
                sensorDetails,
                "Is Low Memory?",
                String.format(Locale.ENGLISH, "%b", memoryInfo.lowMemory)
        );
        addToDetailsList(
                sensorDetails,
                "Description Count",
                String.format(Locale.ENGLISH, "%d", memoryInfo.describeContents())
        );
    }

    public String formatSize(double size) {

        size = size/(1024.0*1024.0*1024);

        return new DecimalFormat("#.##").format(size) + " GB";
    }
}
