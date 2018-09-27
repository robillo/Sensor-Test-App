package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment;

import android.view.View;

import com.appbusters.robinkamboj.senseitall.model.recycler.TinyInfo;

public interface ToolsInterface {

    void setup(View v);

    void setImageToolsAdapter();

    void setEverydayToolsAdapter();

    void setQuickSettingsRecycler();

    void checkQuickSettingsStatus();

    void checkEachQuickSetting(String info);

}
