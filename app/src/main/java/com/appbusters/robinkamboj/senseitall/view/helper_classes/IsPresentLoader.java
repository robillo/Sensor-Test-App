package com.appbusters.robinkamboj.senseitall.view.helper_classes;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.content.AsyncTaskLoader;

import com.appbusters.robinkamboj.senseitall.utils.AppConstants;

import java.util.List;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BARCODE_READER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_EMOJI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FAKE_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFRARED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LABEL_GENERATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MOTION_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RAM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIRTUAL_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.featureNames;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.sensorNames;

public class IsPresentLoader extends AsyncTaskLoader<boolean[][]> {

    private SensorManager sensorManager;
    private boolean isFingerPrintSupported;
    private Vibrator vibrator;
    private ConsumerIrManager infrared;
    private PackageManager featureManager;
    private CameraManager cameraManager;

    private boolean[] sensorsPresent,
            featuresPresent,
            diagnosticPresent,
            informationPresent,
            softwarePresent,
            androidsPresent;

    private List<String> diagnosticList,
            sensorList,
            featureList,
            informationList,
            softwareList,
            androidList;

    public IsPresentLoader(
            Context context,
            SensorManager sensorManager,
            Vibrator vibrator,
            ConsumerIrManager infrared,
            PackageManager featureManager,
            CameraManager cameraManager,
            boolean isFingerPrintSupported,
            List<String> diagnosticList,
            List<String> sensorList,
            List<String> featureList,
            List<String> informationList,
            List<String> softwareList,
            List<String> androidList) {
        super(context);

        this.sensorManager = sensorManager;
        this.vibrator = vibrator;
        this.infrared = infrared;
        this.featureManager = featureManager;
        this.cameraManager = cameraManager;
        this.isFingerPrintSupported = isFingerPrintSupported;

        this.diagnosticList = diagnosticList;
        this.sensorList = sensorList;
        this.featureList = featureList;
        this.informationList = informationList;
        this.softwareList = softwareList;
        this.androidList = androidList;
    }

    @Override
    public boolean[][] loadInBackground() {

        diagnosticPresent = returnPresentDiagnostic(diagnosticList);
        sensorsPresent = returnPresentSensors(sensorList);
        featuresPresent = returnPresentFeatures(featureList);
        informationPresent = returnPresentInformation(informationList);
        softwarePresent = returnPresentSoftware(softwareList);
        androidsPresent = returnPresentAndroid(androidList);

        boolean[][] isPresent = new boolean[6][];
        isPresent[0] = sensorsPresent;
        isPresent[1] = featuresPresent;
        isPresent[2] = diagnosticPresent;
        isPresent[3] = informationPresent;
        isPresent[4] = softwarePresent;
        isPresent[5] = androidsPresent;
        return isPresent;
    }

    private boolean[] returnPresentDiagnostic(List<String> elementList) {
        diagnosticPresent = new boolean[elementList.size()];
        for(int i=0; i<elementList.size(); i++) {
            diagnosticPresent[i] = isPresentDiagnostic(elementList.get(i));
        }
        return diagnosticPresent;
    }

    private boolean[] returnPresentSensors(List<String> elementList) {
        sensorsPresent = new boolean[elementList.size()];
        for(int i=0; i<elementList.size(); i++) {
            sensorsPresent[i] = isPresentSensor(elementList.get(i));
        }
        return sensorsPresent;
    }

    private boolean[] returnPresentFeatures(List<String> elementList) {
        featuresPresent = new boolean[elementList.size()];
        for(int i=0; i<elementList.size(); i++) {
            featuresPresent[i] = isPresentFeature(elementList.get(i));
        }
        return featuresPresent;
    }

    private boolean[] returnPresentInformation(List<String> elementList) {
        informationPresent = new boolean[elementList.size()];
        for(int i=0; i<elementList.size(); i++) {
            informationPresent[i] = isPresentInformation(elementList.get(i));
        }
        return informationPresent;
    }

    private boolean[] returnPresentSoftware(List<String> elementList) {
        softwarePresent = new boolean[elementList.size()];
        for(int i=0; i<elementList.size(); i++) {
            softwarePresent[i] = isPresentSoftware(elementList.get(i));
        }
        return softwarePresent;
    }

    private boolean[] returnPresentAndroid(List<String> elementList) {
        androidsPresent = new boolean[elementList.size()];
        for(int i=0; i<elementList.size(); i++) {
            androidsPresent[i] = isPresentAndroid(elementList.get(i));
        }
        return androidsPresent;
    }

    private boolean isPresentDiagnostic(String element) {
        for(int i=0; i<featureNames.size(); i++) {
            if(featureNames.get(i).equals(element))
                return isPresentFeature(element);
        }
        for(int i=0; i<sensorNames.size(); i++) {
            if(sensorNames.get(i).equals(element))
                return isPresentSensor(element);
        }
        return false;
    }

    private boolean isPresentSensor(String element) {
        return AppConstants.sensorManagerInts.get(element) != null
                && sensorManager != null
                && sensorManager.getDefaultSensor(AppConstants.sensorManagerInts.get(element)) != null;
    }

    private boolean isPresentFeature(String element) {
        switch (element) {
            case ANDROID_OS:
            case BATTERY:
            case CPU:
            case SOUND:
                return true;
            case RADIO:
                return Build.getRadioVersion() != null;
            case VIBRATOR:
                return vibrator != null && vibrator.hasVibrator();
            case INFRARED:
                return infrared != null && infrared.hasIrEmitter();
            case FINGERPRINT: {
                return isFingerPrintSupported;
            }
            default: {
                return featureManager.hasSystemFeature(AppConstants.packageManagerPaths.get(element));
            }
        }
    }

    private boolean isPresentInformation(String element) {
        switch (element) {
            case GSM_UMTS:
            case RADIO:
            case CPU:
            case ANDROID_OS: {
                return isPresentFeature(element);
            }
            case STORAGE:
            case RAM:
            case FAKE_TOUCH: {
                return true;
            }
        }
        return false;
    }

    private boolean isPresentSoftware(String element) {
        switch (element) {
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
            default: return false;
        }
    }

    private boolean isPresentAndroid(@SuppressWarnings("unused") String element) {
        //currently just showing features added in each android version
        //maybe will add tests later
        return true;
//        switch (Build.VERSION.SDK_INT) {
//            case Build.VERSION_CODES.O_MR1:
//            case Build.VERSION_CODES.O: {
//                if(element.equals(PIE)) return false;
//                break;
//            }
//            case Build.VERSION_CODES.N_MR1:
//            case Build.VERSION_CODES.N: {
//                if(element.equals(PIE) || element.equals(OREO)) return false;
//                break;
//            }
//            case Build.VERSION_CODES.M: {
//                if(element.equals(PIE) || element.equals(OREO) || element.equals(NOUGAT)) return false;
//                break;
//            }
//            case Build.VERSION_CODES.LOLLIPOP_MR1:
//            case Build.VERSION_CODES.LOLLIPOP: {
//                if(element.equals(PIE) || element.equals(OREO) || element.equals(NOUGAT) || element.equals(MARSHMALLOW))
//                    return false;
//                break;
//            }
//        }
    }
}
