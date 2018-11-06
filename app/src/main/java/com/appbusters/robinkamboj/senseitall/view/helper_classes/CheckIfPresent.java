package com.appbusters.robinkamboj.senseitall.view.helper_classes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.permission_request_activity.PermissionRequestActivity;
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.TabMainActivity;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AIRPLANE_QUICK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AUTOROTATE_QUICK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AV_OUTPUTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BARCODE_READER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BLUETOOTH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BLUETOOTH_QUICK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BRIGHTNESS_QUICK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CALCULATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CALENDAR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CHECKLIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CROP_IMAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAW_NOTE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.EDIT_IMAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FAKE_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASHLIGHT_QUICK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.HEART_RATE_ECG;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.HOTSPOT_QUICK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IMAGE_FILTERS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INTERNET_SPEED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.KITKAT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LABEL_GENERATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LOCATION_QUICK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LOLLIPOP;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MARSHMALLOW;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MICROPHONE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MIDI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NFC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NOUGAT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.OREO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PIE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RAM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REMINDER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GYROSCOPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_HEART_RATE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LIGHT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LINEAR_ACCELERATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MAGNETIC_FIELD;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PRESSURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_RELATIVE_HUMIDITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ROTATION_VECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_COUNTER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_TEMPERATURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SET_ALARM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND_LEVEL;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SQUARE_IMAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TAKE_NOTE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TIMER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.USB_ACCESSORY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIRTUAL_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VOLUME_CONTROL;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VOLUME_QUICK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WEATHER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI_QUICK;

public class CheckIfPresent {

    private Context context;

    public CheckIfPresent(Context context) {
        this.context = context;
    }

    @SuppressWarnings("deprecation")
    public boolean returnPresence(String item) {
        switch (item) {
            //_____________________________TOOLS________________________________//
            case SET_ALARM:
            case INTERNET_SPEED:
            case TAKE_NOTE:
            case CHECKLIST:
            case CALCULATOR:
            case CALENDAR:
            case WEATHER:
            case REMINDER:
            case VOLUME_CONTROL:
            case TIMER: {
                return true;
            }
            case SOUND_LEVEL: {
                if(isPermissionGranted(SOUND_LEVEL)) {
                    return true;
                }
                else {
                    showSnackBar("audio permission not given");
                    context.startActivity(new Intent(context, PermissionRequestActivity.class));
                    //show snackbar for audio permission
                    //ask for audio permission
                    return false;
                }
            }
            case SQUARE_IMAGE:
            case CROP_IMAGE:
            case IMAGE_FILTERS:
            case DRAW_NOTE:
            case EDIT_IMAGE: {
                if(isPermissionGranted(SQUARE_IMAGE)) {
                    return true;
                }
                else {
                    showSnackBar("storage and camera permission not given");
                    context.startActivity(new Intent(context, PermissionRequestActivity.class));
                    //show snackbar for storage and camera permission
                    //ask for storage and camera permission
                    return false;
                }
            }
            //___________________________QUICK SETTINGS______________________________//
            case WIFI_QUICK:
            case BLUETOOTH_QUICK:
            case BRIGHTNESS_QUICK:
            case VOLUME_QUICK:
            case HOTSPOT_QUICK:
            case FLASHLIGHT_QUICK:
            case LOCATION_QUICK:
            case AIRPLANE_QUICK:
            case AUTOROTATE_QUICK: {
                return true;
            }
            //_________________________ELECTRONIC SENSORS____________________________//
            case SENSOR_LIGHT:
            case SENSOR_ACCELEROMETER:
            case SENSOR_GYROSCOPE:
            case SENSOR_GRAVITY:
            case SENSOR_LINEAR_ACCELERATION:
            case SENSOR_ROTATION_VECTOR:
            case SENSOR_MAGNETIC_FIELD:
            case SENSOR_PRESSURE:
            case SENSOR_PROXIMITY:
            case SENSOR_STEP_DETECTOR:
            case SENSOR_STEP_COUNTER:
            case SENSOR_HEART_RATE:
            case SENSOR_RELATIVE_HUMIDITY:
            case SENSOR_TEMPERATURE: {
                SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
                if(sensorManager == null) return false;
                return sensorManager.getDefaultSensor(AppConstants.sensorManagerInts.get(item)) != null;
            }
            //_________________________EVERYDAY FEATURES____________________________//
            case ANDROID_OS:
            case BATTERY:
            case CPU:
            case SOUND: {
                return true;
            }
            case FINGERPRINT: {
                boolean isFingerprintSupported;
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PackageManager packageManager = context.getPackageManager();
                        isFingerprintSupported = packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);
                    } else {
                        isFingerprintSupported = FingerprintManagerCompat.from(context).isHardwareDetected();
                    }
                    if(!isFingerprintSupported) {
                        showSnackBar("item not supported");
                        return false;
                    }
                    else {
                        return true;
                    }
                }
                catch (Exception ignored) {}

                return false;
            }
            case GSM_UMTS:
            case COMPASS:
            case SCREEN:
            case AV_OUTPUTS:
            case FLASH:
            case MULTI_TOUCH:
            case WIFI:
            case BLUETOOTH:
            case GPS_LOCATION:
            case NFC:
            case MICROPHONE:
            case USB_ACCESSORY:
            case HEART_RATE_ECG:
            case FAKE_TOUCH:
            case MIDI: {
                PackageManager packageManager = context.getPackageManager();
                if(packageManager == null) {
                    showSnackBar("not present");
                    return false;
                }
                if(packageManager.hasSystemFeature(AppConstants.packageManagerPaths.get(item))) {
                    return true;
                }
                else {
                    showSnackBar(item + " not present");
                    return false;
                }
            }
            case RADIO: {
                return Build.getRadioVersion() != null;
            }
            case BACK_CAMERA:
            case FRONT_CAMERA: {
                if(isPermissionGranted(BACK_CAMERA)) {
                    return true;
                }
                else {
                    showSnackBar("camera permission not given");
                    context.startActivity(new Intent(context, PermissionRequestActivity.class));
                    //show snackbar for camera permission
                    //ask for camera permission
                    return false;
                }
            }
            case VIBRATOR: {
                Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
                return vibrator != null && vibrator.hasVibrator();
            }
            //___________________________MODERN FEATURES______________________________//
            case FACE_DETECT:
            case BARCODE_READER:
            case TEXT_SCAN:
            case LABEL_GENERATOR: {
                String ml_package_name = "com.assistiveapps.machinelearningtests";
                if(appInstalledOrNot(ml_package_name)) {
                    return true;
                }
                else {
                    showToast("ML TESTS app not installed");
                    Intent fallbackIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + ml_package_name));
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + ml_package_name)
                    );
                    try {
                        context.startActivity(intent);
                    } catch (android.content.ActivityNotFoundException e) {
                        context.startActivity(fallbackIntent);
                    }
                }
                break;
            }
            case VIRTUAL_REALITY: {
                String vr_package_name = "com.robillo.virtualrealitysample_senseitall";
                if(appInstalledOrNot(vr_package_name)) {
                    return true;
                }
                else {
                    showToast("VR SAMPLES app not installed");
                    Intent fallbackIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + vr_package_name));
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + vr_package_name)
                    );
                    try {
                        context.startActivity(intent);
                    } catch (android.content.ActivityNotFoundException e) {
                        context.startActivity(fallbackIntent);
                    }
                }
                break;
            }
            //___________________________DEVICE DETAILS______________________________//
            case STORAGE:
            case RAM: {
                return true;
            }
            //___________________________ANDROID PROPERTIES______________________________//
            case PIE:
            case OREO:
            case NOUGAT:
            case MARSHMALLOW:
            case LOLLIPOP:
            case KITKAT: {
                return true;
            }
        }
        return false;
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException ignored) {

        }
        return false;
    }

    private void showSnackBar(String text) {
        ((TabMainActivity) context).showSnackBar(text);
    }

    private void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    private boolean isPermissionGranted(String item) {
        boolean isGiven = true;
        switch (item) {
            case FACE_DETECT:
            case BARCODE_READER:
            case TEXT_SCAN:
            case LABEL_GENERATOR:
            case BACK_CAMERA:
            case FRONT_CAMERA: {
                if (isPermissionNotGiven(Manifest.permission.CAMERA)) {
                    isGiven = false;
                }
                break;
            }
            case SQUARE_IMAGE:
            case CROP_IMAGE:
            case IMAGE_FILTERS:
            case EDIT_IMAGE: {
                if (isPermissionNotGiven(Manifest.permission.CAMERA) && isPermissionNotGiven(Manifest.permission.READ_EXTERNAL_STORAGE) && isPermissionNotGiven(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    isGiven = false;
                }
                break;
            }
            case FLASH: {
                if (isPermissionNotGiven(Manifest.permission.CAMERA))
                    isGiven = false;
                break;
            }
            case GSM_UMTS: {
                if (isPermissionNotGiven(Manifest.permission.READ_PHONE_STATE))
                    isGiven = false;
                break;
            }
            case WIFI: {
                if (isPermissionNotGiven(Manifest.permission.ACCESS_COARSE_LOCATION))
                    isGiven = false;
                break;
            }
            case SENSOR_HEART_RATE:
            case HEART_RATE_ECG: {
                if(isPermissionNotGiven(Manifest.permission.BODY_SENSORS))
                    isGiven = false;
                break;
            }
            case GPS_LOCATION: {
                if (isPermissionNotGiven(Manifest.permission.ACCESS_FINE_LOCATION) ||
                        isPermissionNotGiven(Manifest.permission.ACCESS_COARSE_LOCATION))
                    isGiven = false;
                break;
            }
            case SOUND_LEVEL:
            case MICROPHONE: {
                if(isPermissionNotGiven(Manifest.permission.RECORD_AUDIO))
                    isGiven = false;
                break;
            }
            case FINGERPRINT: {

                try {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(isPermissionNotGiven(Manifest.permission.USE_BIOMETRIC)) {
                            isGiven = false;
                        }
                        else {
                            PackageManager packageManager = context.getPackageManager();
                            isGiven = packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);
                        }
                    }
                    else {
                        isGiven = FingerprintManagerCompat.from(context).isHardwareDetected();
                    }
                }
                catch (Exception ignored) {}
                break;
            }
        }
        return isGiven;
    }

    private boolean isPermissionNotGiven(String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
    }
}
