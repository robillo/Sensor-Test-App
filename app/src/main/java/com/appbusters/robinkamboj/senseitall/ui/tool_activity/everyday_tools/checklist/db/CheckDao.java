package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.checklist.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CheckDao {

    @Insert(onConflict = REPLACE)
    void insertChecks(Check... checks);

    @Delete
    void deleteChecks(Check... checks);

    @Query("DELETE FROM `check` WHERE id = :id")
    void deleteCheckById(int... id);

    @Query("UPDATE `check` SET check_text = :check_text, isDone = :isDone WHERE id = :id")
    void updateCheckById(String check_text, boolean isDone, int id);

    @Query("UPDATE `check` SET isDone = :isDone WHERE id = :id")
    void updateIsDoneById(boolean isDone, int id);

    @Query("SELECT * FROM `check`")
    LiveData<List<Check>> getAllChecks();

}
