package com.appbusters.robinkamboj.senseitall.model.recycler;

public class Category {

    private int drawable;
    private String name;
    private String countDescription;

    public Category(int drawable, String name, String countDescription) {
        this.drawable = drawable;
        this.name = name;
        this.countDescription = countDescription;
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
