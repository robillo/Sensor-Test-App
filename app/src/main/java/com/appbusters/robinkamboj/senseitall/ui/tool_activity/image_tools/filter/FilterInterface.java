package com.appbusters.robinkamboj.senseitall.ui.tool_activity.image_tools.filter;

public interface FilterInterface {

    void setup();

    void openGalleryForImageSelect();

    void openCameraForImageSelect();

    void showCoordinator(String coordinatorText);

    void hidePlaceholderViews();

    void setClickListeners();

    void setEnabledTint();

    void setFilterAdapter();

}
