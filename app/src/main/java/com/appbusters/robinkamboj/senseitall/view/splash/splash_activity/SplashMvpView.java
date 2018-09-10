package com.appbusters.robinkamboj.senseitall.view.splash.splash_activity;

public interface SplashMvpView {

    void setup();

    void startDashboardActivity();

    void startCountDown(int millis);

    void changeStatusBarColor();

}
