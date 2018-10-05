package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note;

public interface NoteInterface {

    void setup();

    void getDbInstances();

    void initialize();

    void displayAllNotes();

    void setClickListeners();

    void deleteNoteById(Integer noteId);

    void saveNoteToDb(String heading, String description);

    void saveEditedNote(String heading, String description, Integer noteId);

}
