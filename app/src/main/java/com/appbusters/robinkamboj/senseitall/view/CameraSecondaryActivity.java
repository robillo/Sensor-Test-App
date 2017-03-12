package com.appbusters.robinkamboj.senseitall.view;

import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import java.io.IOException;

public class CameraSecondaryActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    Boolean previewing = false;
    Camera camera;
    Button show_preview, stop_preview;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Camera.Parameters parameters;

    String sensor_name;
    TextView textView;

    private final String tag = "VideoServer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_secondary);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);
        show_preview = (Button) findViewById(R.id.show_preview);
        stop_preview = (Button) findViewById(R.id.stop_preview);

        surfaceView = (SurfaceView)findViewById(R.id.preview);
        surfaceHolder = surfaceView.getHolder();


        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        show_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!previewing){
                    camera = Camera.open();
                    if(camera!=null){
                        try {
                            if (Camera.getNumberOfCameras() >= 2) {
                                //if you want to open front facing camera use this line
                                camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
                            }
                            camera.setDisplayOrientation(90);
                            camera.setPreviewDisplay(surfaceHolder);
                            camera.startPreview();
                            previewing = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        stop_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(camera!=null && previewing){
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                    previewing = false;
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //nonspection SimplifiableIfStatement

        switch (id){
            case R.id.action_settings:{
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

        if(camera!=null && previewing){
            camera.stopPreview();
            camera.release();
            camera = null;
            previewing = false;
        }

        camera = Camera.open();

        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

//        if(display.getRotation() == Surface.ROTATION_0)
//        {
//            parameters.setPreviewSize(height, width);
//            camera.setDisplayOrientation(90);
//        }
//
//        if(display.getRotation() == Surface.ROTATION_90)
//        {
//            parameters.setPreviewSize(width, height);
//        }
//
//        if(display.getRotation() == Surface.ROTATION_180)
//        {
//            parameters.setPreviewSize(height, width);
//        }
//
//        if(display.getRotation() == Surface.ROTATION_270)
//        {
//            parameters.setPreviewSize(width, height);
//            camera.setDisplayOrientation(180);
//        }



//        camera.setParameters(parameters);
        previewCamera();

    }

    public void previewCamera()
    {
        if(!previewing){
            camera = Camera.open();
            if(camera!=null){
                try {
                    int rotation = 0;

                    switch (getWindowManager().getDefaultDisplay().getRotation()){
                        case Surface.ROTATION_0:
                            rotation = 90;
                            break;
                        case Surface.ROTATION_90:
                            rotation = 0;
                            break;
                        case Surface.ROTATION_270:
                            rotation = 180;
                            break;
                    }

                    if (Camera.getNumberOfCameras() >= 2) {
                        //if you want to open front facing camera use this line
                        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
                    }
                    camera.setDisplayOrientation(rotation);
                    camera.getParameters().setRotation(rotation);
                    camera.setPreviewDisplay(surfaceHolder);
                    camera.startPreview();
                    previewing = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}

