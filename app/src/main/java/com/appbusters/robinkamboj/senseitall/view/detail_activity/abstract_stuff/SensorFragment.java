package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.model.recycler.SensorDetail;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.common_adapters.BasicInformationAdapter;
import com.appbusters.robinkamboj.senseitall.view.learn_more_activity.LearnMoreActivity;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CLEAR_SKY_LUMINANCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DATA_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAWABLE_ID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.EARTH_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FULL_MOON_LUMINANCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_DYNAMIC_SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_PRESENT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_WAKE_UP_SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.JUPITER_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MAGNETIC_FIELD_EARTH_MAX;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MAGNETIC_FIELD_EARTH_MIN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MARS_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MAXIMUM_DELAY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MAXIMUM_RANGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MAX_SUNLIGHT_LUMINANCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MERCURY_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MINIMUM_DELAY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MOON_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NEPTUNE_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NO_MOON_LUMINANCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.OVERCAST_SKY_LUMINANCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PLUTO_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.POWER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REPORTING_MODE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RESOLUTION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SATURN_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHADE_LUMINANCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STANDARD_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.STANDARD_PRESSURE_ATMOSPHERE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SUNLIGHT_LUMINANCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SUNRISE_LUMINANCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SUN_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.URANUS_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VENDOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VENUS_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VERSION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.reverseDiagnosticsPointer;

public abstract class SensorFragment extends Fragment implements SensorInterface {

    public Sensor sensor;
    public List<SensorDetail> sensorDetails = new ArrayList<>();

    @BindView(R.id.info_recycler)
    public RecyclerView infoRecycler;

    @BindView(R.id.go_back)
    public TextView goBack;

    @BindView(R.id.go_to_test)
    public TextView goToTest;

    @BindView(R.id.about)
    public TextView about;

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initializeSensor();
        if(sensor == null) {
            Toast.makeText(getActivity(), "Failed to load sensor.", Toast.LENGTH_SHORT).show();
            if(getActivity() != null) getActivity().onBackPressed();
        }
        else {
            showSensorDetails();
        }

        hideGoToTestIfNoTest();

        setupAbout();
    }

    @Override
    public void showSensorDetails() {
        initializeBasicInformation();
        showBasicInformation();
    }

    @Override
    public void showBasicInformation() {
        GenericData intentData = null;
        if(getActivity() != null)
            intentData = ((DetailActivity) getActivity()).intentData;
        infoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(intentData != null)
            infoRecycler.setAdapter(new BasicInformationAdapter(getActivity(), sensorDetails, intentData.getName()));
        else
            infoRecycler.setAdapter(new BasicInformationAdapter(getActivity(), sensorDetails));
    }

    @Override
    public void hideGoToTestIfNoTest() {
        if(getActivity() != null)
            if(AppConstants.diagnosticsPointer.get(((DetailActivity) getActivity()).recyclerName) == null
                    && reverseDiagnosticsPointer.get((((DetailActivity) getActivity()).intentData.getName())) == null)
                goToTest.setVisibility(View.GONE);
    }

    @Override
    public void addToDetailsList(List<SensorDetail> sensorDetails, String key, String value) {

        String valueUnits = null;

        switch (key) {
            case STANDARD_PRESSURE_ATMOSPHERE: {
                valueUnits = "hPa (milli-bar)";
                break;
            }
            case MAGNETIC_FIELD_EARTH_MAX:
            case MAGNETIC_FIELD_EARTH_MIN: {
                valueUnits = "micro-teslas";
                break;
            }
            case CLEAR_SKY_LUMINANCE:
            case FULL_MOON_LUMINANCE:
            case NO_MOON_LUMINANCE:
            case MAX_SUNLIGHT_LUMINANCE:
            case SUNRISE_LUMINANCE:
            case SUNLIGHT_LUMINANCE:
            case SHADE_LUMINANCE:
            case OVERCAST_SKY_LUMINANCE: {
                valueUnits = "lux";
                break;
            }
            case SUN_GRAVITY:
            case MOON_GRAVITY:
            case MERCURY_GRAVITY:
            case VENUS_GRAVITY:
            case EARTH_GRAVITY:
            case MARS_GRAVITY:
            case JUPITER_GRAVITY:
            case SATURN_GRAVITY:
            case URANUS_GRAVITY:
            case NEPTUNE_GRAVITY:
            case PLUTO_GRAVITY:
            case STANDARD_GRAVITY:{
                valueUnits = "m/s<sup>2</sup>";
                break;
            }
            case VENDOR:{
                valueUnits = "<font color='#8B95A3'><small>(vendor of this sensor)</small></font>";
                break;
            }
            case RESOLUTION:{
                valueUnits = "<font color='#8B95A3'><small>(resolution in sensor's units)</small></font>";
                break;
            }
            case MINIMUM_DELAY:{
                valueUnits = "microseconds <font color='#8B95A3'><small>(minimum delay allowed between two events)</small></font>";
                break;
            }
            case MAXIMUM_DELAY:{
                valueUnits = "microseconds <font color='#8B95A3'><small>(maximum delay allowed between two events)</small></font>";
                break;
            }
            case VERSION:{
                valueUnits = "mA";
                break;
            }
            case POWER:{
                valueUnits = "<font color='#8B95A3'><small>(maximum range of this sensor in sensor's units)</small></font>";
                break;
            }
            case MAXIMUM_RANGE:{
                valueUnits = "<font color='#8B95A3'><small>(version of the sensor's module)</small></font>";
                break;
            }
            case IS_WAKE_UP_SENSOR:{
                valueUnits = "<font color='#8B95A3'><small>(sensor is suspended when screen turns off)</small></font>";
                break;
            }
            case REPORTING_MODE:{
                valueUnits = "<font color='#8B95A3'><small>(reporting mode for the input sensor)</small></font>";
                break;
            }
            case IS_DYNAMIC_SENSOR:{
                valueUnits = "<font color='#8B95A3'><small>(can the sensor be created at runtime)</small></font>";
                break;
            }
        }

        if(valueUnits == null) sensorDetails.add(new SensorDetail(key, value));
        else sensorDetails.add(new SensorDetail(key, value + " " + valueUnits));
    }

    @Override
    public void setupAbout() {
        if (getActivity() != null) {
            String[] temp = getActivity().getResources().getStringArray(
                    AppConstants.sensorMapAbout.get(((DetailActivity) getActivity()).intentData.getName())
            );
            about.setText(temp[0]);
        }
    }

    @OnClick(R.id.go_back)
    public void setGoBack() {
        if(getActivity() != null) getActivity().onBackPressed();
    }

    @OnClick(R.id.go_to_test)
    public void setGoToTest() {
        if(getActivity() != null) {
            Intent intent = new Intent(getActivity(), TestActivity.class);
            GenericData intentData = ((DetailActivity) getActivity()).intentData;
            String recyclerName = ((DetailActivity) getActivity()).recyclerName;
            Bundle args = new Bundle();

            args.putString(AppConstants.DATA_NAME, intentData.getName());
            args.putString(AppConstants.RECYCLER_NAME, recyclerName);
            args.putInt(AppConstants.DRAWABLE_ID, intentData.getDrawableId());
            args.putBoolean(AppConstants.IS_PRESENT, intentData.isPresent());
            args.putInt(AppConstants.TYPE, intentData.getType());

            intent.putExtras(args);

            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity);
        }
    }

    @OnClick(R.id.learn_more)
    public void setLearnMore() {
        if(getActivity() != null) {
            GenericData intentData = ((DetailActivity) getActivity()).intentData;
            Intent intent = new Intent(getActivity(), LearnMoreActivity.class);

            Bundle args = new Bundle();
            args.putString(DATA_NAME, intentData.getName());
            args.putInt(DRAWABLE_ID, intentData.getDrawableId());
            args.putBoolean(IS_PRESENT, intentData.isPresent());
            args.putInt(TYPE, intentData.getType());
            intent.putExtras(args);

            getActivity().startActivity(intent);
        }
    }
}
