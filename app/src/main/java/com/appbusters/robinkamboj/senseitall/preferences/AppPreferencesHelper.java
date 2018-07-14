package com.appbusters.robinkamboj.senseitall.preferences;

import android.content.Context;
import android.content.SharedPreferences;

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
}
