package com.appbusters.robinkamboj.senseitall.ui.detail_activity.features.gps_location;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;

public interface GpsInterface {

    void performAfterLocationSuccess(LocationRequest mLocationRequest, LocationSettingsRequest.Builder builder);

}
