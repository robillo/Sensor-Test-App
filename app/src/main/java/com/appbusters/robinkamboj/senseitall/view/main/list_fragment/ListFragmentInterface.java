package com.appbusters.robinkamboj.senseitall.view.main.list_fragment;

import android.view.View;

import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;

import java.util.List;

public interface ListFragmentInterface {

    void setup(View v);

    void inflateData();

    void changeStatusBarColor();

    void setHeaderTextAndRv();

    void turnOnHighlight(int type);

    void checkIfAllPermissionsGiven();

    void checkForPresentSensors();

    void togglePermissionCardVisibility();

    List<PermissionsItem> getPermissionItemsList();

    void toggleToolbarVisibility(int type);

    //adapter related stuff

    void initializeAdapter();

    void fillGenericDataForSelected(int type);
}
