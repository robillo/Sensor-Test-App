package com.appbusters.robinkamboj.senseitall.view.activities.splash;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.activities.main.MainActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.camera.CameraActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.cpu.CPUActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<boolean[]> {

    Animation topDown, bottomUp;
    private List<Sensor> mySensorsList;
    private String[] sensors;
    private boolean[] isPresent = null;

    @BindView(R.id.upper)
    ImageView mUpper;

    @BindView(R.id.lower)
    ImageView mLower;

    @BindView(R.id.ll_one)
    LinearLayout mViewOne;

    @BindView(R.id.ll_two)
    LinearLayout mViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();

        startCountDown(2000);
        Glide.with(this).load(R.drawable.upper).centerCrop().into(mUpper);
        Glide.with(this).load(R.drawable.lower).centerCrop().into(mLower);
        topDown = AnimationUtils.loadAnimation(this, R.anim.top_down);
        bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        mViewOne.startAnimation(topDown);
        mViewTwo.startAnimation(bottomUp);
    }

    public void startNextActivity() {
        if(isPresent!=null){
            Intent intent = new Intent(SplashActivity.this, com.appbusters.robinkamboj.senseitall.view.activities.list.ListActivity.class);
            intent.putExtra("sensors_present", isPresent);
            startActivity(intent);
        }
    }

    public void startCountDown(@SuppressWarnings("SameParameterValue") int millis) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startNextActivity();
            }
        }, millis);
    }

    @Override
    public android.support.v4.content.Loader<boolean[]> onCreateLoader(int id, Bundle args) {

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensorsList = sensorManager != null ? sensorManager.getSensorList(Sensor.TYPE_ALL) : null;
        sensors = getResources().getStringArray(R.array.sensors_list);

        Log.e("loader oncreate", "loading");

        return new MyTaskLoader(SplashActivity.this, sensors, mySensorsList);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<boolean[]> loader, boolean[] isPresent) {

        Log.e("loader onfinished", "loading");

        this.isPresent = isPresent;
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<boolean[]> loader) {

    }
}
