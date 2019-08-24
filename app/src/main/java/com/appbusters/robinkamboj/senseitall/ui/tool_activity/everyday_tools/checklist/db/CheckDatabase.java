package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.checklist.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Check.class}, version = 1, exportSchema = false)
public abstract class CheckDatabase extends RoomDatabase {

    private static CheckDatabase checkDatabase;

    private static final String DB_NAME = "CHECK_DB";

    public abstract CheckDao getCheckDao();

    public static CheckDatabase getDatabaseInstance(Context context) {
        if(checkDatabase == null) {
            checkDatabase = Room.databaseBuilder(context, CheckDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return checkDatabase;
    }
}
