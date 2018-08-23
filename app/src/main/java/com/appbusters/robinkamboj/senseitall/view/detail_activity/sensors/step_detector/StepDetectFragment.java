package com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.step_detector;


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
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.SensorFragment;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_DYNAMIC_SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_WAKE_UP_SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MAXIMUM_DELAY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MAXIMUM_RANGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MINIMUM_DELAY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.POWER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REPORTING_MODE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RESOLUTION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VENDOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VERSION;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetectFragment extends SensorFragment implements StepDetectInterface {


    public StepDetectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_step_detect, container, false);
        setup(v);
        return v;
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
        addToDetailsList(sensorDetails, VENDOR, sensor.getVendor());
        addToDetailsList(sensorDetails, RESOLUTION, String.valueOf(sensor.getResolution()));
        addToDetailsList(sensorDetails, MINIMUM_DELAY, String.valueOf(sensor.getMinDelay()));
        addToDetailsList(sensorDetails, MAXIMUM_DELAY, String.valueOf(sensor.getMaxDelay()));
        addToDetailsList(sensorDetails, POWER, String.valueOf(sensor.getPower()));
        addToDetailsList(sensorDetails, MAXIMUM_RANGE, String.valueOf(sensor.getMaximumRange()));
        addToDetailsList(sensorDetails, VERSION, String.valueOf(sensor.getVersion()));
        addToDetailsList(sensorDetails, IS_WAKE_UP_SENSOR, String.valueOf(sensor.isWakeUpSensor()));
        addToDetailsList(sensorDetails, REPORTING_MODE, String.valueOf(sensor.getReportingMode()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            addToDetailsList(sensorDetails, IS_DYNAMIC_SENSOR, String.valueOf(sensor.isDynamicSensor()));
    }
}
