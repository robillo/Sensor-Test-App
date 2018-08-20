package com.appbusters.robinkamboj.senseitall.view.splash.helper_classes;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Vibrator;

import com.appbusters.robinkamboj.senseitall.utils.AppConstants;

import java.util.HashMap;
import java.util.List;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFRARED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;

public class MyTaskLoader extends android.support.v4.content.AsyncTaskLoader<boolean[][]> {

    private boolean[] sensorsPresent,
            featuresPresent,
            diagnosticsPresent,
            informationsPresent,
            softwaresPresent,
            androidsPresent;
    private SensorManager sensorManager;
    private List<String> sensors;
    private HashMap<String, Integer> sMap;
    private PackageManager fManager;
    private List<String> features;
    private HashMap<String, String> fMap;
    private Vibrator vibrator;
    private ConsumerIrManager infrared;
    private boolean fingerprintBool;

    public MyTaskLoader(Context context,
                        SensorManager sManager,
                        List<String> sensors,
                        HashMap<String, Integer> sMap,
                        PackageManager fManager,
                        List<String> features,
                        HashMap<String, String> fMap,
                        Vibrator vibrator,
                        ConsumerIrManager infrared,
                        boolean fingerprintBool,
                        List<String> diagnostics,
                        List<String> informations,
                        List<String> softwares,
                        List<String> androids) {
        super(context);

        this.sensorManager = sManager;
        this.sensors = sensors;
        this.sMap = sMap;

        this.fManager = fManager;
        this.features = features;
        this.fMap = fMap;

        this.vibrator = vibrator;
        this.infrared = infrared;

        this.fingerprintBool = fingerprintBool;

        sensorsPresent = new boolean[sensors.size()];
        featuresPresent = new boolean[features.size()];
        diagnosticsPresent = new boolean[diagnostics.size()];
        informationsPresent = new boolean[informations.size()];
        softwaresPresent = new boolean[softwares.size()];
        androidsPresent = new boolean[androids.size()];
    }

    @Override
    public boolean[][] loadInBackground() {

        int testPos = 0;

        int pos = 0;
        for(String s : sensors) {
            if (sMap.get(s) != null && sensorManager != null && sensorManager.getDefaultSensor(sMap.get(s)) != null) {
                sensorsPresent[pos] = true;
                setDiagnostics(s, testPos, diagnosticsPresent);
            }
            pos++;
        }

        pos = 0;
        for(String f : features) {
            if(fMap.get(f) != null) {
                if(f.equals(FINGERPRINT)) {
                    if(fingerprintBool) {
                        featuresPresent[pos] = true;
                        setDiagnostics(f, testPos, diagnosticsPresent);
                    }
                }
                else if(fManager.hasSystemFeature(fMap.get(f))) {
                    featuresPresent[pos] = true;
                    setDiagnostics(f, testPos, diagnosticsPresent);
                }
            }
            else {
                switch (f) {
                    case BACK_CAMERA: {

                        break;
                    }
                    case ANDROID_OS:
                    case BATTERY:
                    case CPU:
                    case SOUND:
                        featuresPresent[pos] = true;
                        setDiagnostics(f, testPos, diagnosticsPresent);
                        break;
                    case RADIO:
                        if (Build.getRadioVersion() != null || Build.getRadioVersion().equals(AppConstants.UNKNOWN)) {
                            featuresPresent[pos] = true;
                            setDiagnostics(f, testPos, diagnosticsPresent);
                        }
                        break;
                    case VIBRATOR:
                        if (vibrator != null && vibrator.hasVibrator()) {
                            featuresPresent[pos] = true;
                            setDiagnostics(f, testPos, diagnosticsPresent);
                        }
                        break;
                    case INFRARED:
                        if (infrared != null && infrared.hasIrEmitter()) {
                            featuresPresent[pos] = true;
                            setDiagnostics(f, testPos, diagnosticsPresent);
                        }
                        break;
                }
            }
            pos++;
        }

        boolean[][] isPresent = new boolean[6][];
        isPresent[0] = sensorsPresent;
        isPresent[1] = featuresPresent;
        isPresent[2] = diagnosticsPresent;
        isPresent[3] = informationsPresent;
        isPresent[4] = softwaresPresent;
        isPresent[5] = androidsPresent;

        return isPresent;
    }

    private void setDiagnostics(String data, int position, boolean[] diagnosticsPresent) {
    }
}
