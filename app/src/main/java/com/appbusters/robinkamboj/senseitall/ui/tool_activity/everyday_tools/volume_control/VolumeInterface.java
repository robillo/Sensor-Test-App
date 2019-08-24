package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.volume_control;

public interface VolumeInterface {

    void setup();

    int getPercentVolume(int type);

    void setInitialVolumes();

    void setCrollerListeners();

    void setVolumeFromCroller(int type, int progress);

}
