package com.appbusters.robinkamboj.senseitall.view.activities.sensors.flash;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

@SuppressWarnings("deprecation")
public class FlashActivity extends AppCompatActivity {

    private boolean isFlashOn = false;
    String sensor_name;
    TextView textView;
    Camera.Parameters parameters;
    private Camera camera;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        imageView = (ImageView) findViewById(R.id.flashlight);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFlashOn) {
                    turnOnFlash();
                }
                else {
                    turnOffFlash();
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("IS_FLASH_ON", isFlashOn);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isFlashOn = savedInstanceState.getBoolean("IS_FLASH_ON");
        if(isFlashOn){
            turnOnFlash();
        }
    }

    private void turnOnFlash(){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setBackgroundColor(getResources().getColor(R.color.colorWhiteShade));
                Log.e("params", "before");
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
                isFlashOn = true;
                Log.e("params", "after");
            }
        });
    }

    private void turnOffFlash(){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setBackgroundColor(getResources().getColor(R.color.colorBlackShade));
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.stopPreview();
                isFlashOn = false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // on starting the app get the camera params
        camera = Camera.open();
        parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // on starting the app get the camera params
        camera = Camera.open();
        parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // on starting the app get the camera params
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        camera = Camera.open();
        parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // on stop release the camera
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
