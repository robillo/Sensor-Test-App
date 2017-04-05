package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.MultiTouchCanvas;

import java.util.List;

public class MultiTouchActivity extends Activity implements MultiTouchCanvas.MultiTouchStatusListener {

    TextView tvInfo;
    private String sensor_name;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_multi_touch);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

        ((MultiTouchCanvas) findViewById(R.id.multiTouchView)).setStatusListener(this);

    }

    @Override
    public void onStatus(List<Point> pointerLocations, int numPoints) {

        String str = String.format("Touches Detected: %s \n", Integer.toString(numPoints));
        for (int i = 0; i < numPoints; i++) {
            str += " [";
            str += pointerLocations.get(i).x + "," + pointerLocations.get(i).y;
            str += "]";
        }
        tvInfo.setText(str);

    }

}