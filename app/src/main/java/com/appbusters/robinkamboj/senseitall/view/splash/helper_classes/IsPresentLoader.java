package com.appbusters.robinkamboj.senseitall.view.splash.helper_classes;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

import com.appbusters.robinkamboj.senseitall.utils.AppConstants;

import java.util.List;
import java.util.Objects;

import static android.content.Context.CONSUMER_IR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFRARED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RAM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.featureNames;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.sensorNames;

public class IsPresentLoader extends AsyncTaskLoader<boolean[][]> {

    private SensorManager sensorManager;
    private boolean isFingerPrintSupported;
    private Vibrator vibrator;
    private ConsumerIrManager infrared;
    private PackageManager featureManager;

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
            @NonNull Context context,
            List<String> diagnosticList,
            List<String> sensorList,
            List<String> featureList,
            List<String> informationList,
            List<String> softwareList,
            List<String> androidList) {
        super(context);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        infrared = (ConsumerIrManager) context.getSystemService(CONSUMER_IR_SERVICE);
        featureManager = context.getPackageManager();

        this.diagnosticList = diagnosticList;
        this.sensorList = sensorList;
        this.featureList = featureList;
        this.informationList = informationList;
        this.softwareList = softwareList;
        this.androidList = androidList;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isFingerPrintSupported = ActivityCompat
                    .checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) ==
                    PackageManager.PERMISSION_GRANTED &&
                    Objects.requireNonNull(context.getSystemService(FingerprintManager.class))
                            .isHardwareDetected();
        } else {
            isFingerPrintSupported = FingerprintManagerCompat.from(context).isHardwareDetected();
        }
    }

    @Nullable
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
                return Build.getRadioVersion() != null || Build.getRadioVersion().equals(AppConstants.UNKNOWN);
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
            case RAM: {
                return true;
            }
        }
        return false;
    }

    private boolean isPresentSoftware(String element) {

        return false;
    }

    private boolean isPresentAndroid(String element) {

        return false;
    }
}
