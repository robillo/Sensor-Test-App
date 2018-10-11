package com.appbusters.robinkamboj.senseitall.view.detail_activity.other_files;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.HashMap;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AUGMENTED_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AV_OUTPUTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BARCODE_READER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BAROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BLUETOOTH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_EMOJI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FAKE_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.HEART_RATE_ECG;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFRARED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LABEL_GENERATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MICROPHONE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MIDI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MOTION_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NFC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RAM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GYROSCOPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_HEART_RATE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LIGHT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LINEAR_ACCELERATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MAGNETIC_FIELD;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MOTION_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PRESSURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_RELATIVE_HUMIDITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ROTATION_VECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STATIONARY_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_COUNTER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_TEMPERATURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.USB_ACCESSORY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIRTUAL_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VR_MODE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WEB_VIEW;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI_DIRECT;

public class MapAbout {

    public static HashMap<String, Integer> mapAbout = new HashMap<>();

    static {
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
    }
}
