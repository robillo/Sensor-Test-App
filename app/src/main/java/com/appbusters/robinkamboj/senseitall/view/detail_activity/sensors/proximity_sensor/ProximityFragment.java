package com.appbusters.robinkamboj.senseitall.view.detail_activity.sensors.proximity_sensor;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.model.recycler.SensorDetail;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.common_adapters.BasicInformationAdapter;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProximityFragment extends Fragment implements ProximityInterface {

    private Sensor sensor;
    private List<SensorDetail> sensorDetails = new ArrayList<>();

    @BindView(R.id.info_recycler)
    RecyclerView infoRecycler;

    @BindView(R.id.go_back)
    TextView goBack;

    public ProximityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_proximity, container, false);
        setup(v);
        return v;
    }

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
    }

    @Override
    public void initializeSensor() {
        SensorManager sensorManager = null;

        if(getActivity() != null)
            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager != null)
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public void showSensorDetails() {
        initializeBasicInformation();
        showBasicInformation();
    }

    @Override
    public void initializeBasicInformation() {
        addToDetailsList(sensorDetails, "Vendor", sensor.getVendor());
        addToDetailsList(sensorDetails, "Resolution", String.valueOf(sensor.getResolution()));
        addToDetailsList(sensorDetails, "Minimum Delay", String.valueOf(sensor.getMinDelay()));
        addToDetailsList(sensorDetails, "Maximum Delay", String.valueOf(sensor.getMaxDelay()));
        addToDetailsList(sensorDetails, "Power", String.valueOf(sensor.getPower()));
        addToDetailsList(sensorDetails, "Maximum Range", String.valueOf(sensor.getMaximumRange()));
        addToDetailsList(sensorDetails, "Version", String.valueOf(sensor.getVersion()));
        addToDetailsList(sensorDetails, "Is Wake Up Sensor", String.valueOf(sensor.isWakeUpSensor()));
        addToDetailsList(sensorDetails, "Reporting Mode", String.valueOf(sensor.getReportingMode()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            addToDetailsList(sensorDetails, "Is Dynamic Sensor", String.valueOf(sensor.isDynamicSensor()));
    }

    @Override
    public void showBasicInformation() {
        infoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        infoRecycler.setAdapter(new BasicInformationAdapter(getActivity(), sensorDetails));
    }

    @Override
    public void addToDetailsList(List<SensorDetail> sensorDetails, String key, String value) {
        sensorDetails.add(new SensorDetail(key, value));
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
            //start learn more activity
        }
    }
}
