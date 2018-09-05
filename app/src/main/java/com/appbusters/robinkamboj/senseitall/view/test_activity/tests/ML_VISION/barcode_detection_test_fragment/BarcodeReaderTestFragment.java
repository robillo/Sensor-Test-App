package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.barcode_detection_test_fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.MachineLearningFragment;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarcodeReaderTestFragment extends MachineLearningFragment implements BarcodeReaderTestInterface {

    private List<Rect> boundingBoxes;

    public BarcodeReaderTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_barcode_reader_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void processImage() {
        //disable button till image is processed
        doneButton.setEnabled(false);
        //process image
        if(bitmap == null) {
            showCoordinator(getString(R.string.please_show_image));
            doneButton.setEnabled(true);
            return;
        }

        if(getActivity() != null) ((TestActivity) getActivity()).showBottomSheetResults();

        FirebaseVisionImage visionImage = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance().getVisionBarcodeDetector();
        detector.detectInImage(visionImage).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
            @Override
            public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
                processBarcodeReaderResult(firebaseVisionBarcodes);
                doneButton.setEnabled(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showCoordinator(getString(R.string.please_try_again));
                doneButton.setEnabled(true);
            }
        });
        //enable button
    }

    @Override
    public void processBarcodeReaderResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {

        boundingBoxes = null;
        boundingBoxes = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        for(FirebaseVisionBarcode barcode : firebaseVisionBarcodes) {
            if(barcode.getBoundingBox() != null) {
                boundingBoxes.add(barcode.getBoundingBox());
            }
            builder.append(barcode.getDisplayValue()).append("\n");
        }

        showPreviewInNewBitmapIfAny();

        if(getActivity() != null) {
            ((TestActivity) getActivity()).setResultsToBottomSheet(HEADER_TEXT_SCAN, builder.toString());
        }
    }

    @Override
    public void showPreviewInNewBitmapIfAny() {
        if(bitmap != null && getActivity() != null && boundingBoxes.size() > 0) {

            Bitmap newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(newBitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStrokeWidth(20);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(ContextCompat.getColor(getActivity(), R.color.green_shade_three));

            for(Rect rect: boundingBoxes) {
                canvas.drawRect(rect, paint);
            }

            imageToWorkOn.setImageBitmap(newBitmap);
        }
    }
}
