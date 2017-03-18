package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appbusters.robinkamboj.senseitall.R;

public class BluetoothActivity extends AppCompatActivity {

    private static final int BLUETOOTH_PERMISSION = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        String[] permissions={Manifest.permission.BLUETOOTH};

        if(!hasPermissions(this, permissions)){
            ActivityCompat.requestPermissions(this, permissions, BLUETOOTH_PERMISSION);
        }

    }
    public static boolean hasPermissions(Context context, String[] permissions){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission : permissions){
                if(ActivityCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }
}
