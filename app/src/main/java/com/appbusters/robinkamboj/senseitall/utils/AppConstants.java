package com.appbusters.robinkamboj.senseitall.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.os.Build;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class AppConstants {

    public static final int REQUEST_CODE = 100;

    //generic data arguments
    public static final String RECYCLER_NAME = "RECYCLER NAME";
    public static final String DATA_NAME = "DATA NAME";
    public static final String DRAWABLE_ID = "DRAWABLE ID";
    public static final String IS_PRESENT = "IS PRESENT";
    public static final String TYPE = "TYPE";

    //data types
    public static final int TYPE_DIAGNOSTICS = 0;
    public static final int TYPE_SENSORS = 1;
    public static final int TYPE_FEATURES = 2;
    public static final int TYPE_RATE = 3;

    //data type names
    public static final String DIAGNOSTIC = "Diagnostic";
    public static final String SENSOR = "Sensor";
    public static final String FEATURE = "Feature";

    //random constants
    public static final String UNKNOWN = "unknown";

    //loader id
    public static final int LOADER_ID = 1;

    //intent extra
    public static final String PRESENT_SENSORS = "PRESENT SENSORS";
    public static final String PRESENT_FEATURES = "PRESENT FEATURES";
    public static final String PRESENT_DIAGNOSTICS = "PRESENT DIAGNOSTICS";

    //HEADERS
    public static final String SHOWING_DEVICE_TESTS = "SHOWING DEVICE TESTS";
    public static final String SHOWING_SENSORS_LIST = "SHOWING SENSORS LIST";
    public static final String SHOWING_FEATURES_LIST = "SHOWING FEATURES LIST";
    public static final String RATE_YOUR_EXPERIENCE = "RATE YOUR EXPERIENCE";

    //PREFERENCES
    public static final String PREF_FILE_NAME = "SIA";
    public static final String KEY_HEADER_TEXT = "HEADER TEXT";

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

    //diagnostics
    public static final String PROXIMITY_TEST = "Proximity Sensor Test";
    public static final String BATTERY_TEST = "Battery Test";
    public static final String HEADPHONE_JACK_TEST = "Headphone Jack Test";
    public static final String ACCELEROMETER_TEST = "Accelerometer Test";
    public static final String GPS_TEST = "GPS Test";
    public static final String BLUETOOTH_TEST = "Bluetooth Test";
    public static final String SPEAKER_VOLUME_TEST = "Speaker Volume Test";
    public static final String VIBRATOR_TEST = "Vibrator Test";
    public static final String FLASH_LIGHT_TEST = "Flashlight Test";
    public static final String WIFI_TEST = "WIFI Test";
    public static final String FINGERPRINT_TEST = "Fingerprint Sensor Test";
    public static final String MULTI_TOUCH_TEST = "Multi-Touch Test";
    public static final String FRONT_CAMERA_TEST = "Front Camera Test";
    public static final String BACK_CAMERA_TEST = "Back Camera Test";
    public static final String SCREEN_TEST = "Screen Test";
    public static final String LIGHT_TEST = "Light Sensor Test";
    public static final String COMPASS_TEST = "Compass Test";

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
    public static final String MIDI = "MIDI";
    public static final String VR_MODE = "VR Mode";

    public static HashMap<String, Integer> imageUrlMap = new HashMap<>();
    public static List<String> sensorNames = new ArrayList<>();
    public static List<String> featureNames = new ArrayList<>();
    public static List<String> diagnosticsNames = new ArrayList<>();
    public static HashMap<String, String> packageManagerPaths = new HashMap<>();
    public static HashMap<String, Integer> sensorManagerInts = new HashMap<>();
    public static HashMap<String, String> diagnosticsPointer = new HashMap<>();
    public static HashMap<String, String> reverseDiagnosticsPointer = new HashMap<>();
    public static List<String> dangerousPermissions = new ArrayList<>();
    public static HashMap<String, String> sensorMapDirections = new HashMap<>();
    public static HashMap<String, String> sensorMapHints = new HashMap<>();
    public static HashMap<String, String> sensorMapAbout = new HashMap<>();

    static {

        dangerousPermissions.add(Manifest.permission.CAMERA);
        dangerousPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        dangerousPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        dangerousPermissions.add(Manifest.permission.BODY_SENSORS);
//        dangerousPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        dangerousPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        dangerousPermissions.add(Manifest.permission.RECEIVE_WAP_PUSH);
        dangerousPermissions.add(Manifest.permission.READ_PHONE_STATE);
        dangerousPermissions.add(Manifest.permission.RECORD_AUDIO);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            dangerousPermissions.add(Manifest.permission.USE_FINGERPRINT);

        diagnosticsPointer.put(ACCELEROMETER_TEST, SENSOR_ACCELEROMETER);
        diagnosticsPointer.put(LIGHT_TEST, SENSOR_LIGHT);
        diagnosticsPointer.put(PROXIMITY_TEST, SENSOR_PROXIMITY);
        diagnosticsPointer.put(BACK_CAMERA_TEST, BACK_CAMERA);
        diagnosticsPointer.put(FRONT_CAMERA_TEST, FRONT_CAMERA);
        diagnosticsPointer.put(GPS_TEST, GPS_LOCATION);
        diagnosticsPointer.put(WIFI_TEST, WIFI);
        diagnosticsPointer.put(BLUETOOTH_TEST, BLUETOOTH);
        diagnosticsPointer.put(SCREEN_TEST, SCREEN);
        diagnosticsPointer.put(BATTERY_TEST, BATTERY);
        diagnosticsPointer.put(SPEAKER_VOLUME_TEST, SOUND);
        diagnosticsPointer.put(VIBRATOR_TEST, VIBRATOR);
        diagnosticsPointer.put(HEADPHONE_JACK_TEST, AV_OUTPUTS);
        diagnosticsPointer.put(FLASH_LIGHT_TEST, FLASH);
        diagnosticsPointer.put(MULTI_TOUCH_TEST, MULTI_TOUCH);
        diagnosticsPointer.put(FINGERPRINT_TEST, FINGERPRINT);
        diagnosticsPointer.put(COMPASS_TEST, COMPASS);

        reverseDiagnosticsPointer.put(SENSOR_ACCELEROMETER, ACCELEROMETER_TEST);
        reverseDiagnosticsPointer.put(SENSOR_LIGHT, LIGHT_TEST);
        reverseDiagnosticsPointer.put(SENSOR_PROXIMITY, PROXIMITY_TEST);
        reverseDiagnosticsPointer.put(BACK_CAMERA, BACK_CAMERA_TEST);
        reverseDiagnosticsPointer.put(FRONT_CAMERA, FRONT_CAMERA_TEST);
        reverseDiagnosticsPointer.put(GPS_LOCATION, GPS_TEST);
        reverseDiagnosticsPointer.put(WIFI, WIFI_TEST);
        reverseDiagnosticsPointer.put(BLUETOOTH, BLUETOOTH_TEST);
        reverseDiagnosticsPointer.put(SCREEN, SCREEN_TEST);
        reverseDiagnosticsPointer.put(BATTERY, BATTERY_TEST);
        reverseDiagnosticsPointer.put(SOUND, SPEAKER_VOLUME_TEST);
        reverseDiagnosticsPointer.put(VIBRATOR, VIBRATOR_TEST);
        reverseDiagnosticsPointer.put(AV_OUTPUTS, HEADPHONE_JACK_TEST);
        reverseDiagnosticsPointer.put(FLASH, FLASH_LIGHT_TEST);
        reverseDiagnosticsPointer.put(MULTI_TOUCH, MULTI_TOUCH_TEST);
        reverseDiagnosticsPointer.put(FINGERPRINT, FINGERPRINT_TEST);
        reverseDiagnosticsPointer.put(COMPASS, COMPASS_TEST);

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


        //sensors
        diagnosticsNames.add(ACCELEROMETER_TEST);
        diagnosticsNames.add(LIGHT_TEST);
        diagnosticsNames.add(PROXIMITY_TEST);
        //features
        diagnosticsNames.add(BACK_CAMERA_TEST);
        diagnosticsNames.add(FRONT_CAMERA_TEST);
        diagnosticsNames.add(GPS_TEST);
        diagnosticsNames.add(WIFI_TEST);
        diagnosticsNames.add(BLUETOOTH_TEST);
        diagnosticsNames.add(SCREEN_TEST);
        diagnosticsNames.add(BATTERY_TEST);
        diagnosticsNames.add(SPEAKER_VOLUME_TEST);
        diagnosticsNames.add(VIBRATOR_TEST);
        diagnosticsNames.add(HEADPHONE_JACK_TEST);
        diagnosticsNames.add(FLASH_LIGHT_TEST);
        diagnosticsNames.add(MULTI_TOUCH_TEST);
        diagnosticsNames.add(FINGERPRINT_TEST);
        diagnosticsNames.add(COMPASS_TEST);


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

        imageUrlMap.put(SENSOR_LIGHT, R.drawable.baseline_highlight_black_48);
        imageUrlMap.put(SENSOR_PROXIMITY, R.drawable.baseline_pan_tool_black_48);
        imageUrlMap.put(SENSOR_TEMPERATURE, R.drawable.baseline_brightness_5_black_48);
        imageUrlMap.put(SENSOR_PRESSURE, R.drawable.baseline_terrain_black_48);
        imageUrlMap.put(SENSOR_ACCELEROMETER, R.drawable.baseline_trending_up_black_48);
        imageUrlMap.put(SENSOR_RELATIVE_HUMIDITY, R.drawable.baseline_scatter_plot_black_48);
        imageUrlMap.put(SENSOR_GYROSCOPE, R.drawable.baseline_360_black_48);
        imageUrlMap.put(SENSOR_GRAVITY, R.drawable.baseline_system_update_black_48);
        imageUrlMap.put(SENSOR_LINEAR_ACCELERATION, R.drawable.baseline_trending_flat_black_48);
        imageUrlMap.put(SENSOR_ROTATION_VECTOR, R.drawable.baseline_360_black_48);
        imageUrlMap.put(SENSOR_STEP_DETECTOR, R.drawable.baseline_directions_walk_black_48);
        imageUrlMap.put(SENSOR_STEP_COUNTER, R.drawable.baseline_directions_run_black_48);
        imageUrlMap.put(SENSOR_MOTION_DETECTOR, R.drawable.baseline_directions_bike_black_48);
        imageUrlMap.put(SENSOR_STATIONARY_DETECTOR, R.drawable.baseline_airline_seat_recline_normal_black_48);
        imageUrlMap.put(SENSOR_MAGNETIC_FIELD, R.drawable.baseline_public_black_48);
        imageUrlMap.put(SENSOR_HEART_RATE, R.drawable.baseline_favorite_black_48);
        imageUrlMap.put(BACK_CAMERA, R.drawable.baseline_camera_rear_black_48);
        imageUrlMap.put(FRONT_CAMERA, R.drawable.baseline_camera_front_black_48);
        imageUrlMap.put(GPS_LOCATION, R.drawable.baseline_gps_fixed_black_48);
        imageUrlMap.put(WIFI, R.drawable.baseline_network_wifi_black_48);
        imageUrlMap.put(BLUETOOTH, R.drawable.baseline_bluetooth_black_48);
        imageUrlMap.put(GSM_UMTS, R.drawable.baseline_sim_card_black_48);
        imageUrlMap.put(COMPASS, R.drawable.baseline_compass_calibration_black_48);
        imageUrlMap.put(RADIO, R.drawable.baseline_radio_black_48);
        imageUrlMap.put(SCREEN, R.drawable.baseline_smartphone_black_48);
        imageUrlMap.put(BATTERY, R.drawable.baseline_battery_alert_black_48);
        imageUrlMap.put(CPU, R.drawable.baseline_memory_black_48);
        imageUrlMap.put(SOUND, R.drawable.baseline_volume_off_black_48);
        imageUrlMap.put(VIBRATOR, R.drawable.baseline_vibration_black_48);
        imageUrlMap.put(AV_OUTPUTS, R.drawable.baseline_headset_mic_black_48);
        imageUrlMap.put(ANDROID_OS, R.drawable.baseline_adb_black_48);
        imageUrlMap.put(FLASH, R.drawable.baseline_flash_on_black_48);
        imageUrlMap.put(INFRARED, R.drawable.baseline_wb_sunny_black_48);
        imageUrlMap.put(MULTI_TOUCH, R.drawable.baseline_settings_cell_black_48);
        imageUrlMap.put(FINGERPRINT, R.drawable.baseline_fingerprint_black_48);
        imageUrlMap.put(NFC, R.drawable.baseline_nfc_black_48);
        imageUrlMap.put(MICROPHONE, R.drawable.baseline_mic_none_black_48);
        imageUrlMap.put(USB_ACCESSORY, R.drawable.baseline_usb_black_48);
        imageUrlMap.put(BAROMETER, R.drawable.baseline_pool_black_48);
        imageUrlMap.put(WIFI_DIRECT, R.drawable.baseline_cast_black_48);
        imageUrlMap.put(HEART_RATE_ECG, R.drawable.baseline_favorite_border_black_48);
        imageUrlMap.put(FAKE_TOUCH, R.drawable.baseline_touch_app_black_48);
        imageUrlMap.put(WEB_VIEW, R.drawable.baseline_web_black_48);
        imageUrlMap.put(MIDI, R.drawable.baseline_speaker_black_48);
        imageUrlMap.put(VR_MODE, R.drawable.baseline_videogame_asset_black_48);

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



        sensorMapDirections.put(
                SENSOR_PROXIMITY,
                "Place Your Hand Above Proximity Sensor To Increment Counter."
        );
        sensorMapDirections.put(
                SENSOR_ACCELEROMETER,
                "Shake Your Mobile Phone To Switch Screen colors."
        );
        sensorMapDirections.put(
                SENSOR_LIGHT,
                "Point Your Light Sensor Towards Light To Observe Bar Color Change."
        );
        sensorMapDirections.put(
                BACK_CAMERA,
                "You Should See Back Camera Preview In The Test"
        );
        sensorMapDirections.put(
                FRONT_CAMERA,
                "You Should See Front Camera Preview In The Test"
        );
        sensorMapDirections.put(
                GPS_LOCATION,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                WIFI,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                BLUETOOTH,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                SCREEN,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                BATTERY,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                SOUND,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                VIBRATOR,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                AV_OUTPUTS,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                FLASH,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                MULTI_TOUCH,
                "You Should See Your Location In The Test"
        );
        sensorMapDirections.put(
                FINGERPRINT,
                "You Should See Your Location In The Test"
        );


        sensorMapHints.put(
                SENSOR_PROXIMITY,
                "A proximity sensor is usually located at the top of a mobile screen."
        );
        sensorMapHints.put(
                SENSOR_ACCELEROMETER,
                "Enjoy the color switch."
        );
        sensorMapHints.put(
                SENSOR_LIGHT,
                "A light sensor is usually located at the top of a mobile screen."
        );
        sensorMapHints.put(
                BACK_CAMERA,
                ""
        );
        sensorMapHints.put(
                FRONT_CAMERA,
                ""
        );
        sensorMapHints.put(
                GPS_LOCATION,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                WIFI,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                BLUETOOTH,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                SCREEN,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                BATTERY,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                SOUND,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                VIBRATOR,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                AV_OUTPUTS,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                FLASH,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                MULTI_TOUCH,
                "Tap the marker for location coordinates."
        );
        sensorMapHints.put(
                FINGERPRINT,
                "Tap the marker for location coordinates."
        );


        sensorMapAbout.put(
                SENSOR_PROXIMITY,
                "Proximity sensing technology is becoming popular across a wide range of industries, especially in consumer electronics, because OEMs introduce new products to market every year. The largest application within consumer electronics is the mobile phone. Given the competition in this particular market, it is important for OEMs to adopt the latest technology and offer unique features to differentiate their products."
        );
        sensorMapAbout.put(
                SENSOR_ACCELEROMETER,
                "Accelerometers in mobile phones are used to detect the orientation of the phone. The gyroscope, or gyro for short, adds an additional dimension to the information supplied by the accelerometer by tracking rotation or twist." + "An accelerometer measures linear acceleration of movement, while a gyro on the other hand measures the angular rotational velocity. Both sensors measure rate of change; they just measure the rate of change for different things." + "In practice, that means that an accelerometer will measure the directional movement of a device but will not be able to resolve its lateral orientation or tilt during that movement accurately unless a gyro is there to fill in that info."
        );
        sensorMapAbout.put(
                SENSOR_LIGHT,
                "Ambient light sensors are used as backlighting controls in any number of LCD display applications from consumer electronics to automotive, and by automatically adjusting display brightness, they can conserve battery life, which is a key benefit in mobile device applications. In addition, these sensors work well in all kinds of light sources from natural sunlight to fluorescent and incandescent lamps." + "One common theme running throughout most of these recent product launches is that these ambient light sensors match or virtually match the requirements of the human eye. This is a big deal when it comes to reducing eye strain. These include sensors from AMI Semiconductor, Avago, Capella, Osram, and Vishay"
        );
        sensorMapAbout.put(
                BACK_CAMERA,
                ""
        );
        sensorMapAbout.put(
                FRONT_CAMERA,
                ""
        );
        sensorMapAbout.put(
                GPS_LOCATION,
                ""
        );
        sensorMapAbout.put(
                WIFI,
                ""
        );
        sensorMapAbout.put(
                BLUETOOTH,
                ""
        );
        sensorMapAbout.put(
                SCREEN,
                ""
        );
        sensorMapAbout.put(
                BATTERY,
                ""
        );
        sensorMapAbout.put(
                SOUND,
                ""
        );
        sensorMapAbout.put(
                VIBRATOR,
                ""
        );
        sensorMapAbout.put(
                AV_OUTPUTS,
                ""
        );
        sensorMapAbout.put(
                FLASH,
                ""
        );
        sensorMapAbout.put(
                MULTI_TOUCH,
                ""
        );
        sensorMapAbout.put(
                FINGERPRINT,
                ""
        );
    }
}
