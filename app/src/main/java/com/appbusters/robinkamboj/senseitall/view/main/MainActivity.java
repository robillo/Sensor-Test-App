package com.appbusters.robinkamboj.senseitall.view.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.content.ContextCompat;
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

import java.util.ArrayList;
import java.util.List;

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

public class MainActivity extends AppCompatActivity implements MainInterface {

    private AppPreferencesHelper helper;
    private List<GenericData> list;

    private List<String> sensorNames;
    private List<String> featureNames;
    private List<String> diagnosticsNames;

    private boolean[] sensorsPresent;
    private boolean[] featuresPresent;
    private boolean[] diagnosticsPresent;

    @BindView(R.id.card_permissions)
    CardView permissionsCard;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

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

        checkIfAllPermissionsGiven();
        inflateData();
        changeStatusBarColor();
        setHeaderTextAndRv();
    }

    @Override
    public void inflateData() {
        sensorsPresent = getIntent().getBooleanArrayExtra(PRESENT_SENSORS);
        featuresPresent = getIntent().getBooleanArrayExtra(PRESENT_FEATURES);
        diagnosticsPresent = getIntent().getBooleanArrayExtra(PRESENT_DIAGNOSTICS);

        sensorNames = AppConstants.sensorNames;
        featureNames = AppConstants.featureNames;
        diagnosticsNames = AppConstants.diagnosticsNames;
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
        if(helper.getHeaderText().equals(SHOWING_DEVICE_TESTS)) return;
        helper.setHeaderText(SHOWING_DEVICE_TESTS);
        setHeaderTextAndRv();
    }

    @OnClick(R.id.button_sensors_list)
    public void setShowSensors() {
        if(helper.getHeaderText().equals(SHOWING_SENSORS_LIST)) return;
        helper.setHeaderText(SHOWING_SENSORS_LIST);
        setHeaderTextAndRv();
    }

    @OnClick(R.id.button_features_list)
    public void setShowFeatures() {
        if(helper.getHeaderText().equals(SHOWING_FEATURES_LIST)) return;
        helper.setHeaderText(SHOWING_FEATURES_LIST);
        setHeaderTextAndRv();
    }

    @OnClick(R.id.button_rate_experience)
    public void setRateExperience() {
        if(helper.getHeaderText().equals(RATE_YOUR_EXPERIENCE)) return;
        helper.setHeaderText(RATE_YOUR_EXPERIENCE);
        setHeaderTextAndRv();
    }

    @Override
    public void setHeaderTextAndRv() {
        String string = helper.getHeaderText();

        headerText.setText(string);
        switch (string) {
            case SHOWING_DEVICE_TESTS: {
                turnOnHighlight(TYPE_DIAGNOSTICS);
                fillGenericDataForSelected(TYPE_DIAGNOSTICS);
                break;
            }
            case SHOWING_SENSORS_LIST: {
                turnOnHighlight(TYPE_SENSORS);
                fillGenericDataForSelected(TYPE_SENSORS);
                break;
            }
            case SHOWING_FEATURES_LIST: {
                turnOnHighlight(TYPE_FEATURES);
                fillGenericDataForSelected(TYPE_FEATURES);
                break;
            }
            case RATE_YOUR_EXPERIENCE: {
                turnOnHighlight(TYPE_RATE);
                fillGenericDataForSelected(TYPE_RATE);
                break;
            }
        }

        initializeAdapter();
    }

    @Override
    public void turnOnHighlight(int type) {
        switch (type) {
            case TYPE_DIAGNOSTICS: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case TYPE_SENSORS: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case TYPE_FEATURES: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case TYPE_RATE: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                break;
            }
        }
    }

    @Override
    public void checkIfAllPermissionsGiven() {
        List<String> permissionNames = AppConstants.dangerousPermissions;
        List<PermissionsItem> permissionsItems = new ArrayList<>();
        int rejectedCount = 0;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        for(String p : permissionNames) {
            boolean b = checkSelfPermission(p) == PERMISSION_GRANTED;
            permissionsItems.add(new PermissionsItem(p, b));
            if(!b) rejectedCount++;
        }

        if(rejectedCount == 0)
            permissionsCard.setVisibility(View.GONE);
        else
            permissionsCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void initializeAdapter() {
        int span;
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT: {
                span = 3;
                break;
            }
            case Configuration.ORIENTATION_LANDSCAPE: {
                span = 5;
                break;
            }
            default: {
                span = 3;
            }
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), span);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new GenericDataAdapter(list, this));
    }

    @Override
    public void fillGenericDataForSelected(int type) {
        list = new ArrayList<>();

        //first row null and invisible for title bar space
//        list.add(null);
//        list.add(null);
//        list.add(null);

        List<String> dataNames = new ArrayList<>();
        boolean[] dataPresent = new boolean[dataNames.size()];
        switch (type) {
            case TYPE_DIAGNOSTICS: {
                dataNames = diagnosticsNames;
                dataPresent = diagnosticsPresent;
                break;
            }
            case TYPE_SENSORS: {
                dataNames = sensorNames;
                dataPresent = sensorsPresent;
                break;
            }
            case TYPE_FEATURES: {
                dataNames = featureNames;
                dataPresent = featuresPresent;
                break;
            }
            case TYPE_RATE: {

                break;
            }
        }

        for(int i=0; i<dataNames.size(); i++)
            if(type == TYPE_DIAGNOSTICS)
                list.add(new GenericData(
                        dataNames.get(i),
                        imageUrlMap.get(diagnosticsPointer.get(dataNames.get(i))),
                        dataPresent[i],
                        type));
            else
                list.add(new GenericData(
                        dataNames.get(i),
                        imageUrlMap.get(dataNames.get(i)),
                        dataPresent[i],
                        type));

//        if(dataNames.size()%3 != 0) {
//            list.add(null);
//            list.add(null);
//            list.add(null);
//        }
    }

    @OnClick(R.id.card_permissions)
    public void askPermissions() {

    }
}
