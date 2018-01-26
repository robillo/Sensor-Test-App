package com.appbusters.robinkamboj.senseitall.view.activities.splash;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import java.util.List;

import static android.content.Context.CONSUMER_IR_SERVICE;
import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by robinkamboj on 26/01/18.
 */

public class MyTaskLoader extends android.support.v4.content.AsyncTaskLoader<boolean[]> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private String[] sensors;
    private List<Sensor> sensorList;
    private boolean[] isPresent;

    MyTaskLoader(Context context, String[] sensors, List<Sensor> sensorList) {
        super(context);
        this.context = context;
        this.sensors = sensors;
        this.sensorList = sensorList;
        isPresent = new boolean[sensors.length];

        Log.e("taskloader", "constructor");
    }

//    @Override
//    protected void onStartLoading() {
//        forceLoad();
//    }

    @Override
    public boolean[] loadInBackground() {

        Log.e("load in bg", "loading");

        SensorManager sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        PackageManager packageManager = context.getPackageManager();

        ConsumerIrManager infrared = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            infrared = (ConsumerIrManager) context.getSystemService(CONSUMER_IR_SERVICE);
        }
        for(Sensor sensor1: sensorList){
            Log.d("sensors", "HashMap: "+sensor1.getName());
        }
        for(FeatureInfo featureInfo : packageManager.getSystemAvailableFeatures()){
            Log.d("features", "FEATURE INFO: "+featureInfo.toString());
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            isPresent[0] = true;
        }

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)){
            isPresent[1] = true;
        }

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)){
            isPresent[2] = true;
        }

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI)){
            isPresent[3] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
            isPresent[4] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM)){
            isPresent[5] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)){
            isPresent[6] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)){
            isPresent[7] = true;
        }
        if(Build.getRadioVersion().equals("unknown")|| Build.getRadioVersion()!=null){
            isPresent[8] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)){
            isPresent[9] = true;
        }

        isPresent[10] = true;

        isPresent[11] = true;

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_MIDI)){
            isPresent[12] = true;
        }
        Vibrator v = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);

        if(v != null && v.hasVibrator()){
            isPresent[13] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)){
            isPresent[14] = true;
        }
        isPresent[15] = true;
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT)){
            isPresent[16] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_PROXIMITY)){
            isPresent[17] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE)){
            isPresent[18] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) : null) !=null){
            isPresent[19] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_RELATIVE_HUMIDITY)){
            isPresent[20] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            isPresent[21] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)){
            isPresent[22] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) : null) !=null){
            isPresent[23] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) : null) !=null){
            isPresent[24] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) : null) !=null){
            isPresent[25] = true;
        }
        if(infrared != null && infrared.hasIrEmitter()){
            isPresent[26] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)){
            isPresent[27] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)){
            isPresent[28] = true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT) : null) !=null){
                isPresent[29] = true;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_STATIONARY_DETECT) : null) !=null){
                isPresent[30] = true;
            }
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)){
            isPresent[31] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE)){
            isPresent[32] = true;
        }
        // Check if we're running on Android 6.0 (M) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Fingerprint API only available on from Android 6.0 (M)
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            if (!(fingerprintManager != null && fingerprintManager.isHardwareDetected())) {
                // Device doesn't support fingerprint authentication
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                // User hasn't enrolled any fingerprints to authenticate with
                isPresent[33]=true;
            } else {
                // Everything is ready for fingerprint authentication
                isPresent[33]=true;
            }
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)){
            isPresent[34] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) : null) !=null){
            isPresent[35] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
//            isPresent[14] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY)){
//            isPresent[15] = true;
//        }
////        if(packageManager.hasSystemFeature(PackageManager.FEATURE_ANT+)){
//            isPresent[24] = false;
////        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_FAKETOUCH)){
//            isPresent[31] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT)){
//            isPresent[34] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_BAROMETER)){
//            isPresent[35] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE_ECG)){
//            isPresent[35] = true;
//        }
        return isPresent;
    }

//    @Override
//    public void deliverResult(boolean[] data) {
//        if (isStarted()) {
//            // If the Loader is in a started state, deliver the results to the
//            // client. The superclass method does this for us.
//            super.deliverResult(data);
//        }
//    }

//    @Override
//    protected void onStopLoading() {
//        cancelLoad();
//    }
}
