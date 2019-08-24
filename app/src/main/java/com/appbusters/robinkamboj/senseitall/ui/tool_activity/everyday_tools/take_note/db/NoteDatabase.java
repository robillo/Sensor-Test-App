package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.take_note.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase noteDatabase;

    private static final String DB_NAME = "NOTE_DB";

    public abstract NoteDao getNoteDao();

    public static NoteDatabase getDatabaseInstance(Context context) {
        if(noteDatabase == null) {
            noteDatabase = Room.databaseBuilder(context, NoteDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return noteDatabase;
    }

}
