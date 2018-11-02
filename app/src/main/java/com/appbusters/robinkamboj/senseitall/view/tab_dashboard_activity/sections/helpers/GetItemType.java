package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.helpers;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GYROSCOPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_HEART_RATE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LIGHT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LINEAR_ACCELERATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MAGNETIC_FIELD;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MOTION_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PRESSURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_RELATIVE_HUMIDITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ROTATION_VECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STATIONARY_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_COUNTER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_TEMPERATURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SENSORS;

public class GetItemType {

    private String name;

    public GetItemType(String name) {
        this.name = name;
    }

    public int getItemType() {
        switch (name) {
            case SENSOR_LIGHT:
            case SENSOR_PROXIMITY:
            case SENSOR_TEMPERATURE:
            case SENSOR_PRESSURE:
            case SENSOR_ACCELEROMETER:
            case SENSOR_RELATIVE_HUMIDITY:
            case SENSOR_GYROSCOPE:
            case SENSOR_GRAVITY:
            case SENSOR_LINEAR_ACCELERATION:
            case SENSOR_ROTATION_VECTOR:
            case SENSOR_STEP_COUNTER:
            case SENSOR_STEP_DETECTOR:
            case SENSOR_MOTION_DETECTOR:
            case SENSOR_STATIONARY_DETECTOR:
            case SENSOR_MAGNETIC_FIELD:
            case SENSOR_HEART_RATE: {
                return TYPE_SENSORS;
            }
        }
        return TYPE_SENSORS;
    }
}
