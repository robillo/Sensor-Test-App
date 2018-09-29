package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer;

public interface TimerInterface {

    void setup();

    void showCoordinator(String text);

    void setClickListeners();

    void updateIconTints(int index);

    void takeInputForTimer();

    void setInputForTimer(int hours, int mins, int secs);

    void updateMainTextsForTime(int hh, int mm, int ss);

    String formatTimeToDisplay(int val);

}
