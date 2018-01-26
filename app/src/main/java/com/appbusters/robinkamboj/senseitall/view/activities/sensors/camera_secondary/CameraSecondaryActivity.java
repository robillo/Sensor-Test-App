package com.appbusters.robinkamboj.senseitall.view.activities.sensors.camera_secondary;

import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

@SuppressWarnings("deprecation")
public class CameraSecondaryActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    Boolean previewing = false;
    Camera camera;
    Button show_preview, stop_preview;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_secondary);

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
                                camera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
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

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        releaseCameraAndPreview();
        camera = Camera.open();
        previewCamera();

    }

    public void previewCamera()
    {
        if(!previewing){
            camera = Camera.open();
            if(camera!=null){
                try {
                    int rotation = 0;

                    switch (getWindowManager().getDefaultDisplay().getRotation()) {
                        case Surface.ROTATION_0:
                            rotation = 90;
                            break;
                        case Surface.ROTATION_90:
                            rotation = 0;
                            break;
                        case Surface.ROTATION_270:
                            rotation = 180;
                            break;
                        case Surface.ROTATION_180:
                            break;
                    }

                    if (Camera.getNumberOfCameras() >= 2) {
                        try {
                            //if you want to open front facing camera use this line
                            camera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
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

    private void releaseCameraAndPreview() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
