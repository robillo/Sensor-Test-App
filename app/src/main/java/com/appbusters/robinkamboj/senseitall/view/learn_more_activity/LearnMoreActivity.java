package com.appbusters.robinkamboj.senseitall.view.learn_more_activity;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shuhart.stepview.StepView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LIGHT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;

public class LearnMoreActivity extends AppCompatActivity  implements LearnMoreInterface {

    public GenericData intentData = new GenericData();
    private String[] headers, descriptions, images;

    @BindView(R.id.more_about_sensor)
    TextView more_about_sensor;

    @BindView(R.id.step_view)
    StepView stepView;

    @BindView(R.id.previous)
    TextView previous;

    @BindView(R.id.next)
    TextView next;

    @BindView(R.id.text_image)
    ImageView textImage;

    @BindView(R.id.header_text)
    TextView headerText;

    @BindView(R.id.description_text)
    TextView descriptionText;

    private int currentStep = 0;
    private int maxStep = 3;

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

        setTexts();
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
    public void manageVisibility() {
        if(currentStep == 0) {
            if(previous.getVisibility() == View.VISIBLE) {
                previous.setVisibility(View.INVISIBLE);
                previous.setClickable(false);
            }
            if(next.getVisibility() == View.INVISIBLE) {
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);
            }
        }
        else if(currentStep == maxStep) {
            if(previous.getVisibility() == View.INVISIBLE) {
                previous.setVisibility(View.VISIBLE);
                previous.setClickable(true);
            }
            if(next.getVisibility() == View.VISIBLE) {
                next.setVisibility(View.INVISIBLE);
                next.setClickable(false);
            }
        }
        else {
            if(previous.getVisibility() == View.INVISIBLE) {
                previous.setVisibility(View.VISIBLE);
                previous.setClickable(true);
            }
            if(next.getVisibility() == View.INVISIBLE) {
                next.setVisibility(View.VISIBLE);
                next.setClickable(true);
            }
        }
    }

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
        }

        if(headers != null) maxStep = headers.length - 1;
        else maxStep = 1;
    }

    @Override
    public void setTexts() {
        headerText.setText(headers[currentStep].toUpperCase());
        descriptionText.setText(descriptions[currentStep]);
        Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(images[currentStep])
                .into(textImage);
    }

    @Override
    public void initialize() {
        Bundle intent = getIntent().getExtras();
        if(intent != null) {
            intentData.setName(intent.getString(DATA_NAME));
            intentData.setDrawableId(intent.getInt(DRAWABLE_ID));
            intentData.setPresent(intent.getBoolean(IS_PRESENT));
            intentData.setType(intent.getInt(TYPE));
        }
        if(intentData.getName() != null) {
            String temp = getString(R.string.more_about) + " " + intentData.getName().toUpperCase();
            more_about_sensor.setText(temp);
        }

        getStrings();

        manageVisibility();

        if(headers != null) stepView.setStepsNumber(headers.length);
        else stepView.setStepsNumber(1);
    }

    @OnClick(R.id.previous)
    public void setPrevious() {
        currentStep = currentStep - 1;
        stepView.go(currentStep, true);
        manageVisibility();
        setTexts();
    }

    @OnClick(R.id.next)
    public void setNext() {
        currentStep = currentStep + 1;
        stepView.go(currentStep, true);
        manageVisibility();
        setTexts();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
