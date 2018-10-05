package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist;

import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.db.Check;

public interface ChecklistInterface {

    void setup();

    void showToast(String text);

    void setClickListeners();

    void getDbInstances();

    void initialize();

    void displayAllNotes();

    void addItemToDb(Check check);

}
