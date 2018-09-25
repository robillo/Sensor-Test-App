package com.appbusters.robinkamboj.senseitall.view.detail_activity.information.ram;


import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;

import java.text.DecimalFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.ACTIVITY_SERVICE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AVAILABLE_MEMORY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DESCRIPTION_COUNT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_LOW_MEMORY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MEMORY_THRESHOLD;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PERCENT_AVAILABLE_MEMORY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TOTAL_MEMORY;

/**
 * A simple {@link Fragment} subclass.
 */
public class RamFragment extends FeatureFragment implements RamInterface {

    @BindView(R.id.ram_progress)
    ProgressBar ramProgress;

    @BindView(R.id.ram_used)
    TextView ramUsed;

    private double availableMem, totalMem;

    private ActivityManager.MemoryInfo memoryInfo;

    public RamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
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
    public void setProgress() {
        ramProgress.setMax((int) totalMem);
        ramProgress.setProgress((int) (totalMem - availableMem));

        ramUsed.setText(String.format(
                "%s %s %s",
                getString(R.string.ram_used),
                new DecimalFormat("#.##").format((totalMem - availableMem) * 100 / totalMem),
                "%"
        ));
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
                PERCENT_AVAILABLE_MEMORY,
                decimalFormat.format((float) memoryInfo.availMem * 100/memoryInfo.totalMem) + " %"
        );
        addToDetailsList(sensorDetails, AVAILABLE_MEMORY, formatSize(AVAILABLE_MEMORY, memoryInfo.availMem));
        addToDetailsList(sensorDetails, TOTAL_MEMORY, formatSize(TOTAL_MEMORY, memoryInfo.totalMem));
        addToDetailsList(sensorDetails, MEMORY_THRESHOLD,  formatSize(MEMORY_THRESHOLD, memoryInfo.threshold));
        addToDetailsList(
                sensorDetails,
                IS_LOW_MEMORY,
                String.format(Locale.ENGLISH, "%b", memoryInfo.lowMemory)
        );
        addToDetailsList(
                sensorDetails,
                DESCRIPTION_COUNT,
                String.format(Locale.ENGLISH, "%d", memoryInfo.describeContents())
        );

        setProgress();
    }

    public String formatSize(String memType, double size) {

        size = size/(1024.0*1024.0*1024);

        switch (memType) {
            case AVAILABLE_MEMORY: {
                availableMem = size;
                break;
            }
            case TOTAL_MEMORY: {
                totalMem = size;
                break;
            }
        }

        return new DecimalFormat("#.##").format(size) + " GB";
    }
}
