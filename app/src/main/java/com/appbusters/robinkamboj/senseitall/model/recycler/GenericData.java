package com.appbusters.robinkamboj.senseitall.model.recycler;

public class GenericData {

    private String name;
    private int drawableId;
    private boolean isPresent;
    private int type;

    public GenericData() {

    }

    public GenericData(String name, int drawableId, boolean isPresent, int type) {
        this.name = name;
        this.drawableId = drawableId;
        this.isPresent = isPresent;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
