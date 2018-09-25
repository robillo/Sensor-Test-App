package com.appbusters.robinkamboj.senseitall.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.os.Build;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppConstants {

    public final String vr_package_name = "com.robillo.virtualrealitysample_senseitall";
    public final String vr_intent_filter = "com.robillo.virtualrealitysample_senseitall.VR_TESTS";

    public static final String ml_package_name = "com.assistiveapps.machinelearningtests";
    public static final String face_detect_intent_filter = "com.assistiveapps.machinelearningtests.tests.face_detect";
    public static final String image_label_intent_filter = "com.assistiveapps.machinelearningtests.tests.image_label";
    public static final String text_scan_intent_filter = "com.assistiveapps.machinelearningtests.tests.text_scan";
    public static final String barcode_scan_intent_filter = "com.assistiveapps.machinelearningtests.tests.barcode_scan";

    public static final int REQUEST_CODE = 100;
    public static final int REQUEST_CODE_PICK_IMAGE = 101;
    public static final int REQUEST_CODE_CAPTURE_IMAGE = 102;
    public static final int REQUEST_CHECK_SETTINGS = 103;
    public static final String CHOOSER_INTENT_TITLE = "Select Image";
    public static final String IMAGE_CONTENT_TYPE = "image/*";
    public static final String CATEGORY = "CATEGORY";
    public static final int INFO_RECYCLER_COUNT = 4;
    public static final int FLING_VELOCITY = 3000;

    //storage constants
    public static final String FREE_INTERNAL_STORAGE = "Free Internal Storage";
    public static final String TOTAL_INTERNAL_STORAGE = "Total Internal Storage Excluding System-Used Storage";
    public static final String IS_SD_CARD_MOUNTED = "Is SD Card Mounted?";
    public static final String FREE_EXTERNAL_STORAGE = "Free External Storage";
    public static final String TOTAL_EXTERNAL_STORAGE = "Total External Storage Excluding SD Card Specific Storage";

    //ram constants
    public static final String PERCENT_AVAILABLE_MEMORY = "Percent Memory Available";
    public static final String AVAILABLE_MEMORY = "Available Memory";
    public static final String TOTAL_MEMORY = "Total Memory Excluding System-Used Memory";
    public static final String MEMORY_THRESHOLD = "Memory Threshold";
    public static final String IS_LOW_MEMORY = "Is Low Memory?";
    public static final String DESCRIPTION_COUNT = "Description Count";

    public static final String RECYCLER_NAME = "RECYCLER NAME";                 //generic data arguments
    public static final String DATA_NAME = "DATA NAME";
    public static final String DRAWABLE_ID = "DRAWABLE ID";
    public static final String IS_PRESENT = "IS PRESENT";
    public static final String TYPE = "TYPE";

    public static final int TYPE_DIAGNOSTICS = 0;                               //data types
    public static final int TYPE_SENSORS = 1;
    public static final int TYPE_FEATURES = 2;
    public static final int TYPE_INFORMATION = 3;
    public static final int TYPE_SOFTWARE = 4;
    public static final int TYPE_ANDROID = 5;
//    public static final int TYPE_RATE = 6;

    public static final String DIAGNOSTIC = "Diagnostic";                       //data type names
    public static final String SENSOR = "Electronic Sensors";
    public static final String FEATURE = "Everyday Features";
//    public static final String RATE_APP = "Rate App";
    public static final String INFORMATION = "Device Details";                 //redundant
    public static final String SOFTWARE = "Modern Features";
    public static final String ANDROID = "Android Properties";

    public static final String UNKNOWN = "unknown";                             //random constants

    public static final int LOADER_ID = 1;                                      //loader id

//    public static final String PRESENT_SENSORS = "PRESENT SENSORS";             //intent extra
//    public static final String PRESENT_FEATURES = "PRESENT FEATURES";
//    public static final String PRESENT_DIAGNOSTICS = "PRESENT DIAGNOSTICS";

    public static final String SHOWING_DEVICE_TESTS = "DEVICE TESTS";   //HEADERS
    public static final String SHOWING_SENSORS_LIST = "SENSORS LIST";
    public static final String SHOWING_FEATURES_LIST = "FEATURES LIST";
    public static final String SHOWING_INFORMATION_LIST = "INFORMATION LIST";
    public static final String SHOWING_SOFTWARE_LIST = "SOFTWARE LIST";
    public static final String SHOWING_ANDROID_FEATURE_LIST = "SOFTWARE FEATURES LIST";
//    public static final String RATE_YOUR_EXPERIENCE = "RATE YOUR EXPERIENCE";

    public static final String PREF_FILE_NAME = "SIA";                          //PREFERENCES
    public static final String KEY_HEADER_TEXT = "HEADER TEXT";

    public static final String SENSOR_LIGHT = "Light Sensor";                   //sensors
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

    public static final String BACK_CAMERA = "Back Camera";                     //features
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

    public static final String STORAGE = "STORAGE";     //specific information
    public static final String RAM = "RAM";

    @SuppressWarnings("WeakerAccess")
    public static final String PIE = "P - Pie (9)";     //android versions
    @SuppressWarnings("WeakerAccess")
    public static final String OREO = "O - Oreo (8)";
    @SuppressWarnings("WeakerAccess")
    public static final String NOUGAT = "N - Nougat (7)";
    @SuppressWarnings("WeakerAccess")
    public static final String MARSHMALLOW = "M - Marshmallow (6)";
    @SuppressWarnings("WeakerAccess")
    public static final String LOLLIPOP = "L - Lollipop (5)";
    @SuppressWarnings("WeakerAccess")
    public static final String KITKAT = "K - Kitkat (4)";

    public static final String MOTION_DETECT = "Motion Detect";             //software
    public static final String AUGMENTED_REALITY = "Augmented Reality";
    public static final String VIRTUAL_REALITY = "Virtual Reality";
    public static final String FACE_DETECT = "Face Detect";
    public static final String FACE_EMOJI = "Face Emoji";
    public static final String BARCODE_READER = "Barcode Reader";
    public static final String TEXT_SCAN = "Text Scan";
    public static final String LABEL_GENERATOR = "Label Generator";

    //keys for key statistics
    public static final String STANDARD_GRAVITY = "Standard Gravity";
    public static final String VENDOR = "Vendor";
    public static final String RESOLUTION = "Resolution";
    public static final String MINIMUM_DELAY = "Minimum Delay";
    public static final String MAXIMUM_DELAY = "Maximum Delay";
    public static final String POWER = "Power";
    public static final String MAXIMUM_RANGE = "Maximum Range";
    public static final String VERSION = "Version";
    public static final String IS_WAKE_UP_SENSOR = "Is Wake Up Sensor";
    public static final String REPORTING_MODE = "Reporting Mode";
    public static final String IS_DYNAMIC_SENSOR = "Is Dynamic Sensor";

    public static final String SUN_GRAVITY = "Sun's Gravity";
    public static final String MOON_GRAVITY = "Moon's Gravity";
    public static final String MERCURY_GRAVITY = "Mercury's Gravity";
    public static final String VENUS_GRAVITY = "Venus' Gravity";
    public static final String EARTH_GRAVITY = "Earth's Gravity";
    public static final String MARS_GRAVITY = "Mars' Gravity";
    public static final String JUPITER_GRAVITY = "Jupiter's Gravity";
    public static final String SATURN_GRAVITY = "Saturn's Gravity";
    public static final String URANUS_GRAVITY = "Uranus' Gravity";
    public static final String NEPTUNE_GRAVITY = "Neptune's Gravity";
    public static final String PLUTO_GRAVITY = "Pluto's Gravity";

    public static final String MAX_SUNLIGHT_LUMINANCE = "Max Sunlight Luminance";
    public static final String SUNLIGHT_LUMINANCE = "Sunlight Luminance";
    public static final String SHADE_LUMINANCE = "Luminance In Shade";
    public static final String OVERCAST_SKY_LUMINANCE = "Luminance Under Overcast Sky";
    public static final String CLEAR_SKY_LUMINANCE = "Luminance Under Clear Sky";
    public static final String SUNRISE_LUMINANCE = "Luminance At Sunrise";
    public static final String FULL_MOON_LUMINANCE = "Luminance During Full Moon";
    public static final String NO_MOON_LUMINANCE = "Luminace During No Moon";

    public static final String MAGNETIC_FIELD_EARTH_MAX = "Magnetic Field Earth Max";
    public static final String MAGNETIC_FIELD_EARTH_MIN = "Magnetic Field Earth Min";

    public static final String STANDARD_PRESSURE_ATMOSPHERE = "Standard Pressure Atmosphere";

    public static HashMap<String, Integer> imageUrlMap = new HashMap<>();

    public static List<Category> categories = new ArrayList<>();

    public static List<String> diagnosticsNames = new ArrayList<>();
    public static List<String> sensorNames = new ArrayList<>();
    public static List<String> featureNames = new ArrayList<>();
    public static List<String> informationNames = new ArrayList<>();
    public static List<String> softwareNames = new ArrayList<>();
    public static List<String> androidNames = new ArrayList<>();

    public static HashMap<String, Boolean> isTestMap = new HashMap<>();

    public static HashMap<String, String> packageManagerPaths = new HashMap<>();
    public static HashMap<String, Integer> sensorManagerInts = new HashMap<>();
    public static List<String> dangerousPermissions = new ArrayList<>();
    public static HashMap<String, String> sensorMapDirections = new HashMap<>();
    public static HashMap<String, String> sensorMapHints = new HashMap<>();
    public static HashMap<String, Integer> mapAbout = new HashMap<>();
//    public static HashMap<String, String> versionMapUri = new HashMap<>();

    static {

        dangerousPermissions.add(Manifest.permission.CAMERA);
        dangerousPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        dangerousPermissions.add(Manifest.permission.BODY_SENSORS);
        dangerousPermissions.add(Manifest.permission.READ_PHONE_STATE);
        dangerousPermissions.add(Manifest.permission.RECORD_AUDIO);
        dangerousPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        dangerousPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        diagnosticsNames.add(SENSOR_ACCELEROMETER);
        diagnosticsNames.add(SENSOR_LIGHT);
        diagnosticsNames.add(SENSOR_PROXIMITY);
        diagnosticsNames.add(SENSOR_PRESSURE);
        diagnosticsNames.add(SENSOR_GYROSCOPE);
        diagnosticsNames.add(SENSOR_GRAVITY);
        diagnosticsNames.add(SENSOR_LINEAR_ACCELERATION);
        diagnosticsNames.add(SENSOR_ROTATION_VECTOR);
        diagnosticsNames.add(SENSOR_MAGNETIC_FIELD);
        diagnosticsNames.add(BACK_CAMERA);
        diagnosticsNames.add(FRONT_CAMERA);
        diagnosticsNames.add(GPS_LOCATION);
//        diagnosticsNames.add(WIFI);
//        diagnosticsNames.add(BLUETOOTH);
        diagnosticsNames.add(COMPASS);
        diagnosticsNames.add(SCREEN);
        diagnosticsNames.add(BATTERY);
        diagnosticsNames.add(SOUND);
        diagnosticsNames.add(VIBRATOR);
        diagnosticsNames.add(AV_OUTPUTS);
        diagnosticsNames.add(FLASH);
        diagnosticsNames.add(MULTI_TOUCH);
        diagnosticsNames.add(FINGERPRINT);

        featureNames.add(BACK_CAMERA);  //d
        featureNames.add(FRONT_CAMERA);  //d
        featureNames.add(GSM_UMTS);
        featureNames.add(COMPASS);  //d
        featureNames.add(RADIO);
        featureNames.add(SCREEN);  //d
        featureNames.add(BATTERY);  //d
        featureNames.add(CPU);
        featureNames.add(SOUND);  //d
        featureNames.add(VIBRATOR);  //d
        featureNames.add(AV_OUTPUTS);  //d
        featureNames.add(ANDROID_OS);
        featureNames.add(FLASH);  //d
        featureNames.add(MULTI_TOUCH);  //d
        featureNames.add(FINGERPRINT);  //d
        featureNames.add(WIFI);  //d
        featureNames.add(BLUETOOTH);  //d
        featureNames.add(GPS_LOCATION);  //d
        featureNames.add(NFC);
        featureNames.add(MICROPHONE);
        featureNames.add(USB_ACCESSORY);
        featureNames.add(HEART_RATE_ECG);
        featureNames.add(FAKE_TOUCH);
        featureNames.add(MIDI);

        informationNames.add(GSM_UMTS);
        informationNames.add(ANDROID_OS);
        informationNames.add(CPU);
        informationNames.add(STORAGE);
        informationNames.add(RAM);
        informationNames.add(RADIO);
        informationNames.add(FAKE_TOUCH);

        androidNames.add(PIE);
        androidNames.add(OREO);
        androidNames.add(NOUGAT);
        androidNames.add(MARSHMALLOW);
        androidNames.add(LOLLIPOP);
        androidNames.add(KITKAT);

        isTestMap.put(BACK_CAMERA, true);
        isTestMap.put(FRONT_CAMERA, true);
        isTestMap.put(GPS_LOCATION, true);
        isTestMap.put(WIFI, true);
        isTestMap.put(BLUETOOTH, true);
        isTestMap.put(GSM_UMTS, false);
        isTestMap.put(COMPASS, true);
        isTestMap.put(RADIO, false);
        isTestMap.put(SCREEN, true);
        isTestMap.put(BATTERY, true);
        isTestMap.put(CPU, false);
        isTestMap.put(SOUND, true);
        isTestMap.put(VIBRATOR, true);
        isTestMap.put(AV_OUTPUTS, true);
        isTestMap.put(ANDROID_OS, false);
        isTestMap.put(FLASH, true);
        isTestMap.put(MULTI_TOUCH, true);
        isTestMap.put(FINGERPRINT, true);
        isTestMap.put(NFC, false);
        isTestMap.put(MICROPHONE, false);
        isTestMap.put(USB_ACCESSORY, false);
        isTestMap.put(HEART_RATE_ECG, false);
        isTestMap.put(FAKE_TOUCH, false);
        isTestMap.put(MIDI, false);
        isTestMap.put(SENSOR_ACCELEROMETER, true);
        isTestMap.put(SENSOR_LIGHT, true);
        isTestMap.put(SENSOR_PROXIMITY, true);
        isTestMap.put(SENSOR_TEMPERATURE, false);
        isTestMap.put(SENSOR_PRESSURE, true);
        isTestMap.put(SENSOR_RELATIVE_HUMIDITY, false);
        isTestMap.put(SENSOR_GYROSCOPE, true);
        isTestMap.put(SENSOR_GRAVITY, true);
        isTestMap.put(SENSOR_LINEAR_ACCELERATION, true);
        isTestMap.put(SENSOR_ROTATION_VECTOR, true);
        isTestMap.put(SENSOR_MAGNETIC_FIELD, true);
        isTestMap.put(SENSOR_HEART_RATE, false);
        isTestMap.put(SENSOR_MOTION_DETECTOR, false);
        isTestMap.put(SENSOR_STATIONARY_DETECTOR, false);
        isTestMap.put(STORAGE, false);
        isTestMap.put(RAM, false);
        isTestMap.put(MOTION_DETECT, true);
        isTestMap.put(FACE_DETECT, true);
        isTestMap.put(BARCODE_READER, true);
        isTestMap.put(TEXT_SCAN, true);
        isTestMap.put(FACE_EMOJI, true);
        isTestMap.put(AUGMENTED_REALITY, true);
        isTestMap.put(VIRTUAL_REALITY, true);
        isTestMap.put(LABEL_GENERATOR, true);
        isTestMap.put(SENSOR_STEP_DETECTOR, true);
        isTestMap.put(SENSOR_STEP_COUNTER, true);

        sensorNames.add(SENSOR_LIGHT);  //d
        sensorNames.add(SENSOR_ACCELEROMETER);  //d
        sensorNames.add(SENSOR_GYROSCOPE);  //d
        sensorNames.add(SENSOR_GRAVITY);  //d
        sensorNames.add(SENSOR_LINEAR_ACCELERATION);  //d
        sensorNames.add(SENSOR_ROTATION_VECTOR);  //d
        sensorNames.add(SENSOR_MAGNETIC_FIELD);  //d
        sensorNames.add(SENSOR_PRESSURE);  //d
        sensorNames.add(SENSOR_PROXIMITY);  //d
        sensorNames.add(SENSOR_STEP_DETECTOR);  //d
        sensorNames.add(SENSOR_STEP_COUNTER);  //d
        sensorNames.add(SENSOR_HEART_RATE);
        sensorNames.add(SENSOR_RELATIVE_HUMIDITY);
        sensorNames.add(SENSOR_TEMPERATURE);

//        softwareNames.add(MOTION_DETECT);
        softwareNames.add(FACE_DETECT);
        softwareNames.add(BARCODE_READER);
        softwareNames.add(TEXT_SCAN);
//        softwareNames.add(FACE_EMOJI);
//        softwareNames.add(AUGMENTED_REALITY);
        softwareNames.add(VIRTUAL_REALITY);
        softwareNames.add(LABEL_GENERATOR);

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
        imageUrlMap.put(STORAGE, R.drawable.baseline_storage_black_48);
        imageUrlMap.put(RAM, R.drawable.baseline_memory_black_48);
        imageUrlMap.put(PIE, R.drawable.p);
        imageUrlMap.put(OREO, R.drawable.o);
        imageUrlMap.put(NOUGAT, R.drawable.n);
        imageUrlMap.put(MARSHMALLOW, R.drawable.m);
        imageUrlMap.put(LOLLIPOP, R.drawable.l);
        imageUrlMap.put(KITKAT, R.drawable.k);
        imageUrlMap.put(AUGMENTED_REALITY, R.drawable.ar);
        imageUrlMap.put(VIRTUAL_REALITY, R.drawable.vr);
        imageUrlMap.put(MOTION_DETECT, R.drawable.motion);
        imageUrlMap.put(FACE_DETECT, R.drawable.face);
        imageUrlMap.put(FACE_EMOJI, R.drawable.emoji);
        imageUrlMap.put(BARCODE_READER, R.drawable.barcode);
        imageUrlMap.put(TEXT_SCAN, R.drawable.baseline_text_format_black_48);
        imageUrlMap.put(LABEL_GENERATOR, R.drawable.baseline_speaker_notes_black_48);

//        versionMapUri.put(PIE, "https://developer.android.com/about/versions/pie");
//        versionMapUri.put(OREO, "https://developer.android.com/about/versions/oreo");
//        versionMapUri.put(NOUGAT, "https://developer.android.com/about/versions/nougat");
//        versionMapUri.put(MARSHMALLOW, "https://developer.android.com/about/versions/marshmallow");
//        versionMapUri.put(LOLLIPOP, "https://developer.android.com/about/versions/lollipop");
//        versionMapUri.put(KITKAT, "https://developer.android.com/about/versions/kitkat");

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
        //RADIO, BATTERY, CPU, SOUND, VIBRATOR, INFRARED, ANDROID OS

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
                "Move The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_LIGHT,
                "Point Your Light Sensor Towards Light To Observe Bar Color Change."
        );
        sensorMapDirections.put(
                BACK_CAMERA,
                "You Should See Back Camera Preview In The Test."
        );
        sensorMapDirections.put(
                FRONT_CAMERA,
                "You Should See Front Camera Preview In The Test."
        );
        sensorMapDirections.put(
                GPS_LOCATION,
                "You Should See Your Location In The Test."
        );
        sensorMapDirections.put(
                WIFI,
                "You Should See Your Current Connection As Well As All Available WiFi Connections In The Test."
        );
        sensorMapDirections.put(
                BLUETOOTH,
                "You Should See Your Current Connection As Well As All Available Bluetooth Connections In The Test."
        );
        sensorMapDirections.put(
                SCREEN,
                "Draw By Using Touch Anywhere On The Screen In The Test."
        );
        sensorMapDirections.put(
                BATTERY,
                "See Your Current Battery Level As Well As Whether It Is Plugged In For Charging Or Not."
        );
        sensorMapDirections.put(
                SOUND,
                "Play A Sound Track And Adjust It's Volume To See If The Speaker And Volume UP/DOWN Buttons Are Working."
        );
        sensorMapDirections.put(
                VIBRATOR,
                "Click Anywhere On The Next Screen To Vibrate Your Screen Once Per Click."
        );
        sensorMapDirections.put(
                AV_OUTPUTS,
                "Plug In/Out To Verify If Headphone Jack Is Functioning Properly Or Not."
        );
        sensorMapDirections.put(
                FLASH,
                "Click Anywhere On The Next Screen To Activate/Deactivate Flash On Your Device."
        );
        sensorMapDirections.put(
                MULTI_TOUCH,
                "Click On The Next Screen Multiple Times Simultaneously To Create Circles On It."
        );
        sensorMapDirections.put(
                FINGERPRINT,
                "Place Your Enrolled Fingerprints On The Fingerprint Sensor To Authenticate The Screen."
        );
        sensorMapDirections.put(
                COMPASS,
                "Rotate Your Phone In The Horizontal Plane (Parallel To The Floor) To Verify If The North Pole Of The Compass Still Points Towards The Same Direction."
        );
        sensorMapDirections.put(
                SENSOR_GYROSCOPE,
                "Rotate The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_GRAVITY,
                "Accelerate The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_LINEAR_ACCELERATION,
                "Accelerate The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_ROTATION_VECTOR,
                "Rotate The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_MAGNETIC_FIELD,
                "Move The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_PRESSURE,
                "Move The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                VIRTUAL_REALITY,
                "Move the screen to observe the virtual world."
        );
        sensorMapDirections.put(
                LABEL_GENERATOR,
                "Capture / Upload an image to generate labels."
        );
        sensorMapDirections.put(
                FACE_DETECT,
                "Capture / Upload an image to detect faces."
        );
        sensorMapDirections.put(
                BARCODE_READER,
                "Capture / Upload an image to detect barcodes."
        );
        sensorMapDirections.put(
                TEXT_SCAN,
                "Capture / Upload an image to detect text."
        );
        sensorMapDirections.put(
                SENSOR_STEP_COUNTER,
                "Start walking to track your progress as number of steps you take."
        );
        sensorMapDirections.put(
                SENSOR_STEP_DETECTOR,
                "Start walking to validate if your device detects a step or not."
        );
        //TODO : ATTACH ACTUAL DATA HERE AND NOT UN_ORIGINAL
        sensorMapDirections.put(
                MOTION_DETECT,
                ""
        );
        sensorMapDirections.put(
                FACE_EMOJI,
                ""
        );
        sensorMapDirections.put(
                AUGMENTED_REALITY,
                ""
        );

        sensorMapHints.put(
                SENSOR_PROXIMITY,
                "A proximity sensor is usually located at the top of a mobile screen."
        );
        sensorMapHints.put(
                SENSOR_ACCELEROMETER,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_LIGHT,
                "A light sensor is usually located at the top of a mobile screen."
        );
        sensorMapHints.put(
                BACK_CAMERA,
                "In Phones With Auto-focus Feature, The Focus Should Eventually Shift To The Object You Will Point Towards."
        );
        sensorMapHints.put(
                FRONT_CAMERA,
                "In Phones With Auto-focus Feature, The Focus Should Eventually Shift To The Object You Will Point Towards."
        );
        sensorMapHints.put(
                GPS_LOCATION,
                "Tap the marker for location coordinates (Longitude And Latitude)."
        );
        sensorMapHints.put(
                WIFI,
                "This Might Not Work If Current WiFi State Is Disabled."
        );
        sensorMapHints.put(
                BLUETOOTH,
                "This Might Not Work If Current Bluetooth State Is Disabled."
        );
        sensorMapHints.put(
                SCREEN,
                "You Can Try Drawing Over Places On Screen That You Are Suspicious Of Not Working."
        );
        sensorMapHints.put(
                BATTERY,
                "You Will Also Observe Changes As Your Battery Level Decreases Or Increases."
        );
        sensorMapHints.put(
                SOUND,
                "Alternatively You Can Increase/Decrease Volume By Dragging The Slider."
        );
        sensorMapHints.put(
                VIBRATOR,
                "Avoid Using Vibrations On Call Reception To Avoid Damage To The Vibrator Motor."
        );
        sensorMapHints.put(
                AV_OUTPUTS,
                "On Plugging In The Port, Sometimes It May Momentarily Set State To Plugged And Then Unplugged. This Is Normal."
        );
        sensorMapHints.put(
                FLASH,
                "It Is Recommended To Avoid Turning On Flash For Prolonged Period Of Time To Avoid Device Overheating."
        );
        sensorMapHints.put(
                MULTI_TOUCH,
                "Try To Get Maximum Number Of Circles On The Screen."
        );
        sensorMapHints.put(
                FINGERPRINT,
                "For Fingers Not Enrolled On The Device, Authentication Will Fail. You Can Reset The Screen To Re-test."
        );
        sensorMapHints.put(
                COMPASS,
                "If You Are Not Getting Expected Results, Try Re-calibrating The Device In Settings."
        );
        sensorMapHints.put(
                SENSOR_GYROSCOPE,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_GRAVITY,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_LINEAR_ACCELERATION,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_ROTATION_VECTOR,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_MAGNETIC_FIELD,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_PRESSURE,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                FACE_DETECT,
                "MAKE SURE to set the orientation of the selected image correctly."
        );
        sensorMapHints.put(
                BARCODE_READER,
                "MAKE SURE to set the orientation of the selected image correctly."
        );
        sensorMapHints.put(
                TEXT_SCAN,
                "MAKE SURE to set the orientation of the selected image correctly."
        );
        sensorMapHints.put(
                VIRTUAL_REALITY,
                "Increase the screen brightness to max for a more pleasant experience."
        );
        sensorMapHints.put(
                LABEL_GENERATOR,
                "MAKE SURE to set the orientation of the selected image correctly."
        );
        sensorMapHints.put(
                SENSOR_STEP_DETECTOR,
                "It is recommended to keep the phone at the waist level or in a pocket as you walk."
        );
        sensorMapHints.put(
                SENSOR_STEP_COUNTER,
                "It is recommended to keep the phone at the waist level or in a pocket as you walk."
        );
        //TODO : ATTACH ACTUAL DATA HERE AND NOT UN_ORIGINAL
        sensorMapHints.put(
                MOTION_DETECT,
                ""
        );
        sensorMapHints.put(
                FACE_EMOJI,
                ""
        );
        sensorMapHints.put(
                AUGMENTED_REALITY,
                ""
        );


        mapAbout.put(
                SENSOR_PROXIMITY,
                R.array.proximity_descriptions
        );
        mapAbout.put(
                SENSOR_ACCELEROMETER,
                R.array.accelerometer_descriptions
        );
        mapAbout.put(
                SENSOR_LIGHT,
                R.array.light_descriptions
        );
        mapAbout.put(
                BACK_CAMERA,
                R.array.camera_descriptions
        );
        mapAbout.put(
                FRONT_CAMERA,
                R.array.camera_descriptions
        );
        mapAbout.put(
                GPS_LOCATION,
                R.array.gps_descriptions
        );
        mapAbout.put(
                WIFI,
                R.array.wifi_descriptions
        );
        mapAbout.put(
                BLUETOOTH,
                R.array.bluetooth_descriptions
        );
        mapAbout.put(
                SCREEN,
                R.array.screen_descriptions
        );
        mapAbout.put(
                BATTERY,
                R.array.battery_descriptions
        );
        mapAbout.put(
                SOUND,
                R.array.sound_descriptions
        );
        mapAbout.put(
                VIBRATOR,
                R.array.vibrator_descriptions
        );
        mapAbout.put(
                AV_OUTPUTS,
                R.array.av_descriptions
        );
        mapAbout.put(
                FLASH,
                R.array.flash_descriptions
        );
        mapAbout.put(
                MULTI_TOUCH,
                R.array.multi_touch_descriptions
        );
        mapAbout.put(
                FINGERPRINT,
                R.array.fingerprint_descriptions
        );
        mapAbout.put(
                COMPASS,
                R.array.compass_descriptions
        );
        mapAbout.put(
                SENSOR_TEMPERATURE,
                R.array.ambient_temperature_descriptions
        );
        mapAbout.put(
                SENSOR_PRESSURE,
                R.array.pressure_descriptions
        );
        mapAbout.put(
                SENSOR_RELATIVE_HUMIDITY,
                R.array.relative_humidity_descriptions
        );
        mapAbout.put(
                SENSOR_GYROSCOPE,
                R.array.gyroscope_descriptions
        );
        mapAbout.put(
                SENSOR_GRAVITY,
                R.array.gravity_descriptions
        );
        mapAbout.put(
                SENSOR_LINEAR_ACCELERATION,
                R.array.linear_acceleration_descriptions
        );
        mapAbout.put(
                SENSOR_ROTATION_VECTOR,
                R.array.rotation_vector_descriptions
        );
        mapAbout.put(
                SENSOR_MAGNETIC_FIELD,
                R.array.magnetic_field_descriptions
        );
        mapAbout.put(
                SENSOR_HEART_RATE,
                R.array.heart_rate_ecg_descriptions
        );
        mapAbout.put(
                SENSOR_STEP_DETECTOR,
                R.array.step_detector_descriptions
        );
        mapAbout.put(
                SENSOR_STEP_COUNTER,
                R.array.step_counter_descriptions
        );
        mapAbout.put(
                SENSOR_MOTION_DETECTOR,
                R.array.motion_detector_descriptions
        );
        mapAbout.put(
                SENSOR_STATIONARY_DETECTOR,
                R.array.stationary_detector_descriptions
        );
        mapAbout.put(
                GSM_UMTS,
                R.array.gsm_umts_descriptions
        );
        mapAbout.put(
                RADIO,
                R.array.radio_descriptions
        );
        mapAbout.put(
                CPU,
                R.array.cpu_descriptions
        );
        mapAbout.put(
                ANDROID_OS,
                R.array.android_os_descriptions
        );
        mapAbout.put(
                INFRARED,
                R.array.infrared_descriptions
        );
        mapAbout.put(
                NFC,
                R.array.nfc_descriptions
        );
        mapAbout.put(
                MICROPHONE,
                R.array.microphone_descriptions
        );
        mapAbout.put(
                USB_ACCESSORY,
                R.array.usb_accessory_descriptions
        );
        mapAbout.put(
                BAROMETER,
                R.array.barometer_descriptions
        );
        mapAbout.put(
                WIFI_DIRECT,
                R.array.wifi_direct_descriptions
        );
        mapAbout.put(
                HEART_RATE_ECG,
                R.array.heart_rate_ecg_descriptions
        );
        mapAbout.put(
                WEB_VIEW,
                R.array.web_view_descriptions
        );
        mapAbout.put(
                MIDI,
                R.array.midi_descriptions
        );
        mapAbout.put(
                VR_MODE,
                R.array.vr_descriptions
        );
        mapAbout.put(
                FAKE_TOUCH,
                R.array.fake_touch_descriptions
        );
        mapAbout.put(
                RAM,
                R.array.ram_descriptions
        );
        mapAbout.put(
                STORAGE,
                R.array.storage_descriptions
        );
        mapAbout.put(
                FACE_DETECT,
                R.array.face_detect_descriptions
        );
        mapAbout.put(
                BARCODE_READER,
                R.array.barcode_reader_descriptions
        );
        mapAbout.put(
                VIRTUAL_REALITY,
                R.array.vr_descriptions
        );
        mapAbout.put(
                LABEL_GENERATOR,
                R.array.label_generator_descriptions
        );
        mapAbout.put(
                TEXT_SCAN,
                R.array.text_scan_descriptions
        );
        //TODO : ATTACH ACTUAL DATA HERE AND NOT UN_ORIGINAL
        mapAbout.put(
                MOTION_DETECT,
                R.array.storage_descriptions
        );
        mapAbout.put(
                FACE_EMOJI,
                R.array.storage_descriptions
        );
        mapAbout.put(
                AUGMENTED_REALITY,
                R.array.storage_descriptions
        );


        categories.add(new Category(
                R.drawable.baseline_mobile_friendly_black_48,
                SENSOR,
                sensorNames.size() + " ITEMS")
        );

        categories.add(new Category(
                R.drawable.baseline_battery_charging_full_black_48,
                FEATURE,
                featureNames.size() + " ITEMS")
        );

        categories.add(new Category(
                R.drawable.baseline_system_update_black_48,
                SOFTWARE,
                "0" + softwareNames.size() + " ITEMS")
        );

        categories.add(new Category(
                R.drawable.baseline_info_black_48,
                INFORMATION,
                "0" + informationNames.size() + " ITEMS")
        );

        categories.add(new Category(
                R.drawable.baseline_android_black_48,
                ANDROID,
                "0" + androidNames.size() + " ITEMS")
        );

//        categories.add(new Category(
//                R.drawable.baseline_verified_user_black_48,
//                RATE_APP,
//                "Give Feedback")
//        );

//        categories.add(new Category(
//                R.drawable.baseline_device_unknown_black_48,
//                DIAGNOSTIC,
//                diagnosticsNames.size() + " Diagnostics")
//        );
    }
}
