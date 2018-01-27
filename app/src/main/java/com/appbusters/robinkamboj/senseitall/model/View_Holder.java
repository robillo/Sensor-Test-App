package com.appbusters.robinkamboj.senseitall.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.ItemClickListener;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.accelerometer.AccelerometerActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.audio_video.AudioVideoActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.battery.BatteryActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.bluetooth.BluetoothActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.compass.CompassActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.gravity.GravityActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.gyro.GyroscopeActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.heart_beat.HeartBeatActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.humidity.HumidityActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.linear_acceleration.LinearAccelerationActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.magnetic.MagneticActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.maps.MapsActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.motion_detect.MotionDetectActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.multi_touch.MultiTouchActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.nfc.NFCActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.rotation.RotationActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.stationary_detect.StationaryDetectActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.wifi.WiFiActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.os.AndroidOSActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.cpu.CPUActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.camera.CameraActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.camera_secondary.CameraSecondaryActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.fingerprint.FingerprintActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.flash.FlashActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.gsm.GSMActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.light.LightActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.pressure.PressureActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.proximity.ProximityActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.radio.RadioActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.screen.ScreenActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.sound.SoundActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.step_counter.StepCounterActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.step_detector.StepDetectorActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.temperature.TemperatureActivity;
import com.appbusters.robinkamboj.senseitall.view.activities.sensors.vibrator.VibratorActivity;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;

public class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{


    public TextView sensor_name;
    public ImageView sensor_imageview;
    private ImageView imageview_disabled;
    private CardView cardView;
    private ItemClickListener clickListener;
    private Context context;
    private double Lat=28;
    private double Lon=77;


    public View_Holder(View itemView) {
        super(itemView);

        sensor_name = itemView.findViewById(R.id.textView);
        sensor_imageview = itemView.findViewById(R.id.imageView);
        cardView = itemView.findViewById(R.id.cardView);
        imageview_disabled = itemView.findViewById(R.id.imageview_disabled);

        context = itemView.getContext();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View view) {
        clickListener.onCLick(itemView, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        clickListener.onCLick(itemView, getAdapterPosition(), true);
        return false;
    }

    public void intent(String sensorName, int position){
        switch (sensorName) {
            case "Main Camera": {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);
                    return;
                }
                Intent i = new Intent(context, CameraActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Secondary Camera": {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);
                    return;
                }
                Intent i = new Intent(context, CameraSecondaryActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "GPS": {
                LocationManager locMan = (LocationManager) context.getSystemService(LOCATION_SERVICE);
                LocationListener locLis = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.d(TAG, "onLocationChanged: lat:" + location.getLatitude());
                        Log.d(TAG, "onLocationChanged: long:" + location.getLongitude());
                        Log.d(TAG, "onLocationChanged: alt:" + location.getAltitude());
                        Log.d(TAG, "onLocationChanged: bearing:" + location.getBearing()); // angle from North in clockwise
                        Log.d(TAG, "onLocationChanged: speed:" + location.getSpeed());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            Log.d(TAG, "onLocationChanged: " + location.getElapsedRealtimeNanos());
                        }
                        Lat = location.getLatitude();
                        Lon = location.getLongitude();

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, 121);
                    return;
                } else {
                    if (locMan != null) {
                        locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, locLis);
                        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locLis);
                    }
                }
                Intent i = new Intent(context, MapsActivity.class);
                i.putExtra("lat", Lat);
                i.putExtra("lon", Lon);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "WiFi": {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 4);
                    return;
                }
                else {
                    context.startActivity(new Intent(context, WiFiActivity.class));
                }
                break;
            }
            case "Bluetooth": {
                context.startActivity(new Intent(context, BluetoothActivity.class));
                break;
            }
            case "GSM/UMTS": {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION}, 5);
                    return;
                }
                context.startActivity(new Intent(context, GSMActivity.class));
                break;
            }
            case "Accelerometer": {
                Intent i = new Intent(context, AccelerometerActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Compass": {
                Intent i = new Intent(context, CompassActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Radio": {
                Intent i = new Intent(context, RadioActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Screen": {
                Intent i = new Intent(context, ScreenActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Battery": {
                Intent i = new Intent(context, BatteryActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "CPU": {
                Intent i = new Intent(context, CPUActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Sound": {
                Intent i = new Intent(context, SoundActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Vibrator": {
                Intent i = new Intent(context, VibratorActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Audio/Video outputs": {
                Intent i = new Intent(context, AudioVideoActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Android OS": {
                Intent i = new Intent(context, AndroidOSActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Light Sensor": {
                Intent i = new Intent(context, LightActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Proximity Sensor": {
                Intent i = new Intent(context, ProximityActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Temperature Sensor": {
                Intent i = new Intent(context, TemperatureActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Pressure Sensor": {
                Intent i = new Intent(context, PressureActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Relative Humidity": {
                Intent i = new Intent(context, HumidityActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Flash": {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);
                    return;
                }
                Intent i = new Intent(context, FlashActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Gyroscope": {
                Intent i = new Intent(context, GyroscopeActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Gravity": {
                Intent i = new Intent(context, GravityActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);

                break;
            }
            case "Linear Acceleration": {
                Intent i = new Intent(context, LinearAccelerationActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Rotation Vector": {
                Intent i = new Intent(context, RotationActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Step Detector": {
                Intent i = new Intent(context, StepDetectorActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Step Counter": {
                Intent i = new Intent(context, StepCounterActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Motion Detector": {
                Intent i = new Intent(context, MotionDetectActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Stationary Detector": {
                Intent i = new Intent(context, StationaryDetectActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Multi Touch": {
                Intent i = new Intent(context, MultiTouchActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Heart Rate": {

                Intent i = new Intent(context, HeartBeatActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);

                break;
            }
            case "Fingerprint": {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.USE_FINGERPRINT}, 2);
                    }
                    else {
                        Toast.makeText(context, "FINGERPRINT SENSOR NOT PRESENT", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                Intent i = new Intent(context, FingerprintActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);

                break;
            }
            case "NFC": {
                Intent i = new Intent(context, NFCActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case "Magnetic Field Sensor": {
                Intent i = new Intent(context, MagneticActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
        }
    }
}
