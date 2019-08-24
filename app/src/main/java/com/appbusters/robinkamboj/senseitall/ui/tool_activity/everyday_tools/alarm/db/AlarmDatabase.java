package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Alarm.class}, version = 1, exportSchema = false)
public abstract class AlarmDatabase extends RoomDatabase {

    private static final String DB_NAME = "ALARM_DB";

    public abstract AlarmDao getAlarmDao();

    public static AlarmDatabase getDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, AlarmDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
}
