package com.appbusters.robinkamboj.senseitall.view.tool_activity;

public interface ToolsInterface {

    void setDataForIntent();

    void setCorrespondingFragment();

    void setStatusBarColor();

    void setTimerForTimer(int hours, int mins, int secs);

    void setNoteInputFragment();

    void saveNoteItem(String heading, String description);

    void showCoordinator(String text);

}
