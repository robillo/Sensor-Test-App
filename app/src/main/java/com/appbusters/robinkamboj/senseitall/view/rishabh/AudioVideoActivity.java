package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioRecordingConfiguration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.appbusters.robinkamboj.senseitall.R;

public class AudioVideoActivity extends AppCompatActivity {

    private static final String TAG = "AV";
    AudioManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video);

        am = (AudioManager)getSystemService(AUDIO_SERVICE);
        Log.d(TAG, "onCreate: "+am.generateAudioSessionId());
        Log.d(TAG, "onCreate: "+am.generateAudioSessionId());
        Log.d(TAG, "onCreate: "+am.generateAudioSessionId());
        Log.d(TAG, "onCreate: "+am.generateAudioSessionId());
        for(AudioDeviceInfo i:am.getDevices(AudioManager.GET_DEVICES_ALL)){
            Log.d(TAG, "onCreate: "+i.getProductName() +" "+i.getId()+" "+i.isSink());
        }
    }
}
