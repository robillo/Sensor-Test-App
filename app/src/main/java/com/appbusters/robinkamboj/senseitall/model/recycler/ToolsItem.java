package com.appbusters.robinkamboj.senseitall.model.recycler;

import org.jetbrains.annotations.NotNull;

public class ToolsItem {

    private String name;
    private int drawable;

    public ToolsItem(@NotNull String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
