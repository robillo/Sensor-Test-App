package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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
}
