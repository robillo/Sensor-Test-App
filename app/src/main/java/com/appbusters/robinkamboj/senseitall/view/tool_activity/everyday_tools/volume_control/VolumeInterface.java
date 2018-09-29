package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.volume_control;

import android.view.View;

public interface VolumeInterface {

    void setup();

    int getPercentVolume(int type);

    void setInitialVolumes();

    void setCrollerListeners();

    void setVolumeFromCroller(int type, int progress);

}
