package com.appbusters.robinkamboj.senseitall.model;

import android.support.v7.widget.util.SortedListAdapterCallback;

public class Data {

    public String sensor_name;
    public int drawable;
    public Boolean isPresent;

    public Data(String sensor_name, int drawable, Boolean isPresent) {
        this.sensor_name = sensor_name;
        this.drawable = drawable;
        this.isPresent = isPresent;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

}
