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
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private static final int BLUETOOTH_PERMISSION = 120;
    private static final String TAG = "BT";
    SensorManager sensorManager;
    BluetoothAdapter bluetoothAdapter;
    TextView name,add,state,scan;
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        name= (TextView) findViewById(R.id.name);
        scan= (TextView) findViewById(R.id.scan);
        add= (TextView) findViewById(R.id.add);
        state= (TextView) findViewById(R.id.state);
        String[] permissions={Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN};


        if(!hasPermissions(this, permissions)){
            ActivityCompat.requestPermissions(BluetoothActivity.this, permissions, BLUETOOTH_PERMISSION);
        }
//        sensorManager = (SensorManager) getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        final ProgressDialog progress = new ProgressDialog(BluetoothActivity.this);
        progress.setMessage("Getting Bluetooth Info...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }
//        Timer t = new Timer();
//        t.scheduleAtFixedRate(new TimerTask() {
//                                  @Override
//                                  public void run() {
//                                      runTextViews();
//                                  }
//                              }, 0, 2000);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                pbar.setVisibility(View.VISIBLE);

//                pbar.

                runTextViews();

                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                    for (BluetoothDevice device : pairedDevices) {
                        String deviceName = device.getName();
                        String deviceHardwareAddress = device.getAddress(); // MAC address

                        Log.d(TAG, "run: "+deviceName);
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

    public void runTextViews(){

        name.setText("NAME:   "+bluetoothAdapter.getName());
        add.setText("ADDRESS:   "+bluetoothAdapter.getAddress());
        if(bluetoothAdapter.getState()==BluetoothAdapter.STATE_CONNECTED){
            state.setText("STATE:   "+"CONNECTED");
        }else if(bluetoothAdapter.getState()==BluetoothAdapter.STATE_CONNECTING){
            state.setText("STATE:   "+"CONNECTING");
        }else if(bluetoothAdapter.getState()==BluetoothAdapter.STATE_DISCONNECTING){
            state.setText("STATE:   "+"DISCONNECTING");
        }else if(bluetoothAdapter.getState()==BluetoothAdapter.STATE_ON){
            state.setText("STATE:   "+"ON");
        }else if(bluetoothAdapter.getState()==BluetoothAdapter.STATE_OFF){
            state.setText("STATE:   "+"OFF");
        }else if(bluetoothAdapter.getState()==BluetoothAdapter.STATE_TURNING_OFF){
            state.setText("STATE:   "+"TURNING OFF");
        }else if(bluetoothAdapter.getState()==BluetoothAdapter.STATE_TURNING_ON){
            state.setText("STATE:   "+"TURNING ON");
        }else{
            state.setText("STATE:   "+"DISCONNECTED");
        }
        if(bluetoothAdapter.getScanMode()==BluetoothAdapter.SCAN_MODE_CONNECTABLE) {
            scan.setText("SCAN MODE:   " + "CONNECTABLE");
        }else if(bluetoothAdapter.getScanMode()==BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            scan.setText("SCAN MODE:   " + "CONNECTABLE DISCOVERABLE");
        }else {
            scan.setText("SCAN MODE:   " + "NONE");
        }
    }
}
