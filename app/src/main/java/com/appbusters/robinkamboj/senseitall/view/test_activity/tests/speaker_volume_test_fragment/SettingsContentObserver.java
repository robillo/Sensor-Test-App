package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.speaker_volume_test_fragment;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;

import junit.framework.Test;

public class SettingsContentObserver extends ContentObserver {

    private AudioManager audioManager;
    private Context activityContext;

    SettingsContentObserver(Context activityContext, Handler handler, AudioManager audioManager) {
        super(handler);
        this.audioManager = audioManager;
        this.activityContext = activityContext;
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.e("tag", "Settings change detected volume " + currentVolume);
        TestActivity testActivity = (TestActivity) activityContext;
        if(testActivity != null) {
            SoundTestFragment fragment = (
                    SoundTestFragment) ((TestActivity) activityContext)
                    .getSupportFragmentManager()
                    .findFragmentByTag(activityContext.getString(R.string.tag_sound_test_fragment));

            if(fragment != null)
                fragment.setCroller();
        }
    }
}
