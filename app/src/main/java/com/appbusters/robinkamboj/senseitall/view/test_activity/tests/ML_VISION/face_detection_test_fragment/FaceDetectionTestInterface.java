package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.face_detection_test_fragment;

import android.graphics.Rect;

import com.google.firebase.ml.vision.face.FirebaseVisionFace;

import java.text.DecimalFormat;
import java.util.List;

public interface FaceDetectionTestInterface {

    void processFaceDetectorFaces(List<FirebaseVisionFace> firebaseVisionFaces);

    void showPreviewInNewBitmapIfAny(List<Rect> boundingBoxes);

    String returnPercentageValue(DecimalFormat decimalFormat, float probability);

}
