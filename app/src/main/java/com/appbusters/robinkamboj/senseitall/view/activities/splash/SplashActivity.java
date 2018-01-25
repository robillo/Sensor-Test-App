package com.appbusters.robinkamboj.senseitall.view.activities.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.activities.main.MainActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

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

        ButterKnife.bind(this);

        startCountDown(2000);
        Glide.with(this).load(R.drawable.upper).centerCrop().into(mUpper);
        Glide.with(this).load(R.drawable.lower).centerCrop().into(mLower);
        topDown = AnimationUtils.loadAnimation(this, R.anim.top_down);
        bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        mViewOne.startAnimation(topDown);
        mViewTwo.startAnimation(bottomUp);
    }

    public void startNextActivity() {
        startActivity(new Intent(this, MainActivity.class));
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
}
