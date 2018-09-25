package com.appbusters.robinkamboj.senseitall.view.detail_activity.features.gps_location;

import android.annotation.SuppressLint;
import android.content.IntentSender;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.feature_and_sensor.FeatureFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REQUEST_CHECK_SETTINGS;

/**
 * A simple {@link Fragment} subclass.
 */
public class GpsFragment extends FeatureFragment
        implements GpsInterface,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private GoogleApiClient googleApiClient;
    private FusedLocationProviderClient fusedLocationProviderClient;
    public LocationRequest mLocationRequest;
    public LocationSettingsRequest.Builder builder;

    public GpsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
        View v = inflater.inflate(R.layout.fragment_gps, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);
        initializeSensor();

        hideGoToTestIfNoTest();

        setupAbout();
    }

    @Override
    public void initializeSensor() {
        if (getActivity() != null)
            googleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();

        if (getActivity() != null) {

            mapFragment = new SupportMapFragment();

            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.map_container, mapFragment).commit();

            mapFragment.getMapAsync(this);

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        }

        if (fusedLocationProviderClient != null)
            checkLocationAndAddToMap();
    }

    @Override
    public void initializeBasicInformation() {
        //not required here
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        checkLocationAndAddToMap();
    }

    @SuppressWarnings("FieldCanBeLocal")
    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    @SuppressWarnings("FieldCanBeLocal")
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    private void checkLocationAndAddToMap() {
        //Fetching the last known location using the Fus

        // Create the location request to start receiving updates
        mLocationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest)
                .setAlwaysShow(true); //this is the key ingredient

        if(getActivity() == null) return;

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(getActivity()).checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    // All location settings are satisfied. The client can initialize location
                    // requests here.
                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            try {
                                // Cast to a resolvable exception.
                                ResolvableApiException resolvable = (ResolvableApiException) exception;
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                resolvable.startResolutionForResult(
                                        getActivity(),
                                        REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException | ClassCastException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                isLocationServicesGranted = true;
                performAfterLocationSuccess(mLocationRequest, builder);
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void performAfterLocationSuccess(LocationRequest mLocationRequest, LocationSettingsRequest.Builder builder) {

        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        if (getActivity() == null) return;

        SettingsClient settingsClient = LocationServices.getSettingsClient(getActivity());
        settingsClient.checkLocationSettings(locationSettingsRequest);

        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    if (mMap != null) {
                        sensorDetails = new ArrayList<>();
                        addToDetailsList(sensorDetails, "Latitude", String.valueOf(location.getLatitude()));
                        addToDetailsList(sensorDetails, "Longitude", String.valueOf(location.getLongitude()));
                        addToDetailsList(sensorDetails, "Provider", location.getProvider());
                        addToDetailsList(sensorDetails, "Accuracy", String.valueOf(location.getAccuracy()));
                        addToDetailsList(sensorDetails, "Altitude", String.valueOf(location.getAltitude()));
                        addToDetailsList(sensorDetails, "Bearing", String.valueOf(location.getBearing()));
                        addToDetailsList(sensorDetails, "Elapsed Realtime Nanos", String.valueOf(location.getElapsedRealtimeNanos()));
                        addToDetailsList(sensorDetails, "Speed", String.valueOf(location.getSpeed()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            addToDetailsList(sensorDetails, "Vertical Accuracy Meters", String.valueOf(location.getVerticalAccuracyMeters()));
                            addToDetailsList(sensorDetails, "Bearing Accuracy Degrees", String.valueOf(location.getBearingAccuracyDegrees()));
                            addToDetailsList(sensorDetails, "Speed Accuracy meters/second", String.valueOf(location.getSpeedAccuracyMetersPerSecond()));
                        }
                        showBasicInformation();
                        fusedLocationProviderClient.removeLocationUpdates(this);

                    }
                } else
                    Log.e("tag", "location null");
            }
        }, Looper.myLooper());
    }
}
