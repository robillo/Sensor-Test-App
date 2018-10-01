package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist;

public interface ChecklistInterface {

    void setup();

    void showToast(String text);

    void addItemToDb(String text);

    void addItemToRv(String text);

    void setClickListeners();

}
