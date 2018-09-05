package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.text_scan_test_fragment;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.util.List;

public interface TextScanTestInterface {

    void processTextRecognitionResult(List<FirebaseVisionText.TextBlock> texts);

}
