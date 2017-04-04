package com.appbusters.robinkamboj.senseitall.view.robin;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.appbusters.robinkamboj.senseitall.R;
import com.wang.avi.AVLoadingIndicatorView;

public class MicrophoneActivity extends AppCompatActivity {

    private AVLoadingIndicatorView indic_1, indic_2;
    private Button startRec, stopRec, play, pause;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microphone);

        startRec = (Button) findViewById(R.id.start_rec);
        stopRec = (Button) findViewById(R.id.stop_rec);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);

        stopRec.setEnabled(false);
        play.setEnabled(false);
        pause.setEnabled(false);

        mediaRecorder = new MediaRecorder();
    }
}
