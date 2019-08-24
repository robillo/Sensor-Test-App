package com.appbusters.robinkamboj.senseitall.ui.tool_activity;

public interface ToolsInterface {

    void setDataForIntent();

    void setCorrespondingFragment();

    void setStatusBarColor();

    void setTimerForTimer(int hours, int mins, int secs);

    void setNoteInputFragment(String heading, String description, int noteId);

    void saveNoteItem(String heading, String description);

    void showCoordinator(String text);

    void editNote(String heading, String description, int noteId);

    void deleteNoteById(Integer noteId);

    void saveEditedNote(String heading, String description, Integer id);

    void markCheckAsDoneById(boolean isDone, int id);

    void deleteCheckById(int id);

}
