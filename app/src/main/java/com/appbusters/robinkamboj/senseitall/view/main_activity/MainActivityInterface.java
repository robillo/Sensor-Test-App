package com.appbusters.robinkamboj.senseitall.view.main_activity;

import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;

import java.util.List;

public interface MainActivityInterface {

    void setup();

    void setListFragment();

    void setRequestFragment();

    List<PermissionsItem> getPermissionItemsList();

    void refreshPermissionsRecycler();

    void showSnackBar(String text);

}
