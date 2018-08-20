package com.appbusters.robinkamboj.senseitall.view.detail_activity.information.storage;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.FeatureFragment;

import java.io.File;

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
        addToDetailsList(sensorDetails, "Free Internal Storage", getAvailableInternalMemorySize());
        addToDetailsList(sensorDetails, "Total Internal Storage", getTotalInternalMemorySize());
        addToDetailsList(sensorDetails, "Is SD Card Mounted", isSdCardOnDevice(getActivity()) + "");
        addToDetailsList(sensorDetails, "Free External Storage", getAvailableExternalMemorySize());
        addToDetailsList(sensorDetails, "Total External Storage", getTotalExternalMemorySize());
    }

    public static boolean isSdCardOnDevice(Context context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        if (storages.length > 1 && storages[0] != null && storages[1] != null)
            return true;
        else
            return false;
    }

    public boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public String getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return formatSize(availableBlocks * blockSize);
    }

    public String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return formatSize(totalBlocks * blockSize);
    }

    public String getAvailableExternalMemorySize() {
        if (isSdCardOnDevice(getActivity())) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long availableBlocks = stat.getAvailableBlocksLong();
            return formatSize(availableBlocks * blockSize);
        } else {
            return "no SD card detected";
        }
    }

    public String getTotalExternalMemorySize() {
        if (isSdCardOnDevice(getActivity())) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long totalBlocks = stat.getBlockCountLong();
            return formatSize(totalBlocks * blockSize);
        } else {
            return "no SD card detected";
        }
    }

    public String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = "KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MB";
                size /= 1024;
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }

        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }
}
