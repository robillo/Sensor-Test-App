package com.appbusters.robinkamboj.senseitall.view.detail_activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.PorterDuff;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
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
import com.appbusters.robinkamboj.senseitall.view.detail_activity.android.version_kitkat.KitkatFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.android.version_lollipop.LollipopFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.android.version_marshmallow.MarshmallowFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.android.version_nougat.NougatFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.android.version_pie.PieFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.compass.CompassFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.android_os.OsFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.av_test.JackFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.back_camera.BackCameraFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.barometer.BarometerFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.battery.BatteryFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.bluetooth.BluetoothFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.cpu.CpuFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.fake_touch.FakeTouchFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.fingerprint.FingerprintFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.flash.FlashFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.front_camera.FrontCameraFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.gps_location.GpsFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.gsm.GsmFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.heart_rate_ecg.HeartRateEcgFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.infrared.InfraredFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.mic.MicFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.midi.MidiFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.multi_touch.MultiTouchFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.nfc.NfcFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.radio.RadioFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.screen_test.ScreenFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.sound.SoundFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.usb_acc.UsbFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.vibrator.VibratorFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.vr.VrFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.web_view.WebFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.wifi.WifiFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.features.wifi_direct.WifiDirectFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.information.storage.StorageFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.information.ram.RamFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.accelerometer_sensor.AccelerometerFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.gravity_sensor.GravityFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.gyroscope_sensor.GyroscopeFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.heart_rate_sensor.HeartRateFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.humidity_sensor.HumidityFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.light_sensor.LightFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.linear_accn_sensor.LinearAccelerationFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.mag_field_sensor.MagneticFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.motion_detector.MotionDetectFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.pressure_sensor.PressureFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.proximity_sensor.ProximityFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.rotn_vector_sensor.RotationFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.stationary_detector.StationaryDetectFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.step_counter.StepCountFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.step_detector.StepDetectFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.temperature_sensor.TemperatureFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.software.augmented_reality.AugmentedFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.software.barcode.BarcodeFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.software.face_detect.FaceDetectFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.software.face_emoji.FaceEmojiFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.software.label_generator.LabelFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.software.motion_detect.AiMotionDetectFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.software.text_detect.TextScanFragment;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.software.virtual_reality.VirtualRealityFragment;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AUGMENTED_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AV_OUTPUTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BARCODE_READER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BAROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BLUETOOTH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DATA_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DIAGNOSTIC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAWABLE_ID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_EMOJI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FAKE_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FEATURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.HEART_RATE_ECG;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFORMATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFRARED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_PRESENT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.KITKAT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LABEL_GENERATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LOLLIPOP;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MARSHMALLOW;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MICROPHONE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MIDI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MOTION_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NFC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NOUGAT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.OREO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PIE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RAM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RECYCLER_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GYROSCOPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_HEART_RATE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LIGHT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LINEAR_ACCELERATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MAGNETIC_FIELD;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MOTION_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PRESSURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_RELATIVE_HUMIDITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ROTATION_VECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STATIONARY_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_COUNTER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_TEMPERATURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOFTWARE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_ANDROID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_DIAGNOSTICS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_FEATURES;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_INFORMATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SENSORS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SOFTWARE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.USB_ACCESSORY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIRTUAL_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VR_MODE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WEB_VIEW;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI_DIRECT;

public class DetailActivity extends AppCompatActivity implements DetailActivityInterface {

    @SuppressWarnings("FieldCanBeLocal")
    private WifiManager wifiManager;
    public GenericData intentData = new GenericData();
    public String recyclerName;

    @BindView(R.id.coordinatorLayout)
    public
    CoordinatorLayout coordinatorLayout;

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

        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        switch (intentData.getName()) {
            case WIFI: {
                if(!wifiManager.isWifiEnabled()) wifiManager.setWifiEnabled(true);
                break;
            }
        }

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
            case TYPE_INFORMATION:
                dataType.setText(INFORMATION.toUpperCase());
                break;
            case TYPE_SOFTWARE:
                dataType.setText(SOFTWARE.toUpperCase());
                break;
            case TYPE_ANDROID:
                dataType.setText(ANDROID.toUpperCase());
                dataDrawable.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
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
            case SCREEN: {
                transaction.add(R.id.container, new ScreenFragment()).commit();
                break;
            }
            case COMPASS: {
                transaction.add(R.id.container, new CompassFragment()).commit();
                break;
            }
            case VIBRATOR: {
                transaction.add(R.id.container, new VibratorFragment()).commit();
                break;
            }
            case AV_OUTPUTS: {
                transaction.add(R.id.container, new JackFragment()).commit();
                break;
            }
            case BATTERY: {
                transaction.add(R.id.container, new BatteryFragment()).commit();
                break;
            }
            case BLUETOOTH: {
                transaction.add(R.id.container, new BluetoothFragment()).commit();
                break;
            }
            case WIFI: {
                transaction.add(R.id.container, new WifiFragment()).commit();
                break;
            }
            case SOUND: {
                transaction.add(R.id.container, new SoundFragment()).commit();
                break;
            }
            case SENSOR_GRAVITY: {
                transaction.add(R.id.container, new GravityFragment()).commit();
                break;
            }
            case SENSOR_TEMPERATURE: {
                transaction.add(R.id.container, new TemperatureFragment()).commit();
                break;
            }
            case SENSOR_PRESSURE: {
                transaction.add(R.id.container, new PressureFragment()).commit();
                break;
            }
            case SENSOR_RELATIVE_HUMIDITY: {
                transaction.add(R.id.container, new HumidityFragment()).commit();
                break;
            }
            case SENSOR_GYROSCOPE: {
                transaction.add(R.id.container, new GyroscopeFragment()).commit();
                break;
            }
            case SENSOR_LINEAR_ACCELERATION: {
                transaction.add(R.id.container, new LinearAccelerationFragment()).commit();
                break;
            }
            case SENSOR_ROTATION_VECTOR: {
                transaction.add(R.id.container, new RotationFragment()).commit();
                break;
            }
            case SENSOR_MAGNETIC_FIELD: {
                transaction.add(R.id.container, new MagneticFragment()).commit();
                break;
            }
            case SENSOR_HEART_RATE: {
                transaction.add(R.id.container, new HeartRateFragment()).commit();
                break;
            }
            case SENSOR_STEP_DETECTOR: {
                transaction.add(R.id.container, new StepDetectFragment()).commit();
                break;
            }
            case SENSOR_STEP_COUNTER: {
                transaction.add(R.id.container, new StepCountFragment()).commit();
                break;
            }
            case SENSOR_MOTION_DETECTOR: {
                transaction.add(R.id.container, new MotionDetectFragment()).commit();
                break;
            }
            case SENSOR_STATIONARY_DETECTOR: {
                transaction.add(R.id.container, new StationaryDetectFragment()).commit();
                break;
            }
            case GSM_UMTS: {
                transaction.add(R.id.container, new GsmFragment()).commit();
                break;
            }
            case RADIO: {
                transaction.add(R.id.container, new RadioFragment()).commit();
                break;
            }
            case CPU: {
                transaction.add(R.id.container, new CpuFragment()).commit();
                break;
            }
            case ANDROID_OS: {
                transaction.add(R.id.container, new OsFragment()).commit();
                break;
            }
            case INFRARED: {
                transaction.add(R.id.container, new InfraredFragment()).commit();
                break;
            }
            case NFC: {
                transaction.add(R.id.container, new NfcFragment()).commit();
                break;
            }
            case MICROPHONE: {
                transaction.add(R.id.container, new MicFragment()).commit();
                break;
            }
            case USB_ACCESSORY: {
                transaction.add(R.id.container, new UsbFragment()).commit();
                break;
            }
            case BAROMETER: {
                transaction.add(R.id.container, new BarometerFragment()).commit();
                break;
            }
            case WIFI_DIRECT: {
                transaction.add(R.id.container, new WifiDirectFragment()).commit();
                break;
            }
            case HEART_RATE_ECG: {
                transaction.add(R.id.container, new HeartRateEcgFragment()).commit();
                break;
            }
            case FAKE_TOUCH: {
                transaction.add(R.id.container, new FakeTouchFragment()).commit();
                break;
            }
            case WEB_VIEW: {
                transaction.add(R.id.container, new WebFragment()).commit();
                break;
            }
            case MIDI: {
                transaction.add(R.id.container, new MidiFragment()).commit();
                break;
            }
            case VR_MODE: {
                transaction.add(R.id.container, new VrFragment()).commit();
                break;
            }
            case STORAGE: {
                transaction.add(R.id.container, new StorageFragment()).commit();
                break;
            }
            case RAM: {
                transaction.add(R.id.container, new RamFragment()).commit();
                break;
            }
            case PIE: {
                transaction.add(R.id.container, new PieFragment()).commit();
                break;
            }
            case OREO: {
                transaction.add(R.id.container, new PieFragment()).commit();
                break;
            }
            case NOUGAT: {
                transaction.add(R.id.container, new NougatFragment()).commit();
                break;
            }
            case MARSHMALLOW: {
                transaction.add(R.id.container, new MarshmallowFragment()).commit();
                break;
            }
            case LOLLIPOP: {
                transaction.add(R.id.container, new LollipopFragment()).commit();
                break;
            }
            case KITKAT: {
                transaction.add(R.id.container, new KitkatFragment()).commit();
                break;
            }
            case MOTION_DETECT: {
                transaction.add(R.id.container, new AiMotionDetectFragment()).commit();
                break;
            }
            case AUGMENTED_REALITY: {
                transaction.add(R.id.container, new AugmentedFragment()).commit();
                break;
            }
            case VIRTUAL_REALITY: {
                transaction.add(R.id.container, new VirtualRealityFragment()).commit();
                break;
            }
            case FACE_DETECT: {
                transaction.add(R.id.container, new FaceDetectFragment()).commit();
                break;
            }
            case FACE_EMOJI: {
                transaction.add(R.id.container, new FaceEmojiFragment()).commit();
                break;
            }
            case BARCODE_READER: {
                transaction.add(R.id.container, new BarcodeFragment()).commit();
                break;
            }
            case TEXT_SCAN: {
                transaction.add(R.id.container, new TextScanFragment()).commit();
                break;
            }
            case LABEL_GENERATOR: {
                transaction.add(R.id.container, new LabelFragment()).commit();
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
        try {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
        }
        catch (IllegalStateException ignored) {}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
