package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "check")
public class Check {

    public Check() {}

    @Ignore
    public Check(String check_text, Boolean isDone) {
        this.check_text = check_text;
        this.isDone = isDone;
    }

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String check_text;
    private Boolean isDone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheck_text() {
        return check_text;
    }

    public void setCheck_text(String check_text) {
        this.check_text = check_text;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
