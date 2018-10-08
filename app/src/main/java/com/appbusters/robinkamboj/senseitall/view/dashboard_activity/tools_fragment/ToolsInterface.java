package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment;

import android.view.View;

import com.appbusters.robinkamboj.senseitall.model.recycler.TinyInfo;

public interface ToolsInterface {

    void setup(View v);

    void registerReceivers();

    void setImageToolsAdapter();

    void setEverydayToolsAdapter();

    void setQuickSettingsRecycler();

    void checkQuickSettingsStatus();

    void flipSetting(TinyInfo info);

    void checkEachQuickSetting(String info);

    //receivers

    void registerWifiStateReceiver();

    void registerBluetoothStateReceiver();

    void registerAutorotateStateReceiver();

    //flips

    void flipWifiSetting(boolean turnOn);

    void flipBluetoothSetting(boolean turnOn);

    void flipAutorotateSetting(boolean turnOn);
}
