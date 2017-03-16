package com.appbusters.robinkamboj.senseitall.view.robin;

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

public class FlashActivity extends AppCompatActivity {

    private boolean isFlashOn;
    CardView card;
    String sensor_name;
    TextView textView;
    Camera.Parameters parameters;
    private Camera camera;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        isFlashOn = false;

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

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

        card = (CardView) findViewById(R.id.card);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void turnOnFlash(){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setBackgroundColor(getResources().getColor(R.color.colorWhiteShade));
//        card.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
                isFlashOn = true;
            }
        });
    }

    private void turnOffFlash(){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setBackgroundColor(getResources().getColor(R.color.colorBlackShade));
//        card.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.stopPreview();
                isFlashOn = false;
            }
        });
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
