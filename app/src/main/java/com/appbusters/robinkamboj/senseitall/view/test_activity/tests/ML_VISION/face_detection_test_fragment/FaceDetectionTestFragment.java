package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.face_detection_test_fragment;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaceDetectionTestFragment extends MachineLearningFragment implements FaceDetectionTestInterface {

    public FaceDetectionTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_face_detection_test, container, false);
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
        FirebaseVisionFaceDetectorOptions options = new FirebaseVisionFaceDetectorOptions.Builder()
                .setTrackingEnabled(false)
                .setModeType(FirebaseVisionFaceDetectorOptions.ACCURATE_MODE)
                .setLandmarkType(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                .setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                .setMinFaceSize(0.05f).build();
        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance().getVisionFaceDetector(options);
        detector.detectInImage(visionImage).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
            @Override
            public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                processFaceDetectorFaces(firebaseVisionFaces);
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
    public void processFaceDetectorFaces(List<FirebaseVisionFace> firebaseVisionFaces) {

        List<Rect> boundingBoxes = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        int faceCount = 1;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        for(FirebaseVisionFace face : firebaseVisionFaces) {
            if(face.getBoundingBox() != null) {
                boundingBoxes.add(face.getBoundingBox());
            }

            builder.append("<hr/>").append("<h1>").append("<font color=\'black\'>").append("Face ")
                    .append(faceCount).append("</font></h1>")
                    .append(returnPercentageValue(
                            "Smile",
                            decimalFormat,
                            face.getSmilingProbability())
                    )
                    .append(returnPercentageValue(
                            "Left eye open",
                            decimalFormat,
                            face.getLeftEyeOpenProbability())
                    )
                    .append(returnPercentageValue(
                            "Right eye open",
                            decimalFormat,
                            face.getRightEyeOpenProbability())
                    )
                    .append("<br>");

            faceCount++;
        }

        showPreviewInNewBitmapIfAny(boundingBoxes);

        if(getActivity() != null) {
            ((TestActivity) getActivity()).setResultsToBottomSheet(HEADER_TEXT_SCAN, builder.toString());
        }
    }

    @Override
    public void showPreviewInNewBitmapIfAny(List<Rect> boundingBoxes) {
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

            for(Rect rect: boundingBoxes) {
                canvas.clipRect(rect);
            }
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public String returnPercentageValue(String header, DecimalFormat decimalFormat, float probability) {
        if(probability < 0) probability = 0.00f;
        else probability = Float.valueOf(decimalFormat.format(probability*100));

        StringBuilder builder = new StringBuilder();
        String colorHex;

        if(getActivity() == null) return null;

        if(probability > 90) {
            colorHex = getResources().getString(R.color.ninety_plus);
        }
        else if(probability > 80) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.eighty_plus) & 0xffffff);;
        }
        else if(probability > 70) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.seventy_plus) & 0xffffff);;
        }
        else if(probability > 60) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.sixty_plus) & 0xffffff);;
        }
        else if(probability > 50) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.fifty_plus) & 0xffffff);;
        }
        else if(probability > 40) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.forty_plus) & 0xffffff);;
        }
        else if(probability > 30) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.thirty_plus) & 0xffffff);;
        }
        else if(probability > 20) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.twenty_plus) & 0xffffff);;
        }
        else if(probability > 10) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.ten_plus) & 0xffffff);;
        }
        else if(probability >= 0) {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.zero_plus) & 0xffffff);;
        }
        else {
            colorHex = String.format("#%06x", ContextCompat.getColor(getActivity(), R.color.zero_plus) & 0xffffff);;
        }

        builder.append("<h5>")
                .append(header).append(" <font color=\'")
                .append("#")
                .append(colorHex.substring(3))
                .append("\'>").append(probability)
                .append("%</font> sure</h4>\n");

        return builder.toString();
    }
}
