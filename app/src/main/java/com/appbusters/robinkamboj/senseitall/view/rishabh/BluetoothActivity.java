package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private static final int BLUETOOTH_PERMISSION = 120;
    private static final String TAG = "BT";
    SensorManager sensorManager;
    BluetoothAdapter bluetoothAdapter;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);


        String[] permissions={Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN};


        if(!hasPermissions(this, permissions)){
            ActivityCompat.requestPermissions(this, permissions, BLUETOOTH_PERMISSION);
        }
//        sensorManager = (SensorManager) getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }

        final ProgressDialog progress = new ProgressDialog(BluetoothActivity.this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                pbar.setVisibility(View.VISIBLE);

//                pbar.
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                    for (BluetoothDevice device : pairedDevices) {
                        String deviceName = device.getName();
                        String deviceHardwareAddress = device.getAddress(); // MAC address

                        Log.d(TAG, "run: "+device);
                        Log.d(TAG, "run: "+deviceHardwareAddress);
                        Log.d(TAG, "run: "+device.getUuids());

                        progress.hide();
                    }
                }
            }
        }, 1500);

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

    @Override
    protected void onStop() {
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
        super.onStop();
    }
}
