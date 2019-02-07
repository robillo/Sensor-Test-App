package com.appbusters.robinkamboj.senseitall.model.recycler;

public class SettingInfo {

    private String name;
    private int drawableOn;
    private int drawableOff;
    private boolean isOn;

    public SettingInfo(String name, int drawableOn, int drawableOff) {
        this.name = name;
        this.drawableOn = drawableOn;
        this.drawableOff = drawableOff;
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String getName() {
        return name;
    }

    public void OnName(String name) {
        this.name = name;
    }

    public int getDrawableOn() {
        return drawableOn;
    }

    public void OnDrawableOn(int drawableOn) {
        this.drawableOn = drawableOn;
    }

    public int getdrawableOff() {
        return drawableOff;
    }

    public void OndrawableOff(int drawableOff) {
        this.drawableOff = drawableOff;
    }
}
