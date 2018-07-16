package com.appbusters.robinkamboj.senseitall.view.main;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.Recycler_View_Adapter;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;
import com.appbusters.robinkamboj.senseitall.preferences.AppPreferencesHelper;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.main.adapter.GenericDataAdapter;
import com.appbusters.robinkamboj.senseitall.view.main.list_fragment.ListFragment;
import com.appbusters.robinkamboj.senseitall.view.splash.helper_classes.MyTaskLoader;
import com.appbusters.robinkamboj.senseitall.view.splash.splash_activity.SplashActivity;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.DefaultSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PRESENT_DIAGNOSTICS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PRESENT_FEATURES;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PRESENT_SENSORS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RATE_YOUR_EXPERIENCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_DEVICE_TESTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_FEATURES_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_SENSORS_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_DIAGNOSTICS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_FEATURES;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_RATE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SENSORS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.diagnosticsPointer;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.imageUrlMap;

public class MainActivity extends AppCompatActivity
        implements MainInterface {

    @BindView(R.id.container)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);

        setListFragment();
    }

    @Override
    public void changeStatusBarColor() {

    }

    @Override
    public void setListFragment() {
        String TAG = getString(R.string.tag_list_fragment);

        if(getSupportFragmentManager().findFragmentByTag(TAG) != null)
            return;

        ListFragment fragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, TAG).commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
