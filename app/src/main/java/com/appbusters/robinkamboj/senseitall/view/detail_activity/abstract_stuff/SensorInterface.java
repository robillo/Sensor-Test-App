package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff;

import android.view.View;

import com.appbusters.robinkamboj.senseitall.model.recycler.SensorDetail;

import java.util.List;

public interface SensorInterface {

    void setup(View v);

    void initializeSensor();

    void showSensorDetails();

    void initializeBasicInformation();

    void showBasicInformation();

    void hideGoToTestIfNoTest();

    void addToDetailsList(List<SensorDetail> sensorDetails, String key, String value);
}
