package com.appbusters.robinkamboj.senseitall.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.os.Build;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppConstants {

    public static final String ml_package_name = "com.assistiveapps.machinelearningtests";
    public static final String face_detect_intent_filter = "com.assistiveapps.machinelearningtests.tests.face_detect";
    public static final String image_label_intent_filter = "com.assistiveapps.machinelearningtests.tests.image_label";
    public static final String text_scan_intent_filter = "com.assistiveapps.machinelearningtests.tests.text_scan";
    public static final String barcode_scan_intent_filter = "com.assistiveapps.machinelearningtests.tests.barcode_scan";

    public static final String HEADER_DISCOVER = "DISCOVER";
    public static final String HEADER_PERSONALIZED = "PROFILE";
    public static final String HEADER_TOOLS = "TOOLS";

    public static final String ARG_HEADING_NOTE = "HEADING_NOTE_ARG";
    public static final String ARG_DESCRIPTION_NOTE = "DESCRIPTION_NOTE_ARG";
    public static final String ARG_ID_NOTE = "ID_NOTE_ARG";
    public static final String FROM_ARG_IN_REQUEST = "FROM_ARG_IN_REQUEST";

    public static final int REQUEST_CODE = 100;
    public static final int REQUEST_CHECK_SETTINGS = 103;
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

    private static final String QUICK_SETTINGS = "Quick Settings";
    public static final String CALENDAR = "Calendar";                           //tool names
    public static final String WEATHER = "Weather"; //-
    public static final String REMINDER = "Set Reminder"; //-
    public static final String SET_ALARM = "Set Alarm"; //-
    public static final String INTERNET_SPEED = "Internet Speed"; //-
    public static final String VOLUME_CONTROL = "Volume Control"; //-
    public static final String TAKE_NOTE = "Take Note"; //-
    public static final String CHECKLIST = "Checklist";
    public static final String RECORD_AUDIO = "Record Audio";
    public static final String CALCULATOR = "Calculator";
    public static final String SOUND_LEVEL = "Sound Level";
    public static final String STOP_WATCH = "Stopwatch";
    public static final String TIMER = "Timer";
    public static final String CROP_IMAGE = "Image Crop";
    public static final String IMAGE_FILTERS = "Image Filters";
    public static final String DRAW_NOTE = "Draw Note";
    public static final String SQUARE_IMAGE = "Square Image";
    public static final String EDIT_IMAGE = "Edit Image";

    public static final String WIFI_QUICK = "Wifi";                           //quick setting names
    public static final String BLUETOOTH_QUICK = "Bluetooth";
    public static final String BRIGHTNESS_QUICK = "Brightness";
    public static final String VOLUME_QUICK = "Volume";
    public static final String HOTSPOT_QUICK = "Hotspot";
    public static final String FLASHLIGHT_QUICK = "Flashlight";
    public static final String LOCATION_QUICK = "Location";
    public static final String AIRPLANE_QUICK = "Airplane Mode";
    public static final String AUTOROTATE_QUICK = "Auto Rotate";

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

    public static List<String> categoryNames = new ArrayList<>();
    public static List<String> sensorNames = new ArrayList<>();
    public static List<String> featuredSensorNames = new ArrayList<>();
    public static List<String> featureNames = new ArrayList<>();
    public static List<String> informationNames = new ArrayList<>();
    public static List<String> softwareNames = new ArrayList<>();
    public static List<String> androidNames = new ArrayList<>();
    public static List<String> imageTools = new ArrayList<>();
    public static List<String> popTools = new ArrayList<>();
    public static List<String> popTests = new ArrayList<>();
    public static List<String> everydayTools = new ArrayList<>();
    public static List<Integer> featuredColors = new ArrayList<>();
    public static List<Integer> simpleColors = new ArrayList<>();

    public static HashMap<String, Boolean> isTestMap = new HashMap<>();

    public static HashMap<String, String> packageManagerPaths = new HashMap<>();
    public static HashMap<String, Integer> sensorManagerInts = new HashMap<>();
    public static List<String> dangerousPermissions = new ArrayList<>();
    public static HashMap<String, Integer> onMapImage = new HashMap<>();
    public static HashMap<String, Integer> offMapImage = new HashMap<>();

    public static final String SENSOR_HEADER = "Sensors";
    public static final String FEATURE_HEADER = "Features";
    public static final String TRENDING_HEADER = "Trending";
    public static final String TOOLS_HEADER = "Tools";
    public static final String DEVICE_HEADER = "Device";
    public static final String ANDROID_HEADER = "Android";

    static {

        featuredColors.add(R.color.featured_color_green);
        featuredColors.add(R.color.featured_color_purple);
        featuredColors.add(R.color.featured_color_red);
        featuredColors.add(R.color.featured_color_blue);
        featuredColors.add(R.color.featured_color_yellow);
        featuredColors.add(R.color.featured_color_light_green);

        simpleColors.add(R.color.simple_color_red);
        simpleColors.add(R.color.simple_color_red_light);
        simpleColors.add(R.color.simple_color_yellow);
        simpleColors.add(R.color.simple_color_light_green);
        simpleColors.add(R.color.simple_color_green);
        simpleColors.add(R.color.simple_color_turquoise);
        simpleColors.add(R.color.simple_color_light_blue);
        simpleColors.add(R.color.simple_color_blue);
        simpleColors.add(R.color.simple_color_violet);
        simpleColors.add(R.color.simple_color_purple);
        simpleColors.add(R.color.simple_color_pink);
        simpleColors.add(R.color.simple_color_maroon);

        categoryNames.add(SENSOR_HEADER);
        categoryNames.add(FEATURE_HEADER);
        categoryNames.add(TOOLS_HEADER);
        categoryNames.add(TRENDING_HEADER);
        categoryNames.add(DEVICE_HEADER);
        categoryNames.add(ANDROID_HEADER);

        offMapImage.put(WIFI_QUICK, R.drawable.baseline_wifi_off_black_48);
        offMapImage.put(BLUETOOTH_QUICK, R.drawable.baseline_bluetooth_disabled_black_48);
        offMapImage.put(BRIGHTNESS_QUICK, R.drawable.baseline_brightness_low_black_48);
        offMapImage.put(VOLUME_QUICK, R.drawable.baseline_volume_off_black_48);
        offMapImage.put(HOTSPOT_QUICK, R.drawable.baseline_portable_wifi_off_black_48);
        offMapImage.put(FLASHLIGHT_QUICK, R.drawable.baseline_flash_off_black_48);
        offMapImage.put(LOCATION_QUICK, R.drawable.baseline_location_off_black_48);
        offMapImage.put(AIRPLANE_QUICK, R.drawable.baseline_airplanemode_inactive_black_48);
        offMapImage.put(AUTOROTATE_QUICK, R.drawable.baseline_screen_lock_rotation_black_48);

        onMapImage.put(WIFI_QUICK, R.drawable.baseline_network_wifi_black_48);
        onMapImage.put(BLUETOOTH_QUICK, R.drawable.baseline_bluetooth_black_48);
        onMapImage.put(BRIGHTNESS_QUICK, R.drawable.baseline_brightness_high_black_48);
        onMapImage.put(VOLUME_QUICK, R.drawable.baseline_volume_up_black_48);
        onMapImage.put(HOTSPOT_QUICK, R.drawable.baseline_wifi_tethering_black_48);
        onMapImage.put(FLASHLIGHT_QUICK, R.drawable.baseline_flash_on_black_48);
        onMapImage.put(LOCATION_QUICK, R.drawable.baseline_location_on_black_48);
        onMapImage.put(AIRPLANE_QUICK, R.drawable.baseline_airplanemode_active_black_48);
        onMapImage.put(AUTOROTATE_QUICK, R.drawable.baseline_screen_rotation_black_48);

        dangerousPermissions.add(Manifest.permission.CAMERA);
        dangerousPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        dangerousPermissions.add(Manifest.permission.BODY_SENSORS);
        dangerousPermissions.add(Manifest.permission.READ_PHONE_STATE);
        dangerousPermissions.add(Manifest.permission.RECORD_AUDIO);
        dangerousPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        dangerousPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

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

//        popTools.add(SET_ALARM);
//        popTools.add(WEATHER);
//        popTools.add(REMINDER);
        popTools.add(VOLUME_CONTROL);
        popTools.add(CROP_IMAGE);
        popTools.add(IMAGE_FILTERS);
        popTools.add(DRAW_NOTE);
        popTools.add(CALENDAR);
        popTools.add(SOUND_LEVEL);
        popTools.add(TAKE_NOTE);

        imageTools.add(CROP_IMAGE);
        imageTools.add(IMAGE_FILTERS);
        imageTools.add(DRAW_NOTE);
//        imageTools.add(SQUARE_IMAGE);
//        imageTools.add(EDIT_IMAGE);

//        everydayTools.add(SET_ALARM);
//        everydayTools.add(RECORD_AUDIO);
//        everydayTools.add(STOP_WATCH);
//        everydayTools.add(REMINDER);
//        everydayTools.add(WEATHER);
//        everydayTools.add(INTERNET_SPEED);
//        everydayTools.add(CHECKLIST);
        everydayTools.add(CALCULATOR);
        everydayTools.add(VOLUME_CONTROL);
        everydayTools.add(SOUND_LEVEL);
        everydayTools.add(TAKE_NOTE);
        everydayTools.add(TIMER);
        everydayTools.add(CALENDAR);

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

        featuredSensorNames.add(SENSOR_LIGHT);
        featuredSensorNames.add(SENSOR_PROXIMITY);
        featuredSensorNames.add(SENSOR_ACCELEROMETER);
        featuredSensorNames.add(SENSOR_GRAVITY);
        featuredSensorNames.add(SENSOR_ROTATION_VECTOR);

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
        imageUrlMap.put(CALENDAR, R.drawable.baseline_today_black_48);
        imageUrlMap.put(WEATHER, R.drawable.baseline_wb_cloudy_black_48);
        imageUrlMap.put(REMINDER, R.drawable.baseline_note_black_48);
        imageUrlMap.put(SET_ALARM, R.drawable.baseline_alarm_black_48);
        imageUrlMap.put(INTERNET_SPEED, R.drawable.baseline_trending_up_black_48);
        imageUrlMap.put(VOLUME_CONTROL, R.drawable.baseline_speaker_phone_black_48);
        imageUrlMap.put(TAKE_NOTE, R.drawable.baseline_note_add_black_48);
        imageUrlMap.put(QUICK_SETTINGS, R.drawable.baseline_settings_black_48);
        imageUrlMap.put(CROP_IMAGE, R.drawable.baseline_crop_black_48);
        imageUrlMap.put(SQUARE_IMAGE, R.drawable.baseline_crop_square_black_48);
        imageUrlMap.put(IMAGE_FILTERS, R.drawable.baseline_local_florist_black_48);
        imageUrlMap.put(EDIT_IMAGE, R.drawable.baseline_tune_black_48);
        imageUrlMap.put(CHECKLIST, R.drawable.baseline_spellcheck_black_48);
        imageUrlMap.put(RECORD_AUDIO, R.drawable.baseline_record_voice_over_black_48);
        imageUrlMap.put(CALCULATOR, R.drawable.baseline_exposure_plus_1_black_48);
        imageUrlMap.put(SOUND_LEVEL, R.drawable.baseline_volume_up_black_48);
        imageUrlMap.put(STOP_WATCH, R.drawable.baseline_watch_later_black_48);
        imageUrlMap.put(TIMER, R.drawable.baseline_watch_later_black_48);
        imageUrlMap.put(DRAW_NOTE, R.drawable.baseline_edit_black_48);

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
        //RADIO, BATTERY, CPU, SOUND, VIBRATOR, INFRARED, ANDROID OS

        popTests.add(SENSOR_PROXIMITY);
        popTests.add(MULTI_TOUCH);
        popTests.add(SENSOR_ACCELEROMETER);
        popTests.add(SCREEN);
        popTests.add(SOUND);
        popTests.add(LABEL_GENERATOR);
        popTests.add(VIRTUAL_REALITY);
        popTests.add(FINGERPRINT);
        popTests.add(BATTERY);
        popTests.add(COMPASS);

        //required
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
