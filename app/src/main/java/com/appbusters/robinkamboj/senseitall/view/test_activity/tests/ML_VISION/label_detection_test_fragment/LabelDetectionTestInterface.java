package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.label_detection_test_fragment;

import com.google.firebase.ml.vision.label.FirebaseVisionLabel;

import java.text.DecimalFormat;
import java.util.List;

public interface LabelDetectionTestInterface {

    void processLabelDetectionResult(List<FirebaseVisionLabel> firebaseVisionLabels);

    String returnPercentageValue(String header, DecimalFormat decimalFormat, float probability);

}
