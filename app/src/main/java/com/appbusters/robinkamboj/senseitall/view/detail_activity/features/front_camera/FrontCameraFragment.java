package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.front_camera;


import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.back_camera_test_fragment.BackCamTestInterface;

import java.util.Arrays;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrontCameraFragment extends FeatureFragment implements BackCamTestInterface {

    private CameraManager cameraManager;

    public FrontCameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_back_camera, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);
        initializeSensor();

        hideGoToTestIfNoTest();

        setupAbout();

        showSensorDetails();
    }

    @Override
    public void initializeSensor() {
        if(getActivity() == null) return;

        cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
    }

    @Override
    public void initializeBasicInformation() {
        if(cameraManager == null) return;

        try {
            for(String cameraId : cameraManager.getCameraIdList()) {
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
                addToDetailsList(sensorDetails, "Flash Info Available", String.valueOf(characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)));
                addToDetailsList(sensorDetails, "Lens Facing", String.valueOf(characteristics.get(CameraCharacteristics.LENS_FACING)));
                addToDetailsList(sensorDetails, "Lens Info Available Apertures",
                        Arrays.toString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES)));
                addToDetailsList(sensorDetails, "Lens Info Filter Densities",
                        Arrays.toString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FILTER_DENSITIES)));
                addToDetailsList(sensorDetails, "Lens Info Focal Lengths",
                        Arrays.toString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS)));
                addToDetailsList(sensorDetails, "Lens Info Optical Stabilization",
                        Arrays.toString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION)));
                addToDetailsList(sensorDetails, "Lens Info Focus Distance Calibration",
                        String.valueOf(characteristics.get(CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION)));
                addToDetailsList(sensorDetails, "Color Correction Available Aberration Modes",
                        Arrays.toString(characteristics.get(CameraCharacteristics.COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES)));
                addToDetailsList(sensorDetails, "Control AE Available Antibanding Modes",
                        Arrays.toString(characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    addToDetailsList(sensorDetails, "Control AE Lock Available",
                            String.valueOf(characteristics.get(CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE)));
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
