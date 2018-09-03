package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.text_scan_test_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextScanTestFragment extends MachineLearningFragment implements TextScanTestInterface {


    public TextScanTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_text_scan_test, container, false);
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
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(visionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                processTextRecognitionResult(firebaseVisionText.getTextBlocks());
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
}
