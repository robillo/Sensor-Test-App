package com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.light_sensor;


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
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.proximity_sensor.ProximityInterface;

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
public class LightFragment extends SensorFragment implements LightInterface {


    public LightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_light, container, false);
        setup(v);
        return v;
    }

    @Override
    public void initializeSensor() {
        SensorManager sensorManager = null;

        if(getActivity() != null)
            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager != null)
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void initializeBasicInformation() {
        addToDetailsList(sensorDetails, "Max Sunlight Luminance", String.valueOf(SensorManager.LIGHT_SUNLIGHT_MAX));
        addToDetailsList(sensorDetails, "Sunlight Luminance", String.valueOf(SensorManager.LIGHT_SUNLIGHT));
        addToDetailsList(sensorDetails, "Luminance In Shade", String.valueOf(SensorManager.LIGHT_SHADE));
        addToDetailsList(sensorDetails, "Luminance Under Overcast Sky", String.valueOf(SensorManager.LIGHT_OVERCAST));
        addToDetailsList(sensorDetails, "Luminance Under Cloudy Sky", String.valueOf(SensorManager.LIGHT_CLOUDY));
        addToDetailsList(sensorDetails, "Luminance At Sunrise", String.valueOf(SensorManager.LIGHT_SUNRISE));
        addToDetailsList(sensorDetails, "Luminance During Full Moon", String.valueOf(SensorManager.LIGHT_FULLMOON));
        addToDetailsList(sensorDetails, "Luminance During No Moon", String.valueOf(SensorManager.LIGHT_NO_MOON));

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
