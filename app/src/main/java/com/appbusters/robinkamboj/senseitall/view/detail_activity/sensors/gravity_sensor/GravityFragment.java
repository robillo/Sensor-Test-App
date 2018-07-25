package com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.gravity_sensor;


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
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.SensorFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GravityFragment extends SensorFragment implements GravityInterface {


    public GravityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gravity, container, false);
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
        addToDetailsList(sensorDetails, "Standard Gravity", String.valueOf(SensorManager.STANDARD_GRAVITY));
        addToDetailsList(sensorDetails, "Sun's Gravity", String.valueOf(SensorManager.GRAVITY_SUN));
        addToDetailsList(sensorDetails, "Mercury's Gravity", String.valueOf(SensorManager.GRAVITY_MERCURY));
        addToDetailsList(sensorDetails, "Venus's Gravity", String.valueOf(SensorManager.GRAVITY_VENUS));
        addToDetailsList(sensorDetails, "Earth's Gravity", String.valueOf(SensorManager.GRAVITY_EARTH));
        addToDetailsList(sensorDetails, "Mars's Gravity", String.valueOf(SensorManager.GRAVITY_MARS));
        addToDetailsList(sensorDetails, "Jupiter's Gravity", String.valueOf(SensorManager.GRAVITY_JUPITER));
        addToDetailsList(sensorDetails, "Saturn's Gravity", String.valueOf(SensorManager.GRAVITY_SATURN));
        addToDetailsList(sensorDetails, "Uranus's Gravity", String.valueOf(SensorManager.GRAVITY_URANUS));
        addToDetailsList(sensorDetails, "Neptune's Gravity", String.valueOf(SensorManager.GRAVITY_NEPTUNE));
        addToDetailsList(sensorDetails, "Pluto's Gravity", String.valueOf(SensorManager.GRAVITY_PLUTO));
        addToDetailsList(sensorDetails, "Moon's Gravity", String.valueOf(SensorManager.GRAVITY_MOON));

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
