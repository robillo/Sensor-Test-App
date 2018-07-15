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

public class MyTaskLoader extends android.support.v4.content.AsyncTaskLoader<boolean[][]> {

    private boolean[] sensorsPresent, featuresPresent, diagnosticsPresent;
    private SensorManager sensorManager;
    private List<String> sensors;
    private HashMap<String, Integer> sMap;
    private PackageManager fManager;
    private List<String> features;
    private HashMap<String, String> fMap;
    private Vibrator vibrator;
    private ConsumerIrManager infrared;

    public MyTaskLoader(Context context,
                        SensorManager sManager,
                        List<String> sensors,
                        HashMap<String, Integer> sMap,
                        PackageManager fManager,
                        List<String> features,
                        HashMap<String, String> fMap,
                        Vibrator vibrator,
                        ConsumerIrManager infrared,
                        List<String> diagnostics) {
        super(context);

        this.sensorManager = sManager;
        this.sensors = sensors;
        this.sMap = sMap;
        this.fManager = fManager;
        this.features = features;
        this.fMap = fMap;
        this.vibrator = vibrator;
        this.infrared = infrared;
        sensorsPresent = new boolean[sensors.size()];
        featuresPresent = new boolean[features.size()];
        diagnosticsPresent = new boolean[diagnostics.size()];
    }

    @Override
    public boolean[][] loadInBackground() {

        int diagPos = 0;

        int pos = 0;
        for(String s : sensors) {
            if (sMap.get(s) != null && sensorManager != null && sensorManager.getDefaultSensor(sMap.get(s)) != null) {
                sensorsPresent[pos] = true;
                if(AppConstants.diagnosticsPointer.get(s) != null) {
                    diagnosticsPresent[diagPos] = true;
                }
            }
            if(AppConstants.diagnosticsPointer.get(s) != null) diagPos++;
            pos++;
        }

        pos = 0;
        for(String f : features) {
            if(fMap.get(f) != null) {
                if(fManager.hasSystemFeature(f)) {
                    featuresPresent[pos] = true;
                    if(AppConstants.diagnosticsPointer.get(f) != null) {
                        diagnosticsPresent[diagPos] = true;
                    }
                }
            }
            else {
                switch (f) {
                    case AppConstants.ANDROID_OS:
                    case AppConstants.BATTERY:
                    case AppConstants.CPU:
                    case AppConstants.SOUND:
                        featuresPresent[pos] = true;
                        break;
                    case AppConstants.RADIO:
                        if (Build.getRadioVersion() != null || Build.getRadioVersion().equals(AppConstants.UNKNOWN)) {
                            featuresPresent[pos] = true;
                            if(AppConstants.diagnosticsPointer.get(f) != null) {
                                diagnosticsPresent[diagPos] = true;
                            }
                        }
                        break;
                    case AppConstants.VIBRATOR:
                        if (vibrator != null && vibrator.hasVibrator()) {
                            featuresPresent[pos] = true;
                            if(AppConstants.diagnosticsPointer.get(f) != null) {
                                diagnosticsPresent[diagPos] = true;
                            }
                        }
                        break;
                    case AppConstants.INFRARED:
                        if (infrared != null && infrared.hasIrEmitter()) {
                            featuresPresent[pos] = true;
                            if(AppConstants.diagnosticsPointer.get(f) != null) {
                                diagnosticsPresent[diagPos] = true;
                            }
                        }
                        break;
                }
            }
            if(AppConstants.diagnosticsPointer.get(f) != null) diagPos++;
            pos++;
        }

        boolean[][] isPresent = new boolean[3][];
        isPresent[0] = sensorsPresent;
        isPresent[1] = featuresPresent;
        isPresent[2] = diagnosticsPresent;

        return isPresent;
    }
}
