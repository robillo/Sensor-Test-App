package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao {

    @Insert(onConflict = REPLACE)
    void insertNotes(Note... Note);

    @Delete
    void deleteNotes(Note... Note);

    @Query("DELETE FROM note WHERE id = :id")
    void deleteNotesById(int... id);

    @Query("UPDATE note SET heading = :heading, description = :description, date = :date WHERE id = :id")
    void updateNoteById(String heading, String description, String date, int id);

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

}
