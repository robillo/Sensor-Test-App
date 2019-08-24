package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.take_note.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {

    public Note() {
    }

    @Ignore
    public Note(String heading, String description, String date) {
        this.heading = heading;
        this.description = description;
        this.date = date;
    }

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String heading;
    private String description;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
