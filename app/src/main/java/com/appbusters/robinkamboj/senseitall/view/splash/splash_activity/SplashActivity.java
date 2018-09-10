package com.appbusters.robinkamboj.senseitall.view.splash.splash_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.DashboardActivity;

import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class SplashActivity extends AppCompatActivity implements SplashMvpView {

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

        startCountDown(1000);
    }

    @Override
    public void startDashboardActivity() {
        startActivity(new Intent(this, DashboardActivity.class));
        overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity);
    }

    @Override
    public void startCountDown(int millis) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //noinspection ConstantConditions
                if(SplashActivity.this != null)
                    SplashActivity.this.startDashboardActivity();
            }
        }, millis);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void changeStatusBarColor() {
        Window window = getWindow();
        if(window == null) return;
        View view = window.getDecorView();
        if(view == null) return;
        int flags = view.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
}
