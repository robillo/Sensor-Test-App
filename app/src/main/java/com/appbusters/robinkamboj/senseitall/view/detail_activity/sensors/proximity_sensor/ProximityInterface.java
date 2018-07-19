package com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.proximity_sensor;

import android.view.View;

import com.appbusters.robinkamboj.senseitall.model.recycler.SensorDetail;

import java.util.List;

public interface ProximityInterface {

    void setup(View v);

    void initializeSensor();

    void showSensorDetails();

    void initializeBasicInformation();

    void showBasicInformation();

    void addToDetailsList(List<SensorDetail> sensorDetails, String key, String value);
}
