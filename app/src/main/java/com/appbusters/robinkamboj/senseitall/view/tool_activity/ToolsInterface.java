package com.appbusters.robinkamboj.senseitall.view.tool_activity;

import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db.Note;

public interface ToolsInterface {

    void setDataForIntent();

    void setCorrespondingFragment();

    void setStatusBarColor();

    void setTimerForTimer(int hours, int mins, int secs);

    void setNoteInputFragment(String heading, String description);

    void saveNoteItem(String heading, String description);

    void showCoordinator(String text);

    void editNote(String heading, String description);

    void deleteNoteById(Integer noteId);

}
