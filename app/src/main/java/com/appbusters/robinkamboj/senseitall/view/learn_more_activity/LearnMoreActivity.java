package com.appbusters.robinkamboj.senseitall.view.learn_more_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.model.recycler.LearnMoreItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_OS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AV_OUTPUTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BARCODE_READER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BAROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BLUETOOTH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CPU;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DATA_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAWABLE_ID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FAKE_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.HEART_RATE_ECG;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFRARED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_PRESENT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LABEL_GENERATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MICROPHONE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MIDI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NFC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RADIO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RAM;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
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
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STORAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.USB_ACCESSORY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIRTUAL_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VR_MODE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WEB_VIEW;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI_DIRECT;

public class LearnMoreActivity extends AppCompatActivity  implements LearnMoreInterface {

    public GenericData intentData = new GenericData();
    private String[] headers, descriptions, images;
    private List<LearnMoreItem> list = new ArrayList<>();

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.more_about_sensor)
    TextView more_about_sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);

        setup();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);

        setStatusBarColor();

        initialize();

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

    @SuppressLint("InlinedApi")
    @Override
    public void getStrings() {

        switch (intentData.getName()) {
            case SENSOR_PROXIMITY: {
                headers = getResources().getStringArray(R.array.proximity_headers);
                descriptions = getResources().getStringArray(R.array.proximity_descriptions);
                images = getResources().getStringArray(R.array.proximity_images);
                break;
            }
            case SENSOR_ACCELEROMETER: {
                headers = getResources().getStringArray(R.array.accelerometer_headers);
                descriptions = getResources().getStringArray(R.array.accelerometer_descriptions);
                images = getResources().getStringArray(R.array.accelerometer_images);
                break;
            }
            case SENSOR_LIGHT: {
                headers = getResources().getStringArray(R.array.light_headers);
                descriptions = getResources().getStringArray(R.array.light_descriptions);
                images = getResources().getStringArray(R.array.light_images);
                break;
            }
            case BACK_CAMERA: {
                headers = getResources().getStringArray(R.array.camera_headers);
                descriptions = getResources().getStringArray(R.array.camera_descriptions);
                images = getResources().getStringArray(R.array.camera_images);
                break;
            }
            case FRONT_CAMERA: {
                headers = getResources().getStringArray(R.array.camera_headers);
                descriptions = getResources().getStringArray(R.array.camera_descriptions);
                images = getResources().getStringArray(R.array.camera_images);
                break;
            }
            case GPS_LOCATION: {
                headers = getResources().getStringArray(R.array.gps_headers);
                descriptions = getResources().getStringArray(R.array.gps_descriptions);
                images = getResources().getStringArray(R.array.gps_images);
                break;
            }
            case FLASH: {
                headers = getResources().getStringArray(R.array.flash_headers);
                descriptions = getResources().getStringArray(R.array.flash_descriptions);
                images = getResources().getStringArray(R.array.flash_images);
                break;
            }
            case MULTI_TOUCH: {
                headers = getResources().getStringArray(R.array.multi_touch_headers);
                descriptions = getResources().getStringArray(R.array.multi_touch_descriptions);
                images = getResources().getStringArray(R.array.multi_touch_images);
                break;
            }
            case FINGERPRINT: {
                headers = getResources().getStringArray(R.array.fingerprint_headers);
                descriptions = getResources().getStringArray(R.array.fingerprint_descriptions);
                images = getResources().getStringArray(R.array.fingerprint_images);
                break;
            }
            case SCREEN: {
                headers = getResources().getStringArray(R.array.screen_headers);
                descriptions = getResources().getStringArray(R.array.screen_descriptions);
                images = getResources().getStringArray(R.array.screen_images);
                break;
            }
            case COMPASS: {
                headers = getResources().getStringArray(R.array.compass_headers);
                descriptions = getResources().getStringArray(R.array.compass_descriptions);
                images = getResources().getStringArray(R.array.compass_images);
                break;
            }
            case VIBRATOR: {
                headers = getResources().getStringArray(R.array.vibrator_headers);
                descriptions = getResources().getStringArray(R.array.vibrator_descriptions);
                images = getResources().getStringArray(R.array.vibrator_images);
                break;
            }
            case AV_OUTPUTS: {
                headers = getResources().getStringArray(R.array.av_headers);
                descriptions = getResources().getStringArray(R.array.av_descriptions);
                images = getResources().getStringArray(R.array.av_images);
                break;
            }
            case BATTERY: {
                headers = getResources().getStringArray(R.array.battery_headers);
                descriptions = getResources().getStringArray(R.array.battery_descriptions);
                images = getResources().getStringArray(R.array.battery_images);
                break;
            }
            case BLUETOOTH: {
                headers = getResources().getStringArray(R.array.bluetooth_headers);
                descriptions = getResources().getStringArray(R.array.bluetooth_descriptions);
                images = getResources().getStringArray(R.array.bluetooth_images);
                break;
            }
            case WIFI: {
                headers = getResources().getStringArray(R.array.wifi_headers);
                descriptions = getResources().getStringArray(R.array.wifi_descriptions);
                images = getResources().getStringArray(R.array.wifi_images);
                break;
            }
            case SOUND: {
                headers = getResources().getStringArray(R.array.sound_headers);
                descriptions = getResources().getStringArray(R.array.sound_descriptions);
                images = getResources().getStringArray(R.array.sound_images);
                break;
            }
            case SENSOR_TEMPERATURE: {
                headers = getResources().getStringArray(R.array.ambient_temperature_headers);
                descriptions = getResources().getStringArray(R.array.ambient_temperature_descriptions);
                images = getResources().getStringArray(R.array.ambient_temperature_images);
                break;
            }
            case SENSOR_PRESSURE: {
                headers = getResources().getStringArray(R.array.pressure_headers);
                descriptions = getResources().getStringArray(R.array.pressure_descriptions);
                images = getResources().getStringArray(R.array.pressure_images);
                break;
            }
            case SENSOR_RELATIVE_HUMIDITY: {
                headers = getResources().getStringArray(R.array.relative_humidity_headers);
                descriptions = getResources().getStringArray(R.array.relative_humidity_descriptions);
                images = getResources().getStringArray(R.array.relative_humidity_images);
                break;
            }
            case SENSOR_GYROSCOPE: {
                headers = getResources().getStringArray(R.array.gyroscope_headers);
                descriptions = getResources().getStringArray(R.array.gyroscope_descriptions);
                images = getResources().getStringArray(R.array.gyroscope_images);
                break;
            }
            case SENSOR_GRAVITY: {
                headers = getResources().getStringArray(R.array.gravity_headers);
                descriptions = getResources().getStringArray(R.array.gravity_descriptions);
                images = getResources().getStringArray(R.array.gravity_images);
                break;
            }
            case SENSOR_LINEAR_ACCELERATION: {
                headers = getResources().getStringArray(R.array.linear_acceleration_headers);
                descriptions = getResources().getStringArray(R.array.linear_acceleration_descriptions);
                images = getResources().getStringArray(R.array.linear_acceleration_images);
                break;
            }
            case SENSOR_ROTATION_VECTOR: {
                headers = getResources().getStringArray(R.array.rotation_vector_headers);
                descriptions = getResources().getStringArray(R.array.rotation_vector_descriptions);
                images = getResources().getStringArray(R.array.rotation_vector_images);
                break;
            }
            case SENSOR_MAGNETIC_FIELD: {
                headers = getResources().getStringArray(R.array.magnetic_field_headers);
                descriptions = getResources().getStringArray(R.array.magnetic_field_descriptions);
                images = getResources().getStringArray(R.array.magnetic_field_images);
                break;
            }
            case HEART_RATE_ECG: {
                headers = getResources().getStringArray(R.array.heart_rate_ecg_headers);
                descriptions = getResources().getStringArray(R.array.heart_rate_ecg_descriptions);
                images = getResources().getStringArray(R.array.heart_rate_ecg_images);
                break;
            }
            case SENSOR_STEP_DETECTOR: {
                headers = getResources().getStringArray(R.array.step_detector_headers);
                descriptions = getResources().getStringArray(R.array.step_detector_descriptions);
                images = getResources().getStringArray(R.array.step_detector_images);
                break;
            }
            case SENSOR_STEP_COUNTER: {
                headers = getResources().getStringArray(R.array.step_counter_headers);
                descriptions = getResources().getStringArray(R.array.step_counter_descriptions);
                images = getResources().getStringArray(R.array.step_counter_images);
                break;
            }
            case SENSOR_MOTION_DETECTOR: {
                headers = getResources().getStringArray(R.array.motion_detector_headers);
                descriptions = getResources().getStringArray(R.array.motion_detector_descriptions);
                images = getResources().getStringArray(R.array.motion_detector_images);
                break;
            }
            case SENSOR_STATIONARY_DETECTOR: {
                headers = getResources().getStringArray(R.array.stationary_detector_headers);
                descriptions = getResources().getStringArray(R.array.stationary_detector_descriptions);
                images = getResources().getStringArray(R.array.stationary_detector_images);
                break;
            }
            case GSM_UMTS: {
                headers = getResources().getStringArray(R.array.gsm_umts_headers);
                descriptions = getResources().getStringArray(R.array.gsm_umts_descriptions);
                images = getResources().getStringArray(R.array.gsm_umts_images);
                break;
            }
            case RADIO: {
                headers = getResources().getStringArray(R.array.radio_headers);
                descriptions = getResources().getStringArray(R.array.radio_descriptions);
                images = getResources().getStringArray(R.array.radio_images);
                break;
            }
            case CPU: {
                headers = getResources().getStringArray(R.array.cpu_headers);
                descriptions = getResources().getStringArray(R.array.cpu_descriptions);
                images = getResources().getStringArray(R.array.cpu_images);
                break;
            }
            case ANDROID_OS: {
                headers = getResources().getStringArray(R.array.android_os_headers);
                descriptions = getResources().getStringArray(R.array.android_os_descriptions);
                images = getResources().getStringArray(R.array.android_os_images);
                break;
            }
            case INFRARED: {
                headers = getResources().getStringArray(R.array.infrared_headers);
                descriptions = getResources().getStringArray(R.array.infrared_descriptions);
                images = getResources().getStringArray(R.array.infrared_images);
                break;
            }
            case NFC: {
                headers = getResources().getStringArray(R.array.nfc_headers);
                descriptions = getResources().getStringArray(R.array.nfc_descriptions);
                images = getResources().getStringArray(R.array.nfc_images);
                break;
            }
            case MICROPHONE: {
                headers = getResources().getStringArray(R.array.microphone_headers);
                descriptions = getResources().getStringArray(R.array.microphone_descriptions);
                images = getResources().getStringArray(R.array.microphone_images);
                break;
            }
            case USB_ACCESSORY: {
                headers = getResources().getStringArray(R.array.usb_accessory_headers);
                descriptions = getResources().getStringArray(R.array.usb_accessory_descriptions);
                images = getResources().getStringArray(R.array.usb_accessory_images);
                break;
            }
            case BAROMETER: {
                headers = getResources().getStringArray(R.array.barometer_headers);
                descriptions = getResources().getStringArray(R.array.barometer_descriptions);
                images = getResources().getStringArray(R.array.barometer_images);
                break;
            }
            case WIFI_DIRECT: {
                headers = getResources().getStringArray(R.array.wifi_direct_headers);
                descriptions = getResources().getStringArray(R.array.wifi_direct_descriptions);
                images = getResources().getStringArray(R.array.wifi_direct_images);
                break;
            }
            case SENSOR_HEART_RATE: {
                headers = getResources().getStringArray(R.array.heart_rate_ecg_headers);
                descriptions = getResources().getStringArray(R.array.heart_rate_ecg_descriptions);
                images = getResources().getStringArray(R.array.heart_rate_ecg_images);
                break;
            }
            case WEB_VIEW: {
                headers = getResources().getStringArray(R.array.web_view_headers);
                descriptions = getResources().getStringArray(R.array.web_view_descriptions);
                images = getResources().getStringArray(R.array.web_view_images);
                break;
            }
            case VR_MODE: {
                headers = getResources().getStringArray(R.array.vr_headers);
                descriptions = getResources().getStringArray(R.array.vr_descriptions);
                images = getResources().getStringArray(R.array.vr_images);
                break;
            }
            case MIDI: {
                headers = getResources().getStringArray(R.array.midi_headers);
                descriptions = getResources().getStringArray(R.array.midi_descriptions);
                images = getResources().getStringArray(R.array.midi_images);
                break;
            }
            case FAKE_TOUCH: {
                headers = getResources().getStringArray(R.array.fake_touch_headers);
                descriptions = getResources().getStringArray(R.array.fake_touch_descriptions);
                images = getResources().getStringArray(R.array.fake_touch_images);
                break;
            }
            case RAM: {
                headers = getResources().getStringArray(R.array.ram_headers);
                descriptions = getResources().getStringArray(R.array.ram_descriptions);
                images = getResources().getStringArray(R.array.ram_images);
                break;
            }
            case STORAGE: {
                headers = getResources().getStringArray(R.array.storage_headers);
                descriptions = getResources().getStringArray(R.array.storage_descriptions);
                images = getResources().getStringArray(R.array.storage_images);
                break;
            }
            case FACE_DETECT: {
                headers = getResources().getStringArray(R.array.face_detect_headers);
                descriptions = getResources().getStringArray(R.array.face_detect_descriptions);
                images = getResources().getStringArray(R.array.face_detect_images);
                break;
            }
            case BARCODE_READER: {
                headers = getResources().getStringArray(R.array.barcode_reader_headers);
                descriptions = getResources().getStringArray(R.array.barcode_reader_descriptions);
                images = getResources().getStringArray(R.array.barcode_reader_images);
                break;
            }
            case TEXT_SCAN: {
                headers = getResources().getStringArray(R.array.text_scan_headers);
                descriptions = getResources().getStringArray(R.array.text_scan_descriptions);
                images = getResources().getStringArray(R.array.text_scan_images);
                break;
            }
            case VIRTUAL_REALITY: {
                headers = getResources().getStringArray(R.array.vr_headers);
                descriptions = getResources().getStringArray(R.array.vr_descriptions);
                images = getResources().getStringArray(R.array.vr_images);
                break;
            }
            case LABEL_GENERATOR: {
                headers = getResources().getStringArray(R.array.label_generator_headers);
                descriptions = getResources().getStringArray(R.array.label_generator_descriptions);
                images = getResources().getStringArray(R.array.label_generator_images);
                break;
            }
        }

        for(int i=0; i<headers.length; i++) list.add(new LearnMoreItem(images[i], headers[i], descriptions[i]));
    }

    @Override
    public void fillAdapter() {
        LearnMoreAdapter adapter = new LearnMoreAdapter(list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    @Override
    public void initialize() {
        Bundle intent = getIntent().getExtras();
        if(intent != null) {
            intentData.setName(intent.getString(DATA_NAME));
            intentData.setDrawableId(intent.getInt(DRAWABLE_ID));
            intentData.setPresent(intent.getBoolean(IS_PRESENT));
            intentData.setType(intent.getInt(TYPE));

            if(intentData.getName() != null) {
                String temp = getString(R.string.more_about) + " " + intentData.getName().toUpperCase();
                more_about_sensor.setText(temp);
            }
        }

        getStrings();

        fillAdapter();
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
}
