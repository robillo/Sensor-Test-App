package com.appbusters.robinkamboj.senseitall.view.sensors.vibrator;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.Timer;
import java.util.TimerTask;

public class VibratorActivity extends AppCompatActivity {

    private RelativeLayout activity_vibrate;
    private Context context;
    private Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vibrator);

        context = getApplicationContext();

        vib = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card: {
                vib.vibrate(500);
                break;
            }
        }
    }
}
