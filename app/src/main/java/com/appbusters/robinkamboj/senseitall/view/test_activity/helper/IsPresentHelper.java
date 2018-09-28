package com.appbusters.robinkamboj.senseitall.view.test_activity.helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Vibrator;

import com.appbusters.robinkamboj.senseitall.utils.AppConstants;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BARCODE_READER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_EMOJI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FAKE_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LABEL_GENERATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MOTION_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RAM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIRTUAL_REALITY;

public class IsPresentHelper {

    private CameraManager cameraManager;
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private PackageManager packageManager;

    public IsPresentHelper(Context context) {
        packageManager = context.getPackageManager();
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public boolean isPresent(String item) {

        if(AppConstants.sensorManagerInts.get(item) != null) {
            return sensorManager != null && sensorManager.getDefaultSensor(AppConstants.sensorManagerInts.get(item)) != null;
        }

        if(AppConstants.packageManagerPaths.get(item) != null) {
            switch (item) {
                case ANDROID_OS:
                case BATTERY:
                case CPU:
                case SOUND:
                    return true;
                case RADIO:
                    return Build.getRadioVersion() != null || Build.getRadioVersion().equals(AppConstants.UNKNOWN);
                case VIBRATOR:
                    return vibrator != null && vibrator.hasVibrator();
                default: {
                    return packageManager.hasSystemFeature(AppConstants.packageManagerPaths.get(item));
                }
            }
        }

        switch (item) {
            case GSM_UMTS:
            case RADIO:
            case CPU:
            case STORAGE:
            case RAM:
            case FAKE_TOUCH: {
                return true;
            }
            case VIRTUAL_REALITY:
                return true;
            case MOTION_DETECT:
            case BARCODE_READER:
            case TEXT_SCAN:
            case LABEL_GENERATOR:
            case FACE_EMOJI:
            case FACE_DETECT: {
                return cameraManager != null;
            }
        }

        return false;
    }
}
