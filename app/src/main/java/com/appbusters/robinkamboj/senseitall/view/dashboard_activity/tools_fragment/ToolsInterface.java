package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment;

import android.view.View;

import com.appbusters.robinkamboj.senseitall.model.recycler.SettingInfo;

public interface ToolsInterface {

    void initialize();

    void setup(View v);

    void registerReceivers();

    void unregisterReceivers();

    void setImageToolsAdapter();

    void setEverydayToolsAdapter();

    void setQuickSettingsRecycler();

    void checkQuickSettingsStatus();

    void flipSetting(SettingInfo info);

    void checkEachQuickSetting(String info);

    void inflateQuickSettingsList();

    //receivers

    void registerWifiStateReceiver();

    void registerBluetoothStateReceiver();

    void registerAutorotateStateReceiver();

    void registerAirplaneModeStateReceiver();

    void registerLocationAccessStateReceiver();

    //flips

    void flipWifiSetting(boolean turnOn);

    void flipHotspotSetting(boolean turnOn);

    void flipBluetoothSetting(boolean turnOn);

    void flipAutorotateSetting(boolean turnOn);

    void flipAirplaneModeSetting(boolean turnOn);

    void flipLocationAccessSetting(boolean turnOn);

}
