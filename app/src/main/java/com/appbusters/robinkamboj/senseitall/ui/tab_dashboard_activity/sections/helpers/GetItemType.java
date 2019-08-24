package com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity.sections.helpers;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AUGMENTED_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AV_OUTPUTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BARCODE_READER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BAROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BLUETOOTH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CALCULATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CHECKLIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CROP_IMAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAW_NOTE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.EDIT_IMAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_EMOJI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FAKE_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.HEART_RATE_ECG;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IMAGE_FILTERS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFORMATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INTERNET_SPEED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.KITKAT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LABEL_GENERATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LOLLIPOP;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MARSHMALLOW;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MICROPHONE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MIDI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MOTION_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NFC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NOUGAT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.OREO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PIE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RAM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RECORD_AUDIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REMINDER;
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
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SET_ALARM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOFTWARE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND_LEVEL;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SQUARE_IMAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STOP_WATCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TAKE_NOTE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TIMER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_ANDROID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_DEVICE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_FEATURES;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SENSORS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_TOOLS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_TRENDING;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.USB_ACCESSORY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIRTUAL_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VOLUME_CONTROL;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VR_MODE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WEATHER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WEB_VIEW;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI_DIRECT;

public class GetItemType {

    private String name;

    public GetItemType(String name) {
        this.name = name;
    }

    public int getItemType() {
        switch (name) {
            case SENSOR_LIGHT:
            case SENSOR_PROXIMITY:
            case SENSOR_TEMPERATURE:
            case SENSOR_PRESSURE:
            case SENSOR_ACCELEROMETER:
            case SENSOR_RELATIVE_HUMIDITY:
            case SENSOR_GYROSCOPE:
            case SENSOR_GRAVITY:
            case SENSOR_LINEAR_ACCELERATION:
            case SENSOR_ROTATION_VECTOR:
            case SENSOR_STEP_COUNTER:
            case SENSOR_STEP_DETECTOR:
            case SENSOR_MOTION_DETECTOR:
            case SENSOR_STATIONARY_DETECTOR:
            case SENSOR_MAGNETIC_FIELD:
            case SENSOR_HEART_RATE: {
                return TYPE_SENSORS;
            }
            case FRONT_CAMERA:
            case GPS_LOCATION:
            case WIFI:
            case BLUETOOTH:
            case GSM_UMTS:
            case COMPASS:
            case RADIO:
            case SCREEN:
            case BATTERY:
            case CPU:
            case SOFTWARE:
            case VIBRATOR:
            case AV_OUTPUTS:
            case ANDROID_OS:
            case FLASH:
            case INFORMATION:
            case MULTI_TOUCH:
            case FINGERPRINT:
            case NFC:
            case MICROPHONE:
            case USB_ACCESSORY:
            case BAROMETER:
            case WIFI_DIRECT:
            case HEART_RATE_ECG:
            case FAKE_TOUCH:
            case WEB_VIEW:
            case MIDI:
            case VR_MODE: {
                return TYPE_FEATURES;
            }
            case STORAGE:
            case RAM: {
                return TYPE_DEVICE;
            }
            case PIE:
            case OREO:
            case NOUGAT:
            case MARSHMALLOW:
            case LOLLIPOP:
            case KITKAT: {
                return TYPE_ANDROID;
            }
            case MOTION_DETECT:
            case AUGMENTED_REALITY:
            case VIRTUAL_REALITY:
            case FACE_DETECT:
            case FACE_EMOJI:
            case BARCODE_READER:
            case TEXT_SCAN:
            case LABEL_GENERATOR: {
                return TYPE_TRENDING;
            }
            case WEATHER:
            case REMINDER:
            case SET_ALARM:
            case INTERNET_SPEED:
            case VOLUME_CONTROL:
            case TAKE_NOTE:
            case CHECKLIST:
            case RECORD_AUDIO:
            case CALCULATOR:
            case SOUND_LEVEL:
            case STOP_WATCH:
            case TIMER:
            case CROP_IMAGE:
            case IMAGE_FILTERS:
            case DRAW_NOTE:
            case SQUARE_IMAGE:
            case EDIT_IMAGE: {
                return TYPE_TOOLS;
            }
        }
        return TYPE_SENSORS;
    }
}
