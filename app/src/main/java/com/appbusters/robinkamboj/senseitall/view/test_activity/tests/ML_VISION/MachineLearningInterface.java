package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION;

import android.view.View;

public interface MachineLearningInterface {

    void setup(View v);

    void openGalleryForImageSelect();

    void openCameraForImageSelect();

    void showCoordinator(String coordinatorText);

    void processImage();

}
