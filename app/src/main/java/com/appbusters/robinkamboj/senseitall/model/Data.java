package com.appbusters.robinkamboj.senseitall.model;

import android.support.v7.widget.util.SortedListAdapterCallback;

public class Data {

    public String sensor_name;
    public Boolean isPresent;
    private String drawableUrl;

    public Data(String sensor_name, Boolean isPresent, String drawableUrl) {
        this.sensor_name = sensor_name;
        this.isPresent = isPresent;
        this.drawableUrl = drawableUrl;
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


    public String getDrawableUrl() {
        return drawableUrl;
    }

    public void setDrawableUrl(String drawableUrl) {
        this.drawableUrl = drawableUrl;
    }
}
