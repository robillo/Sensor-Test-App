package com.appbusters.robinkamboj.senseitall.preferences;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.appbusters.robinkamboj.senseitall.utils.AppConstants;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.KEY_HEADER_TEXT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PREF_FILE_NAME;

public class AppPreferencesHelper implements PreferencesHelper {

    private SharedPreferences prefs;

    public AppPreferencesHelper(Context context) {
        prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getHeaderText() {
        return prefs.getString(KEY_HEADER_TEXT, AppConstants.SHOWING_DEVICE_TESTS);
    }

    @Override
    public void setHeaderText(String headerText) {
        prefs.edit().putString(KEY_HEADER_TEXT, headerText).apply();
    }

    @Override
    public boolean isDontAskAgainCamera() {
        return prefs.getBoolean(Manifest.permission.CAMERA, false);
    }

    @Override
    public void setIsDontAskAgainCamera(boolean dontAskAgain) {
        prefs.edit().putBoolean(Manifest.permission.CAMERA, dontAskAgain).apply();
    }

    @Override
    public boolean isDontAskAgainFineLocation() {
        return prefs.getBoolean(Manifest.permission.ACCESS_FINE_LOCATION, false);
    }

    @Override
    public void setIsDontAskAgainFineLocation(boolean dontAskAgain) {
        prefs.edit().putBoolean(Manifest.permission.ACCESS_FINE_LOCATION, dontAskAgain).apply();
    }

    @Override
    public boolean isDontAskAgainCoarseLocation() {
        return prefs.getBoolean(Manifest.permission.ACCESS_COARSE_LOCATION, false);
    }

    @Override
    public void setIsDontAskAgainCoarseLocation(boolean dontAskAgain) {
        prefs.edit().putBoolean(Manifest.permission.ACCESS_COARSE_LOCATION, dontAskAgain).apply();
    }

    @Override
    public boolean isDontAskAgainBodySensors() {
        return prefs.getBoolean(Manifest.permission.BODY_SENSORS, false);
    }

    @Override
    public void setIsDontAskAgainBodySensors(boolean dontAskAgain) {
        prefs.edit().putBoolean(Manifest.permission.BODY_SENSORS, dontAskAgain).apply();
    }

    @Override
    public boolean isDontAskAgainPhoneState() {
        return prefs.getBoolean(Manifest.permission.READ_PHONE_STATE, false);
    }

    @Override
    public void setIsDontAskAgainPhoneState(boolean dontAskAgain) {
        prefs.edit().putBoolean(Manifest.permission.READ_PHONE_STATE, dontAskAgain).apply();
    }

    @Override
    public boolean isDontAskAgainRecordAudio() {
        return prefs.getBoolean(Manifest.permission.RECORD_AUDIO, false);
    }

    @Override
    public void setIsDontAskAgainRecordAudio(boolean dontAskAgain) {
        prefs.edit().putBoolean(Manifest.permission.RECORD_AUDIO, dontAskAgain).apply();
    }

    @Override
    public boolean isDontAskAgainUseFingerprint() {
        return
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        &&
                        prefs.getBoolean(Manifest.permission.USE_FINGERPRINT, false);
    }

    @Override
    public void setIsDontAskAgainUseFingerprint(boolean dontAskAgain) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            prefs.edit().putBoolean(Manifest.permission.USE_FINGERPRINT, dontAskAgain).apply();
        }
    }

    @Override
    public boolean isDontAsk(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA: {
                return isDontAskAgainCamera();
            }
            case Manifest.permission.ACCESS_FINE_LOCATION: {
                return isDontAskAgainFineLocation();
            }
            case Manifest.permission.ACCESS_COARSE_LOCATION: {
                return isDontAskAgainCoarseLocation();
            }
            case Manifest.permission.READ_PHONE_STATE: {
                return isDontAskAgainPhoneState();
            }
            case Manifest.permission.RECORD_AUDIO: {
                return isDontAskAgainRecordAudio();
            }
            case Manifest.permission.USE_FINGERPRINT: {
                return isDontAskAgainUseFingerprint();
            }
            case Manifest.permission.BODY_SENSORS: {
                return isDontAskAgainBodySensors();
            }
        }
        return false;
    }

    @Override
    public void setIsDontAsk(String permission, boolean isDontAsk) {
        switch (permission) {
            case Manifest.permission.CAMERA: {
                setIsDontAskAgainCamera(isDontAsk);
                break;
            }
            case Manifest.permission.ACCESS_FINE_LOCATION: {
                setIsDontAskAgainFineLocation(isDontAsk);
                break;
            }
            case Manifest.permission.ACCESS_COARSE_LOCATION: {
                setIsDontAskAgainCoarseLocation(isDontAsk);
                break;
            }
            case Manifest.permission.READ_PHONE_STATE: {
                setIsDontAskAgainPhoneState(isDontAsk);
                break;
            }
            case Manifest.permission.RECORD_AUDIO: {
                setIsDontAskAgainRecordAudio(isDontAsk);
                break;
            }
            case Manifest.permission.USE_FINGERPRINT: {
                setIsDontAskAgainUseFingerprint(isDontAsk);
                break;
            }
            case Manifest.permission.BODY_SENSORS: {
                setIsDontAskAgainBodySensors(isDontAsk);
                break;
            }
        }
    }
}
