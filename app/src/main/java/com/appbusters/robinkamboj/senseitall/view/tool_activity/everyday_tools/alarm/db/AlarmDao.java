package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface AlarmDao {

    @Insert(onConflict = REPLACE)
    void insertAlarms(Alarm... alarm);

    @Delete
    void deleteAlarms(Alarm... alarm);

    @Query("DELETE FROM alarm WHERE id = :id")
    void deleteAlarmsById(Integer... id);

    @Query("SELECT * FROM alarm")
    LiveData<List<Alarm>> getAlarmInstances();

    @Query("SELECT * FROM alarm WHERE isEnabled = 1")
    LiveData<List<Alarm>> getActiveAlarms();

    @Query("SELECT * FROM alarm WHERE isEnabled = 0")
    LiveData<List<Alarm>> getInactiveAlarms();

}
