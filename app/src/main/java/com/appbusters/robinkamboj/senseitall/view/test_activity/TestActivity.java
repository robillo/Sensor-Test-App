package com.appbusters.robinkamboj.senseitall.view.test_activity;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.view.test_activity.other_files.BottomSheetFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.accelerometer_test_fragment.AccelerometerTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.back_camera_test_fragment.BackCamTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.battery_test_fragment.BatteryTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.bluetooth_test_fragment.BluetoothTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.compass_test_fragment.CompassTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.directions.directions_fragment.DirectionsFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.fingerprint_test_fragment.FingerprintTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.flashlight_test_fragment.FlashlightTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.front_camera_test_fragment.FrontCamTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.gps_test_fragment.GpsTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.gravity_test_fragment.GravityTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.gyroscope_test.GyroscopeTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.headphone_jack_test_fragment.JackTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.light_test_fragment.LightTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.linear_acceleration_test.LinearAccTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.magnetic_field_test.MagneticFieldTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.multi_touch_fragment.MultiTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.pressure_sensor_test.PressureTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.proximity_test_fragment.ProximityTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.rotation_vector_test.RotationTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.screen_test_fragment.ScreenTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.speaker_volume_test_fragment.SoundTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.text_scan_test_fragment.TextScanTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.vibrator_test_fragment.VibratorTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.tests.wifi_test_fragment.WifiTestFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AV_OUTPUTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BLUETOOTH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DATA_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAWABLE_ID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_PRESENT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RECYCLER_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GYROSCOPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LIGHT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LINEAR_ACCELERATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MAGNETIC_FIELD;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PRESSURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ROTATION_VECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;

public class TestActivity extends AppCompatActivity implements TestInterface {

    public GenericData intentData = new GenericData();
    public String recyclerName;
    private BottomSheetFragment bottomSheetFragment;

    @BindView(R.id.test_name)
    TextView testName;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setup();
    }

    @Override
    public void initializeIntentData() {
        Bundle intentBundle = getIntent().getExtras();
        if(intentBundle != null) {
            intentData.setName(intentBundle.getString(DATA_NAME));
            intentData.setDrawableId(intentBundle.getInt(DRAWABLE_ID));
            intentData.setPresent(intentBundle.getBoolean(IS_PRESENT));
            intentData.setType(intentBundle.getInt(TYPE));
            recyclerName = intentBundle.getString(RECYCLER_NAME);
        }
        if(recyclerName != null)
            testName.setText(recyclerName.toUpperCase());
    }

    @Override
    public void showCoordinator(String coordinatorText) {
        Snackbar s = Snackbar.make(coordinatorLayout, coordinatorText, Snackbar.LENGTH_SHORT);
        View v = s.getView();
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.red_shade_four));
        TextView t = v.findViewById(android.support.design.R.id.snackbar_text);
        t.setTextColor(ContextCompat.getColor(this, R.color.white));
        t.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        s.show();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);

        initializeIntentData();
        setStatusBarColor();
        setDirectionsFragment();
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

        if(getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_test_fragment)) != null)
            return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new DirectionsFragment(), getString(R.string.tag_directions_fragment));
        transaction.commit();
    }

    @Override
    public void setTestFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_directions_fragment));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left_activity, R.anim.slide_out_left_activity);
        transaction.remove(fragment);
        transaction.commit();

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right_activity, R.anim.slide_out_right_activity);

        switch (intentData.getName()) {
            case SENSOR_PROXIMITY: {
                transaction.add(
                        R.id.container,
                        new ProximityTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_ACCELEROMETER: {
                transaction.add(
                        R.id.container,
                        new AccelerometerTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_LIGHT: {
                transaction.add(
                        R.id.container,
                        new LightTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case BACK_CAMERA: {
                transaction.add(
                        R.id.container,
                        new BackCamTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case FRONT_CAMERA: {
                transaction.add(
                        R.id.container,
                        new FrontCamTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case GPS_LOCATION: {
                transaction.add(
                        R.id.container,
                        new GpsTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case FLASH: {
                transaction.add(
                        R.id.container,
                        new FlashlightTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case MULTI_TOUCH: {
                transaction.add(
                        R.id.container,
                        new MultiTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case FINGERPRINT: {
                transaction.add(
                        R.id.container,
                        new FingerprintTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SCREEN: {
                transaction.add(
                        R.id.container,
                        new ScreenTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case COMPASS: {
                transaction.add(
                        R.id.container,
                        new CompassTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case VIBRATOR: {
                transaction.add(
                        R.id.container,
                        new VibratorTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case AV_OUTPUTS: {
                transaction.add(
                        R.id.container,
                        new JackTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case BATTERY: {
                transaction.add(
                        R.id.container,
                        new BatteryTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case WIFI: {
                transaction.add(
                        R.id.container,
                        new WifiTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case BLUETOOTH: {
                transaction.add(
                        R.id.container,
                        new BluetoothTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SOUND: {
                transaction.add(
                        R.id.container,
                        new SoundTestFragment(),
                        getString(R.string.tag_sound_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_GYROSCOPE: {
                transaction.add(
                        R.id.container,
                        new GyroscopeTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_GRAVITY: {
                transaction.add(
                        R.id.container,
                        new GravityTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_LINEAR_ACCELERATION: {
                transaction.add(
                        R.id.container,
                        new LinearAccTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_ROTATION_VECTOR: {
                transaction.add(
                        R.id.container,
                        new RotationTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_MAGNETIC_FIELD: {
                transaction.add(
                        R.id.container,
                        new MagneticFieldTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_PRESSURE: {
                transaction.add(
                        R.id.container,
                        new PressureTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case TEXT_SCAN: {
                transaction.add(
                        R.id.container,
                        new TextScanTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }

    public void showBottomSheetResults() {
        if(bottomSheetFragment == null) {
            bottomSheetFragment = new BottomSheetFragment();
        }

        bottomSheetFragment.show(getSupportFragmentManager(), getString(R.string.tag_bottom_sheet));
    }

    public void setResultsToBottomSheet(String header, String text) {
        bottomSheetFragment.setHeader(header);
        bottomSheetFragment.setResults(text);
    }
}
