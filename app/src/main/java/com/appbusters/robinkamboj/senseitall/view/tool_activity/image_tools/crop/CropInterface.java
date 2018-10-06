package com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.crop;

public interface CropInterface {

    void setup();

    void openGalleryForImageSelect();

    void openCameraForImageSelect();

    void showCoordinator(String coordinatorText);

    void hidePlaceholderViews();

    void setClickListeners();

    void setEnabledTint();
}
