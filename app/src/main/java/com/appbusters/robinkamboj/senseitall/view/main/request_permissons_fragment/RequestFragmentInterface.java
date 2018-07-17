package com.appbusters.robinkamboj.senseitall.view.main.request_permissons_fragment;

import android.view.View;

import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;

import java.util.List;

public interface RequestFragmentInterface {

    void setup(View v);

    void setStatusBarColor();

    void showRecycler(List<PermissionsItem> list);

    void updatePermissionsListAndRecycler(List<PermissionsItem> permissionsItems);
}
