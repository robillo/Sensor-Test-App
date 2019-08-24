package com.appbusters.robinkamboj.senseitall.ui.test_activity.graph_fragment_abstract;

import android.view.View;

public interface GraphInterface {

    void setup(View v);

    void initialize();

    String returnUnits(String sensor);
}
