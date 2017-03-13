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

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.ItemClickListener;
import com.appbusters.robinkamboj.senseitall.view.robin.AndroidOSActivity;
import com.appbusters.robinkamboj.senseitall.view.rishabh.BatteryActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.CPUActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.CameraActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.CameraSecondaryActivity;
import com.appbusters.robinkamboj.senseitall.view.rishabh.CompassActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.ECGActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.FingerprintActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.FlashActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.GSMActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.LightActivity;
import com.appbusters.robinkamboj.senseitall.view.rishabh.MagneticActivity;
import com.appbusters.robinkamboj.senseitall.view.rishabh.MapsActivity;
import com.appbusters.robinkamboj.senseitall.view.rishabh.MultiTouchActivity;
import com.appbusters.robinkamboj.senseitall.view.rishabh.NFCActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.PressureActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.ProximityActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.RadioActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.StepCounterActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.StepDetectorActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.TemperatureActivity;
import com.appbusters.robinkamboj.senseitall.view.robin.VibratorActivity;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;

public class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView sensor_name;
    public ImageView sensor_imageview, imageview_disabled;
    public CardView cardView;
    ItemClickListener clickListener;
    Context context;
    double Lat=28;
    double Lon=77;


    public View_Holder(View itemView) {
        super(itemView);

        sensor_name = (TextView) itemView.findViewById(R.id.textView);
        sensor_imageview = (ImageView) itemView.findViewById(R.id.imageView);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        imageview_disabled = (ImageView) itemView.findViewById(R.id.imageview_disabled);

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
        position++;
        switch (position){
            case 1:{
                Intent i = new Intent(context, CameraActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 2:{
                Intent i = new Intent(context, CameraSecondaryActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 3:{
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
                        Lat=  location.getLatitude();
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
                if (ActivityCompat.checkSelfPermission((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Activity) context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions((Activity) context,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},121);
                    return;
                }else{
                    locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, locLis);
                    locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,10,locLis);
                }
                Intent i=  new Intent(context, MapsActivity.class);
                i.putExtra("lat",  Lat);
                i.putExtra("lon", Lon);
                context.startActivity(i);
                break;
            }
            case 4:{

                break;
            }
            case 5:{

                break;
            }
            case 6:{
                Intent i = new Intent(context, GSMActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 7:{

                break;
            }
            case 8:{
                Intent i = new Intent(context, CompassActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 9:{
                Intent i = new Intent(context, RadioActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 10:{

                break;
            }
            case 11:{
                Intent i = new Intent(context, BatteryActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 12:{
                Intent i = new Intent(context, CPUActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 13:{

                break;
            }
            case 14:{
                Intent i = new Intent(context, VibratorActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 15:{

                break;
            }
            case 16:{

                break;
            }
            case 17:{

                break;
            }
            case 18:{
                Intent i = new Intent(context, AndroidOSActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 19:{
                Intent i = new Intent(context, LightActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 20:{
                Intent i = new Intent(context, ProximityActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 21:{
                Intent i = new Intent(context, TemperatureActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 22:{
                Intent i = new Intent(context, PressureActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 23:{

                break;
            }
            case 24:{
                Intent i = new Intent(context, FlashActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 25:{

                break;
            }
            case 26:{

                break;
            }
            case 27:{

                break;
            }
            case 28:{

                break;
            }
            case 29:{

                break;
            }
            case 30:{

                break;
            }
            case 31:{
                Intent i = new Intent(context, StepDetectorActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 32:{
                Intent i = new Intent(context, StepCounterActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 33:{

                break;
            }
            case 34:{

                break;
            }
            case 35:{

                break;
            }
            case 36:{
                Intent i = new Intent(context, MultiTouchActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 37:{

                break;
            }
            case 38:{

                break;
            }
            case 39:{

                break;
            }
            case 40:{
                Intent i = new Intent(context, ECGActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 41:{
                Intent i = new Intent(context, FingerprintActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 42:{
                Intent i = new Intent(context, NFCActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
            case 43:{
                Intent i = new Intent(context, MagneticActivity.class);
                i.putExtra("sensorName", sensorName);
                context.startActivity(i);
                break;
            }
        }
    }
}
