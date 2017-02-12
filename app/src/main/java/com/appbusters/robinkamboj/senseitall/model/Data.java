package com.appbusters.robinkamboj.senseitall.model;

public class Data {

    public String sensor_name;
    public int drawable;

    public Data(String sensor_name, int drawable) {
        this.sensor_name = sensor_name;
        this.drawable = drawable;
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
