package com.appbusters.robinkamboj.senseitall.view.sensors.screen;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class ScreenActivity extends AppCompatActivity {

    private TextView has_touch, has_touchscreen_emulation, basic_multitouch, extended_multitouch, full_hand_multitouch,
            screen_height, screen_width, screen_density, scaled_density, density, x_dpi, y_dpi;
    private String[] results;
    private Point size;
    private DisplayMetrics metrics;
    private String sensor_name;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        has_touch = (TextView) findViewById(R.id.has_touch);
        has_touchscreen_emulation = (TextView) findViewById(R.id.touchscreen_emulation);
        basic_multitouch = (TextView) findViewById(R.id.has_basic_multitouch);
        extended_multitouch = (TextView) findViewById(R.id.has_extended_multitouch);
        full_hand_multitouch = (TextView) findViewById(R.id.has_full_hand_multitouch);
        screen_height = (TextView) findViewById(R.id.screen_height);
        screen_width = (TextView) findViewById(R.id.screen_width);
        screen_density = (TextView) findViewById(R.id.screen_density);
        scaled_density = (TextView) findViewById(R.id.scaled_density);
        density = (TextView) findViewById(R.id.density);
        x_dpi = (TextView) findViewById(R.id.x_dpi);
        y_dpi = (TextView) findViewById(R.id.y_dpi);

        Display display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setResults();
                setTextviews();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setResults(){
        results = new String[]{
                "Has Touch? :  " + String.valueOf(getPackageManager().hasSystemFeature("android.hardware.touchscreen")),
                "Has Touchscreen Emulation? :  " + String.valueOf(getPackageManager().hasSystemFeature("android.hardware.faketouch")),
                "Has Basic MultiTouch? :  " + String.valueOf(getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")),
                "Has Extended MultiTouch? :  " + String.valueOf(getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct")),
                "Has Full Hand MultiTouch? :  " + String.valueOf(getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct")),
                "Screen Height:  " + String.valueOf(size.x),
                "Screen Width:  " + String.valueOf(size.y),
                "Screen Density:  " + String.valueOf(metrics.density),
                "Scaled Density:  " + String.valueOf(metrics.scaledDensity),
                "Density:  " + String.valueOf(metrics.densityDpi),
                "X dpi:  " + String.valueOf(metrics.xdpi),
                "Y dpi:  " + String.valueOf(metrics.ydpi)
        };
    }

    private void setTextviews(){
        has_touch.setText(results[0]);
        has_touchscreen_emulation.setText(results[1]);
        basic_multitouch.setText(results[2]);
        extended_multitouch.setText(results[3]);
        full_hand_multitouch.setText(results[4]);
        screen_height.setText(results[5]);
        screen_width.setText(results[6]);
        screen_density.setText(results[7]);
        scaled_density.setText(results[8]);
        density.setText(results[9]);
        x_dpi.setText(results[10]);
        y_dpi.setText(results[11]);
    }
}
