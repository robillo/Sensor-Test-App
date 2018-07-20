package com.appbusters.robinkamboj.senseitall.view.detail_activity;

import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.back_camera.BackCameraFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.fingerprint.FingerprintFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.flash.FlashFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.front_camera.FrontCameraFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.gps_location.GpsFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.multi_touch.MultiTouchFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.accelerometer_sensor.AccelerometerFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.light_sensor.LightFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.proximity_sensor.ProximityFragment;
import com.bumptech.glide.Glide;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DATA_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DIAGNOSTIC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAWABLE_ID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FEATURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_PRESENT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PROXIMITY_TEST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RECYCLER_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LIGHT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_DIAGNOSTICS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_FEATURES;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SENSORS;

public class DetailActivity extends AppCompatActivity implements DetailActivityInterface {

    public GenericData intentData = new GenericData();
    public String recyclerName;

    @BindView(R.id.data_name)
    TextView dataName;

    @BindView(R.id.data_drawable)
    ImageView dataDrawable;

    @BindView(R.id.data_type)
    TextView dataType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setup();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);
        changeStatusBarColor();
        initializeIntentData();
        setFragmentForSensor(intentData.getName());
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

    @Override
    public void initializeIntentData() {
        Bundle intent = getIntent().getExtras();
        if(intent != null) {
            intentData.setName(intent.getString(DATA_NAME));
            intentData.setDrawableId(intent.getInt(DRAWABLE_ID));
            intentData.setPresent(intent.getBoolean(IS_PRESENT));
            intentData.setType(intent.getInt(TYPE));
            recyclerName = intent.getString(RECYCLER_NAME);
        }

        assert recyclerName != null;
        dataName.setText(recyclerName.toUpperCase());
        switch (intentData.getType()) {
            case TYPE_DIAGNOSTICS:
                dataType.setText(DIAGNOSTIC.toUpperCase());
                break;
            case TYPE_FEATURES:
                dataType.setText(FEATURE.toUpperCase());
                break;
            case TYPE_SENSORS:
                dataType.setText(SENSOR.toUpperCase());
                break;
        }
        Glide.with(this).load(intentData.getDrawableId()).into(dataDrawable);
    }

    @Override
    public void setFragmentForSensor(String sensor) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (sensor) {
            case SENSOR_PROXIMITY: {
                transaction.add(R.id.container, new ProximityFragment()).commit();
                break;
            }
            case SENSOR_ACCELEROMETER: {
                transaction.add(R.id.container, new AccelerometerFragment()).commit();
                break;
            }
            case SENSOR_LIGHT: {
                transaction.add(R.id.container, new LightFragment()).commit();
                break;
            }
            case BACK_CAMERA: {
                transaction.add(R.id.container, new BackCameraFragment()).commit();
                break;
            }
            case FRONT_CAMERA: {
                transaction.add(R.id.container, new FrontCameraFragment()).commit();
                break;
            }
            case GPS_LOCATION: {
                transaction.add(R.id.container, new GpsFragment()).commit();
                break;
            }
            case FLASH: {
                transaction.add(R.id.container, new FlashFragment()).commit();
                break;
            }
            case MULTI_TOUCH: {
                transaction.add(R.id.container, new MultiTouchFragment()).commit();
                break;
            }
            case FINGERPRINT: {
                transaction.add(R.id.container, new FingerprintFragment()).commit();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }
}
