package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.appbusters.robinkamboj.senseitall.view.rishabh.AudioVideoActivity.state;
import static com.appbusters.robinkamboj.senseitall.view.rishabh.AudioVideoActivity.mic;


import static android.content.ContentValues.TAG;

public class ReceiverAudio extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int stateInt = intent.getIntExtra("state", -1);
            int microphone = intent.getIntExtra("microphone", -1);
            String name = intent.getStringExtra("name");

            Log.d(TAG, "onReceive: "+state+" "+microphone+" "+name);

            if(state!=null){
                if(stateInt==1){
                    state.setText("Plugged");
                }else{
                    state.setText("Unplugged");
                }
            }

            if(mic!=null){
                if(microphone==1){
                    mic.setText("Yes");
                }else{
                    mic.setText("No");
                }
            }
        }
    }
}
