package com.appbusters.robinkamboj.senseitall.model.recycler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ToolsItem {

    private String name;
    private int drawable;

    public ToolsItem(@NotNull String name, @Nullable Integer drawable) {
        this.name = name;
        this.drawable = drawable == null ? 0 : drawable;
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
