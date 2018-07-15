package com.appbusters.robinkamboj.senseitall.view.splash.splash_activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.main.MainActivity;
import com.appbusters.robinkamboj.senseitall.view.splash.helper_classes.MyTaskLoader;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity
        implements android.support.v4.app.LoaderManager.LoaderCallbacks<boolean[][]>, SplashMvpView {

    Animation topDown, bottomUp;

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

        setup();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);
        getSupportLoaderManager().initLoader(AppConstants.LOADER_ID, null, this).forceLoad();

        startCountDown(2000);
        animateStuff();
    }

    @Override
    public void animateStuff() {
        Glide.with(this).load(R.drawable.upper).into(mUpper);
        Glide.with(this).load(R.drawable.lower).into(mLower);
        topDown = AnimationUtils.loadAnimation(this, R.anim.top_down);
        bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        mViewOne.startAnimation(topDown);
        mViewTwo.startAnimation(bottomUp);
    }

    @Override
    public void startListActivity() {
        if(isPresent!=null){
            Intent intent = new Intent(
                    SplashActivity.this,
                    MainActivity.class
            );
            intent.putExtra(AppConstants.PRESENT_SENSORS, isPresent[0]);
            intent.putExtra(AppConstants.PRESENT_FEATURES, isPresent[1]);
            intent.putExtra(AppConstants.PRESENT_DIAGNOSTICS, isPresent[2]);
            startActivity(intent);
        }
    }

    @Override
    public void startCountDown(@SuppressWarnings("SameParameterValue") int millis) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startListActivity();
            }
        }, millis);
    }

    private boolean[][] isPresent = null;

    @NonNull
    @Override
    public android.support.v4.content.Loader<boolean[][]> onCreateLoader(int id, Bundle args) {

        SensorManager sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<String> sensors = AppConstants.sensorNames;
        HashMap<String, Integer> sMap = AppConstants.sensorManagerInts;

        PackageManager fManager = this.getPackageManager();
        List<String> features = AppConstants.featureNames;
        HashMap<String, String> fMap = AppConstants.packageManagerPaths;

        List<String> diagnostics = AppConstants.diagnosticsNames;

        Vibrator vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        ConsumerIrManager infrared = (ConsumerIrManager) this.getSystemService(CONSUMER_IR_SERVICE);

        Log.e("tag", "create loader");

        return new MyTaskLoader(
                SplashActivity.this,
                sManager,
                sensors,
                sMap,
                fManager,
                features,
                fMap,
                vibrator,
                infrared,
                diagnostics
        );
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<boolean[][]> loader, boolean[][] isPresent) {
        this.isPresent = isPresent;
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<boolean[][]> loader) {

    }
}
