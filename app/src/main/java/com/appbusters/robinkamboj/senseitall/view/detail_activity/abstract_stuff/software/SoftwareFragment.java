package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.software;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

abstract public class SoftwareFragment extends Fragment implements SoftwareInterface {

    @BindView(R.id.go_back)
    public TextView goBack;

    @BindView(R.id.go_to_test)
    public TextView goToTest;

    @BindView(R.id.about)
    public TextView about;

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        hideGoToTestIfNoTest();

        setupAbout();
    }

    @Override
    public void hideGoToTestIfNoTest() {
        if(getActivity() != null && AppConstants.isTestMap.get(((DetailActivity) getActivity()).recyclerName) == null) {
            goToTest.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupAbout() {
        if (getActivity() != null) {
            String[] temp = getActivity().getResources().getStringArray(
                    AppConstants.mapAbout.get(((DetailActivity) getActivity()).intentData.getName())
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
}
