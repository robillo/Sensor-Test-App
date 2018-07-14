package com.appbusters.robinkamboj.senseitall.utils;

import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class AppConstants {

    //random constants
    public static final String UNKNOWN = "unknown";

    //loader id
    public static final int LOADER_ID = 1;

    //intent extra
    public static final String PRESENT_SENSORS = "PRESENT SENSORS";
    public static final String PRESENT_FEATURES = "PRESENT FEATURES";

    //sensors
    public static final String SENSOR_LIGHT = "Light Sensor";
    public static final String SENSOR_PROXIMITY = "Proximity Sensor";
    public static final String SENSOR_TEMPERATURE = "Ambient Temperature Sensor";
    public static final String SENSOR_PRESSURE = "Pressure Sensor";
    public static final String SENSOR_ACCELEROMETER = "Accelerometer";
    public static final String SENSOR_RELATIVE_HUMIDITY = "Relative Humidity";
    public static final String SENSOR_GYROSCOPE = "Gyroscope";
    public static final String SENSOR_GRAVITY = "Gravity";
    public static final String SENSOR_LINEAR_ACCELERATION = "Linear Acceleration";
    public static final String SENSOR_ROTATION_VECTOR = "Rotation Vector";
    public static final String SENSOR_STEP_DETECTOR = "Step Detector";
    public static final String SENSOR_STEP_COUNTER = "Step Counter";
    public static final String SENSOR_MOTION_DETECTOR = "Motion Detector";
    public static final String SENSOR_STATIONARY_DETECTOR = "Stationary Detector";
    public static final String SENSOR_MAGNETIC_FIELD = "Magnetic Field";
    public static final String SENSOR_HEART_RATE = "Heart Rate Sensor";

    //features
    public static final String BACK_CAMERA = "Back Camera";
    public static final String FRONT_CAMERA = "Front Camera";
    public static final String GPS_LOCATION = "GPS Location";
    public static final String WIFI = "WiFi";
    public static final String BLUETOOTH = "BlueTooth";
    public static final String GSM_UMTS = "GSM/UMTS";
    public static final String COMPASS = "Compass";
    public static final String RADIO = "Radio";
    public static final String SCREEN = "Screen";
    public static final String BATTERY = "Battery";
    public static final String CPU = "CPU";
    public static final String SOUND = "Sound";
    public static final String VIBRATOR = "Vibrator";
    public static final String AV_OUTPUTS = "Audio/Video Outputs";
    public static final String ANDROID_OS = "Android OS";
    public static final String FLASH = "Flash";
    public static final String INFRARED = "Infrared";
    public static final String MULTI_TOUCH = "Multi-Touch";
    public static final String FINGERPRINT = "Fingerprint Sensor";
    public static final String NFC = "NFC";
    public static final String MICROPHONE = "Microphone Feature";
    public static final String USB_ACCESSORY = "USB Accessory Feature";
    public static final String BAROMETER = "Barometer Sensor";
    public static final String WIFI_DIRECT = "WIFI Direct";
    public static final String HEART_RATE_ECG = "Heart Rate ECG";
    public static final String FAKE_TOUCH = "Fake-Touch";
    public static final String WEB_VIEW = "Web-View";
    public static final String HDMI = "HDMI";
    public static final String MIDI = "MIDI";
    public static final String VR_MODE = "VR Mode";

    public static List<String> sensorNames = new ArrayList<>();
    public static List<String> featureNames = new ArrayList<>();
    public static HashMap<String, String> imageUrlMap = new HashMap<>();
    public static HashMap<String, String> packageManagerPaths = new HashMap<>();
    public static HashMap<String, Integer> sensorManagerInts = new HashMap<>();

    static {
        sensorNames.add(SENSOR_ACCELEROMETER);
        sensorNames.add(SENSOR_LIGHT);
        sensorNames.add(SENSOR_PROXIMITY);
        sensorNames.add(SENSOR_TEMPERATURE);
        sensorNames.add(SENSOR_PRESSURE);
        sensorNames.add(SENSOR_RELATIVE_HUMIDITY);
        sensorNames.add(SENSOR_GYROSCOPE);
        sensorNames.add(SENSOR_GRAVITY);
        sensorNames.add(SENSOR_LINEAR_ACCELERATION);
        sensorNames.add(SENSOR_ROTATION_VECTOR);
        sensorNames.add(SENSOR_MAGNETIC_FIELD);
        sensorNames.add(SENSOR_HEART_RATE);
        sensorNames.add(SENSOR_STEP_DETECTOR);
        sensorNames.add(SENSOR_STEP_COUNTER);
        sensorNames.add(SENSOR_MOTION_DETECTOR);
        sensorNames.add(SENSOR_STATIONARY_DETECTOR);

        featureNames.add(BACK_CAMERA);
        featureNames.add(FRONT_CAMERA);
        featureNames.add(GPS_LOCATION);
        featureNames.add(WIFI);
        featureNames.add(BLUETOOTH);
        featureNames.add(GSM_UMTS);
        featureNames.add(COMPASS);
        featureNames.add(RADIO);
        featureNames.add(SCREEN);
        featureNames.add(BATTERY);
        featureNames.add(CPU);
        featureNames.add(SOUND);
        featureNames.add(VIBRATOR);
        featureNames.add(AV_OUTPUTS);
        featureNames.add(ANDROID_OS);
        featureNames.add(FLASH);
        featureNames.add(INFRARED);
        featureNames.add(MULTI_TOUCH);
        featureNames.add(FINGERPRINT);
        featureNames.add(NFC);
        featureNames.add(MICROPHONE);
        featureNames.add(USB_ACCESSORY);
        featureNames.add(BAROMETER);
        featureNames.add(WIFI_DIRECT);
        featureNames.add(HEART_RATE_ECG);
        featureNames.add(FAKE_TOUCH);
        featureNames.add(WEB_VIEW);
        featureNames.add(MIDI);
        featureNames.add(VR_MODE);

        packageManagerPaths.put(BACK_CAMERA, PackageManager.FEATURE_CAMERA);
        packageManagerPaths.put(FRONT_CAMERA, PackageManager.FEATURE_CAMERA_FRONT);
        packageManagerPaths.put(GPS_LOCATION, PackageManager.FEATURE_LOCATION_GPS);
        packageManagerPaths.put(WIFI, PackageManager.FEATURE_WIFI);
        packageManagerPaths.put(BLUETOOTH, PackageManager.FEATURE_BLUETOOTH);
        packageManagerPaths.put(GSM_UMTS, PackageManager.FEATURE_TELEPHONY_GSM);
        packageManagerPaths.put(COMPASS, PackageManager.FEATURE_SENSOR_COMPASS);
        packageManagerPaths.put(SCREEN, PackageManager.FEATURE_TOUCHSCREEN);
        packageManagerPaths.put(AV_OUTPUTS, PackageManager.FEATURE_AUDIO_OUTPUT);
        packageManagerPaths.put(FLASH, PackageManager.FEATURE_CAMERA_FLASH);
        packageManagerPaths.put(MULTI_TOUCH, PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
        packageManagerPaths.put(NFC, PackageManager.FEATURE_NFC);
        packageManagerPaths.put(MICROPHONE, PackageManager.FEATURE_MICROPHONE);
        packageManagerPaths.put(USB_ACCESSORY, PackageManager.FEATURE_USB_ACCESSORY);
        packageManagerPaths.put(BAROMETER, PackageManager.FEATURE_SENSOR_BAROMETER);
        packageManagerPaths.put(WIFI_DIRECT, PackageManager.FEATURE_WIFI_DIRECT);
        packageManagerPaths.put(HEART_RATE_ECG, PackageManager.FEATURE_SENSOR_HEART_RATE_ECG);
        packageManagerPaths.put(FAKE_TOUCH, PackageManager.FEATURE_FAKETOUCH);
        packageManagerPaths.put(WEB_VIEW, PackageManager.FEATURE_WEBVIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            packageManagerPaths.put(FINGERPRINT, PackageManager.FEATURE_FINGERPRINT);
            packageManagerPaths.put(MIDI, PackageManager.FEATURE_MIDI);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            packageManagerPaths.put(VR_MODE, PackageManager.FEATURE_VR_MODE);

        //RADIO
        //BATTERY
        //CPU
        //SOUND
        //VIBRATOR
        //INFRARED
        //ANDROID OS

        sensorManagerInts.put(SENSOR_ACCELEROMETER, Sensor.TYPE_ACCELEROMETER);
        sensorManagerInts.put(SENSOR_LIGHT, Sensor.TYPE_LIGHT);
        sensorManagerInts.put(SENSOR_PROXIMITY, Sensor.TYPE_PROXIMITY);
        sensorManagerInts.put(SENSOR_TEMPERATURE, Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManagerInts.put(SENSOR_PRESSURE, Sensor.TYPE_PRESSURE);
        sensorManagerInts.put(SENSOR_RELATIVE_HUMIDITY, Sensor.TYPE_RELATIVE_HUMIDITY);
        sensorManagerInts.put(SENSOR_GYROSCOPE, Sensor.TYPE_GYROSCOPE);
        sensorManagerInts.put(SENSOR_GRAVITY, Sensor.TYPE_GRAVITY);
        sensorManagerInts.put(SENSOR_LINEAR_ACCELERATION, Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManagerInts.put(SENSOR_ROTATION_VECTOR, Sensor.TYPE_ROTATION_VECTOR);
        sensorManagerInts.put(SENSOR_MAGNETIC_FIELD, Sensor.TYPE_MAGNETIC_FIELD);
        sensorManagerInts.put(SENSOR_HEART_RATE, Sensor.TYPE_HEART_RATE);
        sensorManagerInts.put(SENSOR_STEP_DETECTOR, Sensor.TYPE_STEP_DETECTOR);
        sensorManagerInts.put(SENSOR_STEP_COUNTER, Sensor.TYPE_STEP_COUNTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sensorManagerInts.put(SENSOR_MOTION_DETECTOR, Sensor.TYPE_MOTION_DETECT);
            sensorManagerInts.put(SENSOR_STATIONARY_DETECTOR, Sensor.TYPE_STATIONARY_DETECT);
        }
    }
}
