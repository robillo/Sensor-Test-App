package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.label_detection_test_fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.google.firebase.ml.vision.label.FirebaseVisionLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector;

import java.text.DecimalFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LabelDetectionTestFragment extends MachineLearningFragment implements LabelDetectionTestInterface {


    public LabelDetectionTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_label_detection_test, container, false);
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
        FirebaseVisionLabelDetector detector = FirebaseVision.getInstance().getVisionLabelDetector();
        detector.detectInImage(visionImage).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionLabel>>() {
            @Override
            public void onSuccess(List<FirebaseVisionLabel> firebaseVisionLabels) {
                processLabelDetectionResult(firebaseVisionLabels);
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
    public void processLabelDetectionResult(List<FirebaseVisionLabel> firebaseVisionLabels) {
        StringBuilder builder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        for(FirebaseVisionLabel label : firebaseVisionLabels) {
            builder.append(returnPercentageValue(label.getLabel(), decimalFormat, label.getConfidence()))
                    .append("\n");
        }
        if(getActivity() != null) {
            ((TestActivity) getActivity()).setResultsToBottomSheet(HEADER_TEXT_SCAN, builder.toString());
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
