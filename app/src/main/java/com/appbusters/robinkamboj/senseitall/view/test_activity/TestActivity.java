package com.appbusters.robinkamboj.senseitall.view.test_activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.test_activity.directions_fragment.DirectionsFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.test_fragment.TestFragment;

import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity implements TestInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setup();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);

        setStatusBarColor();
        setFragments();
    }

    @Override
    public void setStatusBarColor() {
        Window window = getWindow();
        View view = window.getDecorView();
        int flags = view.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public void setDirectionsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new DirectionsFragment(), getString(R.string.tag_directions_fragment));
        transaction.addToBackStack(getString(R.string.tag_directions_fragment));
        transaction.commit();
    }

    @Override
    public void setTestFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new TestFragment(), getString(R.string.tag_test_fragment));
        transaction.commit();
    }

    @Override
    public void setFragments() {
        setTestFragment();
        setDirectionsFragment();
    }

    @Override
    public void onBackPressed() {

        String TAG = getString(R.string.tag_directions_fragment);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);

        if(fragment != null) {
            super.onBackPressed();
        }
        else {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
        }
    }
}
