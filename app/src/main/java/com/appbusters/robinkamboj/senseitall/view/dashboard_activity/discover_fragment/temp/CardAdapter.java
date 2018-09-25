package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp;

import android.support.v7.widget.CardView;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}