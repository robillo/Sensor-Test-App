package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import butterknife.OnClick;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DATA_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAWABLE_ID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFO_RECYCLER_COUNT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_PRESENT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;

public abstract class FeatureFragment extends Fragment implements SensorInterface  {

    public List<SensorDetail> sensorDetails = new ArrayList<>();

    @BindView(R.id.view_more)
    TextView viewMoreStatistics;

    @BindView(R.id.info_recycler)
    public RecyclerView infoRecycler;

    @BindView(R.id.go_back)
    public TextView goBack;

    @BindView(R.id.go_to_test)
    public TextView goToTest;

    @BindView(R.id.about)
    public TextView about;

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

        List<SensorDetail> subSensorDetails = new ArrayList<>();
        for(int i=0; i<INFO_RECYCLER_COUNT && i<sensorDetails.size(); i++) {
            subSensorDetails.add(sensorDetails.get(i));
        }

        if(intentData != null)
            infoRecycler.setAdapter(new BasicInformationAdapter(getActivity(), subSensorDetails, intentData.getName()));
        else
            infoRecycler.setAdapter(new BasicInformationAdapter(getActivity(), subSensorDetails));

        if(sensorDetails.size() > INFO_RECYCLER_COUNT) {
            viewMoreStatistics.setVisibility(View.VISIBLE);
        }
        else {
            viewMoreStatistics.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideGoToTestIfNoTest() {
        if(getActivity() != null && AppConstants.isTestMap.get(((DetailActivity) getActivity()).recyclerName) == null) {
            goToTest.setVisibility(View.GONE);
        }
    }

    @Override
    public void addToDetailsList(List<SensorDetail> sensorDetails, String key, String value) {
        sensorDetails.add(new SensorDetail(key, value));
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
            getActivity().overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity);
        }
    }
}
