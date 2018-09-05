package com.appbusters.robinkamboj.senseitall.model.recycler;

public class LearnMoreItem {

    private String drawable;
    private String header;
    private String content;

    public LearnMoreItem(String drawable, String header, String content) {
        this.drawable = drawable;
        this.header = header;
        this.content = content;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
