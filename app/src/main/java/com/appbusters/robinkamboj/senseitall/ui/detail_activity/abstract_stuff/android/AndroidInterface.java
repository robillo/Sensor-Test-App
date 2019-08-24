package com.appbusters.robinkamboj.senseitall.ui.detail_activity.abstract_stuff.android;

import android.content.Context;
import android.view.View;

public interface AndroidInterface {

    void setup(View v);

    void initialize();

    void loadUri();

    boolean isConnected(Context context);

    void setupView(View v);

    void initializeData();

    void setAdapter();

}
