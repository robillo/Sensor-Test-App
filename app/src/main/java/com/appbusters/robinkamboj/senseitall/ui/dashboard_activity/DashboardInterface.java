package com.appbusters.robinkamboj.senseitall.ui.dashboard_activity;

public interface DashboardInterface {

    void setup();

    void changeStatusBarColor();

    void setDiscoverFragment();

    void setPersonalizedFragment();

    void setToolsFragment();

    void setClickListeners();

    void showSnackBar(String text);

    void setColorFilterToIcons(String header);

}
