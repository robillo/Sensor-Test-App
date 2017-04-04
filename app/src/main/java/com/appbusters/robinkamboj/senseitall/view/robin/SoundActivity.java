package com.appbusters.robinkamboj.senseitall.view.robin;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.appbusters.robinkamboj.senseitall.R;
import com.bumptech.glide.Glide;

public class SoundActivity extends AppCompatActivity {

    private ImageButton play, pause;
    private SeekBar volume;
    private ImageView bg;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        play = (ImageButton) findViewById(R.id.play);
        pause = (ImageButton) findViewById(R.id.pause);
        volume = (SeekBar) findViewById(R.id.volume);
        bg = (ImageView) findViewById(R.id.bg);

        Glide.with(getApplicationContext()).load(R.drawable.sound_bg)
                .centerCrop()
                .into(bg);

        mediaPlayer = MediaPlayer.create(this, R.raw.song1);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}
