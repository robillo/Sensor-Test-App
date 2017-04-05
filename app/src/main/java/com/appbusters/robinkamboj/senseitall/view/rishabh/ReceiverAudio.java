package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by rishabhshukla on 05/04/17.
 */

public class ReceiverAudio extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            int microphone = intent.getIntExtra("microphone", -1);
            String name = intent.getStringExtra("name");

            Log.d(TAG, "onReceive: "+state+" "+microphone+" "+name);
        }
    }
}
