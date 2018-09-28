package com.appbusters.robinkamboj.senseitall.model.recycler;

public class Category {

    private int drawable;
    private String name;
    private String countDescription;
    private int type;

    public Category(int drawable, String name, String countDescription) {
        this.drawable = drawable;
        this.name = name;
        this.countDescription = countDescription;
        type = 0;
    }

    public Category(int drawable, String name, String countDescription, int type) {
        this.drawable = drawable;
        this.name = name;
        this.countDescription = countDescription;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountDescription() {
        return countDescription;
    }

    public void setCountDescription(String countDescription) {
        this.countDescription = countDescription;
    }
}
