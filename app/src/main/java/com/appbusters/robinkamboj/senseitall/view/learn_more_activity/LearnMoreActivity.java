package com.appbusters.robinkamboj.senseitall.view.learn_more_activity;

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

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DATA_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAWABLE_ID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_PRESENT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RECYCLER_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;

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
    @SuppressWarnings("FieldCanBeLocal")
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
        headers = getResources().getStringArray(R.array.dummy_headers);
        descriptions = getResources().getStringArray(R.array.dummy_descriptions);
        images = getResources().getStringArray(R.array.dummy_images);
    }

    @Override
    public void setTexts() {
        headerText.setText(headers[currentStep]);
        descriptionText.setText(descriptions[currentStep]);
        Glide.with(this).applyDefaultRequestOptions(RequestOptions.centerCropTransform()).load(images[currentStep]).into(textImage);
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

        manageVisibility();

        getStrings();

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
}
