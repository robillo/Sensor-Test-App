package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.gps_test_fragment;


import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GpsTestFragment extends Fragment implements
        GpsTestInterface,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private FusedLocationProviderClient fusedLocationProviderClient;

    SupportMapFragment mapFragment;

    public GpsTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_gps_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initialize();
    }

    @Override
    public void initialize() {
        if(getActivity() != null)
            googleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();

        if(getActivity() != null) {

            mapFragment = new SupportMapFragment();

            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, mapFragment, "TAG")
                    .commit();

            mapFragment.getMapAsync(this);

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        }

        if(fusedLocationProviderClient != null)
            checkLocationAndAddToMap();
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

    @SuppressLint("MissingPermission")
    private void checkLocationAndAddToMap() {

        Log.e("tag", "check and add to map");

        //Fetching the last known location using the Fus

        // Create the location request to start receiving updates
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        if(getActivity() == null) return;

        SettingsClient settingsClient = LocationServices.getSettingsClient(getActivity());
        settingsClient.checkLocationSettings(locationSettingsRequest);

        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location location = locationResult.getLastLocation();
                if(location != null) {
                    if(mMap != null) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.addMarker(
                                new MarkerOptions().position(latLng).title(
                                        "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude()
                                )
                        );
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                        fusedLocationProviderClient.removeLocationUpdates(this);

                    }
                }
                else
                    Log.e("tag", "location null");
            }
        }, Looper.myLooper());
    }
}
