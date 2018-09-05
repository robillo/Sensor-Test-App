package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.barcode_detection_test_fragment;

import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;

import java.util.List;

public interface BarcodeReaderTestInterface {

    void processBarcodeReaderResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes);

    void showPreviewInNewBitmapIfAny();

}
