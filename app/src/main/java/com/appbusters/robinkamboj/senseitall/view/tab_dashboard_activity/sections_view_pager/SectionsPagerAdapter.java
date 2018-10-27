package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections_view_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.generic_fragment.GenericFragment;

import java.util.List;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID_HEADER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DEVICE_HEADER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FEATURE_HEADER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_HEADER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TOOLS_HEADER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TRENDING_HEADER;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<String> headersList;

    public SectionsPagerAdapter(FragmentManager fm, List<String> headersList) {
        super(fm);
        this.headersList = headersList;
    }

    @Override
    public Fragment getItem(int position) {
        switch (headersList.get(position)) {
            case SENSOR_HEADER: {
                return new GenericFragment();
            }
            case FEATURE_HEADER: {
                return new GenericFragment();
            }
            case TOOLS_HEADER: {
                return new GenericFragment();
            }
            case TRENDING_HEADER: {
                return new GenericFragment();
            }
            case DEVICE_HEADER: {
                return new GenericFragment();
            }
            case ANDROID_HEADER: {
                return new GenericFragment();
            }
            default: {
                return new GenericFragment();
            }
        }
    }

    @Override
    public int getCount() {
        return headersList.size();
    }
}
