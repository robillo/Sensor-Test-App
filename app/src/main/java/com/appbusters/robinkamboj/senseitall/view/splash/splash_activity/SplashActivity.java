package com.appbusters.robinkamboj.senseitall.view.splash.splash_activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PRESENT_DIAGNOSTICS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PRESENT_FEATURES;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PRESENT_SENSORS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.diagnosticsNames;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.featureNames;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.sensorNames;

public class SplashActivity extends AppCompatActivity implements SplashMvpView {

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
        changeStatusBarColor();
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
        startActivity(new Intent(this, MainActivity.class));
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

    @Override
    public void changeStatusBarColor() {
        Window window = getWindow();
        View view = window.getDecorView();
        int flags = view.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
}
