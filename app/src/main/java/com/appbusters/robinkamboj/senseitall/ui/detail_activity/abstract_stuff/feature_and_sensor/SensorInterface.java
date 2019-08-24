package com.appbusters.robinkamboj.senseitall.ui.detail_activity.abstract_stuff.feature_and_sensor;

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

    void setupAbout();

    void setStatisticsAdapter();

    void addToDetailsList(List<SensorDetail> sensorDetails, String key, String value);
}
