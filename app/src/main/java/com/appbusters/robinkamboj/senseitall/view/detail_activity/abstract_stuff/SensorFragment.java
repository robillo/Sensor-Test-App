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
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        if (getActivity() != null)
            about.setText(AppConstants.sensorMapAbout.get(((DetailActivity) getActivity()).intentData.getName()));
    }

    @Override
    public void showSensorDetails() {
        initializeBasicInformation();
        showBasicInformation();
    }

    @Override
    public void showBasicInformation() {
        infoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        infoRecycler.setAdapter(new BasicInformationAdapter(getActivity(), sensorDetails));
    }

    @Override
    public void hideGoToTestIfNoTest() {
        if(getActivity() != null)
            if(AppConstants.diagnosticsPointer.get(((DetailActivity) getActivity()).recyclerName) == null)
                goToTest.setVisibility(View.GONE);
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
