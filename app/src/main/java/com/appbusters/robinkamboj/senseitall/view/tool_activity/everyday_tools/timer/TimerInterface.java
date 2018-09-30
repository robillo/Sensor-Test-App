package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer;

public interface TimerInterface {

    void setup();

    void stopTimer();

    void startTimer();

    void resetTimer();

    void takeInputForTimer();

    void setClickListeners();

    void refreshMainTextsForTick();

    void updateIconTints(int index);

    void showCoordinator(String text);

    String formatTimeToDisplay(int val);

    long timeInMillis(int hh, int mm, int ss);

    void setInputForTimer(int hours, int mins, int secs);

    void updateMainTextsForTime(int hh, int mm, int ss);

}
