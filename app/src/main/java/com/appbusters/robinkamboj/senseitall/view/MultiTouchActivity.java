package com.appbusters.robinkamboj.senseitall.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class MultiTouchActivity extends Activity{
    private TextView txtInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_multi_touch);

        txtInfo = (TextView) findViewById(R.id.txtInfo);

        Button btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.getBackground().setAlpha(128);
    }


}