package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static final String DB_NAME = "NOTE_DB";

    public abstract NoteDao getNoteDao();

    public static NoteDatabase getDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, NoteDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

}
