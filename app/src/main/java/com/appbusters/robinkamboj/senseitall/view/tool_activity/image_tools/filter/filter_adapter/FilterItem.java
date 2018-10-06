package com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.filter.filter_adapter;

import jp.wasabeef.glide.transformations.gpu.GPUFilterTransformation;

public class FilterItem {

    private String transName;
    private GPUFilterTransformation transformation;

    public FilterItem(String transName, GPUFilterTransformation transformation) {
        this.transName = transName;
        this.transformation = transformation;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public GPUFilterTransformation getTransformation() {
        return transformation;
    }

    public void setTransformation(GPUFilterTransformation transformation) {
        this.transformation = transformation;
    }
}
