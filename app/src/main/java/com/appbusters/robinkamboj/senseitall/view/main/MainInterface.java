package com.appbusters.robinkamboj.senseitall.view.main;

public interface MainInterface {

    void setup();

    void inflateData();

    void changeStatusBarColor();

    void setHeaderTextAndRv();

    void turnOnHighlight(int type);

    void checkIfAllPermissionsGiven();

    //adapter related stuff

    void initializeAdapter();

    void fillGenericDataForSelected(int type);
}
