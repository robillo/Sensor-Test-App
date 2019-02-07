package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.speaker_volume_test_fragment;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.sdsmdg.harjot.crollerTest.Croller;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoundTestFragment extends Fragment implements SoundTestInterface, MediaPlayer.OnCompletionListener {

    private SettingsContentObserver contentObserver;

    @BindView(R.id.croller)
    Croller croller;

    @BindView(R.id.play_pause)
    TextView play_pause;

    private boolean isPlaying = false;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    public SoundTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_sound_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initialize();
    }

    @Override
    public void initialize() {

        if(getActivity() == null) return;

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        contentObserver = new SettingsContentObserver(getActivity(), new Handler());

        croller.setOnProgressChangedListener(new Croller.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int volumeProgress) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeProgress, 0);
            }
        });

        if(audioManager == null) return;

        setCroller();

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.song1);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void playOrPause() {
        if(isPlaying) {
            pause();
            if(getActivity() != null) play_pause.setText(getActivity().getString(R.string.play));
        }
        else {
            play();
            if(getActivity() != null) play_pause.setText(getActivity().getString(R.string.pause));
        }
        isPlaying = !isPlaying;
    }

    @Override
    public void play() {
        if(mediaPlayer != null && !mediaPlayer.isPlaying()) mediaPlayer.start();
    }

    @Override
    public void pause() {
        if(mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.pause();
    }

    @Override
    public void volumeUpButtonPressed() {
        if(audioManager == null) return;
        setCroller();
    }

    @Override
    public void volumeDownButtonPressed() {
        if(audioManager == null) return;
        setCroller();
    }

    @Override
    public void setCroller() {
        croller.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        croller.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    @Override
    public void onPause() {

        if(getActivity() != null) {
            getActivity().getApplicationContext().getContentResolver().unregisterContentObserver(contentObserver);
        }

        isPlaying = false;
        if(mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.pause();
        if(getActivity() != null) play_pause.setText(getActivity().getString(R.string.play));

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getActivity() != null) {
            getActivity().getApplicationContext().getContentResolver().registerContentObserver(
                    android.provider.Settings.System.CONTENT_URI,
                    true,
                    contentObserver
            );
        }
    }

    @OnClick(R.id.play_pause)
    public void setPlayPause() {
        playOrPause();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        isPlaying = false;
        if(mediaPlayer != null) mediaPlayer.pause();
        if(getActivity() != null) play_pause.setText(getActivity().getString(R.string.play));
    }
}
