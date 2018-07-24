package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.headphone_jack_test_fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.appbusters.robinkamboj.senseitall.R;

public class ReceiverAudio extends BroadcastReceiver {

    JackTestFragment fragment;

    public ReceiverAudio(JackTestFragment fragment) {
        this.fragment = fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null)
            if(intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {

                int stateInt = intent.getIntExtra("state", -1);

                String state;
                if(context != null) {
                    if(stateInt == 1)  {
                        state = "STATE: PLUGGED";
                        fragment.jackImage.setColorFilter(ContextCompat.getColor(context, R.color.green_shade_three));
                    }
                    else if(stateInt == 0){
                        state = "STATE: UNPLUGGED";
                        fragment.jackImage.setColorFilter(ContextCompat.getColor(context, R.color.red_shade_four));
                    }
                    else {
                        state = "";
                    }
                    fragment.jackTextStatus.setText(state);
                }
            }
    }
}
