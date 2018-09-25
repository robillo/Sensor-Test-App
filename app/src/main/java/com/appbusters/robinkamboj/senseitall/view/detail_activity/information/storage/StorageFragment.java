package com.appbusters.robinkamboj.senseitall.view.detail_activity.information.storage;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FREE_EXTERNAL_STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FREE_INTERNAL_STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_SD_CARD_MOUNTED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TOTAL_EXTERNAL_STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TOTAL_INTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class StorageFragment extends FeatureFragment implements StorageInterface {

    @BindView(R.id.internal_storage_used)
    TextView internalStorageUsed;

    @BindView(R.id.external_storage_used)
    TextView externalStorageUsed;

    @BindView(R.id.internal_storage_progress)
    ProgressBar internalProgress;

    @BindView(R.id.external_storage_progress)
    ProgressBar externalProgress;

    @BindView(R.id.card_internal_storage)
    CardView cardInternalStorage;

    @BindView(R.id.card_external_storage)
    CardView cardExternalStorage;

    private double freeInternal, totalInternal, freeExternal, totalExternal;

    public StorageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
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
    public void setInternalStorageProgress() {
        internalProgress.setMax((int) totalInternal);
        internalProgress.setProgress((int) (totalInternal - freeInternal));

        internalStorageUsed.setText(String.format(
                "%s %s %s",
                getString(R.string.internal_storage_used),
                new DecimalFormat("#.##").format((totalInternal - freeInternal) * 100/totalInternal),
                "%"
        ));
    }

    @Override
    public void setExternalStorageProgress() {
        externalProgress.setMax((int) totalExternal);
        externalProgress.setProgress((int) (totalExternal - freeExternal));

        externalStorageUsed.setText(String.format(
                "%s %s %s",
                getString(R.string.external_storage_used),
                new DecimalFormat("#.##").format((totalExternal - freeExternal) * 100/totalExternal),
                "%"
        ));
    }

    @Override
    public void initializeSensor() {

    }

    @Override
    public void initializeBasicInformation() {

        addToDetailsList(sensorDetails, FREE_INTERNAL_STORAGE, getFreeInternalMemorySize(FREE_INTERNAL_STORAGE));
        addToDetailsList(sensorDetails, TOTAL_INTERNAL_STORAGE, getTotalInternalMemorySize(TOTAL_INTERNAL_STORAGE));
        addToDetailsList(sensorDetails, IS_SD_CARD_MOUNTED, String.valueOf(isSdCardOnDevice(getActivity())));
        addToDetailsList(sensorDetails, FREE_EXTERNAL_STORAGE, getFreeExternalMemorySize(FREE_EXTERNAL_STORAGE));
        addToDetailsList(sensorDetails, TOTAL_EXTERNAL_STORAGE, getTotalExternalMemorySize(TOTAL_EXTERNAL_STORAGE));

        setInternalStorageProgress();

        if(isSdCardOnDevice(getActivity())) {
            setExternalStorageProgress();
        }
        else cardExternalStorage.setVisibility(View.GONE);
    }

    public static boolean isSdCardOnDevice(Context context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        return storages.length > 1 && storages[0] != null && storages[1] != null;
    }

    public String getFreeInternalMemorySize(String memType) {
        File path = Environment.getDataDirectory();
        return formatSize(memType, path.getFreeSpace());
    }

    public String getTotalInternalMemorySize(String memType) {
        File path = Environment.getDataDirectory();
        return formatSize(memType, path.getTotalSpace());
    }

    public String getFreeExternalMemorySize(String memType) {
        if (isSdCardOnDevice(getActivity())) {
            if(getActivity() == null) return null;
            File[] storages = ContextCompat.getExternalFilesDirs(getActivity(), null);
            File path = storages[1];
            return formatSize(memType, path.getFreeSpace());
        } else {
            return "no SD card detected";
        }
    }

    public String getTotalExternalMemorySize(String memType) {
        if (isSdCardOnDevice(getActivity())) {
            if(getActivity() == null) return null;
            File[] storages = ContextCompat.getExternalFilesDirs(getActivity(), null);
            File path = storages[1];
            return formatSize(memType, path.getTotalSpace());
        } else {
            return "no SD card detected";
        }
    }

    public String formatSize(String memType, double size) {

        size = size/(1024.0*1024.0*1024.0);

        switch (memType) {
            case FREE_INTERNAL_STORAGE: {
                freeInternal = size;
                break;
            }
            case TOTAL_INTERNAL_STORAGE: {
                totalInternal = size;
                break;
            }
            case FREE_EXTERNAL_STORAGE: {
                freeExternal = size;
                break;
            }
            case TOTAL_EXTERNAL_STORAGE: {
                totalExternal = size;
                break;
            }
        }

        return new DecimalFormat("#.##").format(size) + " GB";
    }
}
