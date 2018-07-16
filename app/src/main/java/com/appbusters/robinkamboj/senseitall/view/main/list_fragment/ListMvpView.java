package com.appbusters.robinkamboj.senseitall.view.main.list_fragment;

import android.view.View;

public interface ListMvpView {

    void setup(View v);

    void inflateData();

    void changeStatusBarColor();

    void setHeaderTextAndRv();

    void turnOnHighlight(int type);

    void checkIfAllPermissionsGiven();

    void checkForPresentSensors();

    void togglePermissionCardVisibility();

    //adapter related stuff

    void initializeAdapter();

    void fillGenericDataForSelected(int type);
}
