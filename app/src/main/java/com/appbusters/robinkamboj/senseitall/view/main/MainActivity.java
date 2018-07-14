package com.appbusters.robinkamboj.senseitall.view.main;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.preferences.AppPreferencesHelper;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RATE_YOUR_EXPERIENCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_DEVICE_TESTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_FEATURES_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_SENSORS_LIST;

public class MainActivity extends AppCompatActivity implements MainInterface {

    private AppPreferencesHelper helper;

    @BindView(R.id.header_text)
    TextSwitcher headerText;

    @BindView(R.id.highlight_tests)
    ImageView highlight_tests;

    @BindView(R.id.highlight_sensors)
    ImageView highlight_sensors;

    @BindView(R.id.highlight_features)
    ImageView highlight_features;

    @BindView(R.id.highlight_rate)
    ImageView highlight_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);

        helper = new AppPreferencesHelper(this);

        Animation in = AnimationUtils.loadAnimation(this, R.anim.top_down_enter_header);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.top_down_exit_header);
        in.setDuration(200);
        out.setDuration(200);

        headerText.setInAnimation(in);
        headerText.setOutAnimation(out);

        setHeaderText();
        changeStatusBarColor();
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
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.button_tests_list)
    public void setShowTests() {
        helper.setHeaderText(SHOWING_DEVICE_TESTS);
        setHeaderText();
    }

    @OnClick(R.id.button_sensors_list)
    public void setShowSensors() {
        helper.setHeaderText(SHOWING_SENSORS_LIST);
        setHeaderText();
    }

    @OnClick(R.id.button_features_list)
    public void setShowFeatures() {
        helper.setHeaderText(SHOWING_FEATURES_LIST);
        setHeaderText();
    }

    @OnClick(R.id.button_rate_experience)
    public void setRateExperience() {
        helper.setHeaderText(RATE_YOUR_EXPERIENCE);
        setHeaderText();
    }

    @Override
    public void setHeaderText() {
        String string = helper.getHeaderText();

        headerText.setText(string);
        switch (string) {
            case SHOWING_DEVICE_TESTS: {
                turnOnHighlight(0);
                break;
            }
            case SHOWING_SENSORS_LIST: {
                turnOnHighlight(1);
                break;
            }
            case SHOWING_FEATURES_LIST: {
                turnOnHighlight(2);
                break;
            }
            case RATE_YOUR_EXPERIENCE: {
                turnOnHighlight(3);
                break;
            }
        }
    }

    @Override
    public void turnOnHighlight(int index) {
        switch (index) {
            case 0: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case 1: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case 2: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case 3: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                break;
            }
        }
    }
}
