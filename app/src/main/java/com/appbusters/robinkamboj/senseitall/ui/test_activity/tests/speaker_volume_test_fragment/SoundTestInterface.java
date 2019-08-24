package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.speaker_volume_test_fragment;

import android.view.View;

public interface SoundTestInterface {

    void setup(View v);

    void initialize();

    void playOrPause();

    void play();

    void pause();

    void volumeUpButtonPressed();

    void volumeDownButtonPressed();

    void setCroller();
}
