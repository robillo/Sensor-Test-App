package com.appbusters.robinkamboj.senseitall.view.detail_activity.features;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.FeatureFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.screen_test.CompassInterface;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompassFragment extends FeatureFragment implements CompassInterface {

    private Sensor sensor;

    public CompassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_compass, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        hideGoToTestIfNoTest();

        setupAbout();

        showSensorDetails();
    }

    @Override
    public void initializeSensor() {
        SensorManager sensorManager = null;

        if(getActivity() != null)
            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager != null)
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void initializeBasicInformation() {

        addToDetailsList(sensorDetails, "Standard Gravity", String.valueOf(SensorManager.STANDARD_GRAVITY));

        addToDetailsList(sensorDetails, "Vendor", sensor.getVendor());
        addToDetailsList(sensorDetails, "Resolution", String.valueOf(sensor.getResolution()));
        addToDetailsList(sensorDetails, "Minimum Delay", String.valueOf(sensor.getMinDelay()));
        addToDetailsList(sensorDetails, "Maximum Delay", String.valueOf(sensor.getMaxDelay()));
        addToDetailsList(sensorDetails, "Power", String.valueOf(sensor.getPower()));
        addToDetailsList(sensorDetails, "Maximum Range", String.valueOf(sensor.getMaximumRange()));
        addToDetailsList(sensorDetails, "Version", String.valueOf(sensor.getVersion()));
        addToDetailsList(sensorDetails, "Is Wake Up Sensor", String.valueOf(sensor.isWakeUpSensor()));
        addToDetailsList(sensorDetails, "Reporting Mode", String.valueOf(sensor.getReportingMode()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            addToDetailsList(sensorDetails, "Is Dynamic Sensor", String.valueOf(sensor.isDynamicSensor()));
    }
}
