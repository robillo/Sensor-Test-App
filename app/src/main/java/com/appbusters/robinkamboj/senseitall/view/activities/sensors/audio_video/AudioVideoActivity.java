package com.appbusters.robinkamboj.senseitall.view.activities.sensors.audio_video;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class AudioVideoActivity extends AppCompatActivity {

    private static final String TAG = "AV";
    AudioManager am;
    ReceiverAudio receiver;
    public static TextView state, mic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video);

        state = findViewById(R.id.state);
        mic = findViewById(R.id.mic);


        IntentFilter receiverFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        receiver = new ReceiverAudio();
        registerReceiver( receiver, receiverFilter );

    }

    @Override
    protected void onPause() {
        if(receiver!=null){
            unregisterReceiver(receiver);
        }
        super.onPause();
    }
}
