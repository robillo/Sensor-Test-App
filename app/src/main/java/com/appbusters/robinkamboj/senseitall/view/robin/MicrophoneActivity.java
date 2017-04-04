package com.appbusters.robinkamboj.senseitall.view.robin;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.appbusters.robinkamboj.senseitall.R;
import com.wang.avi.AVLoadingIndicatorView;

public class MicrophoneActivity extends AppCompatActivity {

    private AVLoadingIndicatorView indic_1, indic_2;
    private Button startRec, stopRec, play, pause;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microphone);
    }
}
