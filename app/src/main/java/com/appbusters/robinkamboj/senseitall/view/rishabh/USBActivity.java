package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.UsbDataBinder;

import java.util.HashMap;
import java.util.Iterator;

public class USBActivity extends AppCompatActivity {

    private TextView mInfo;
    private HashMap<UsbDevice, UsbDataBinder> mHashMap = new HashMap<UsbDevice, UsbDataBinder>();
    private UsbManager mUsbManager;
    private PendingIntent mPermissionIntent;


    private static final String TAG = "USBDEVICE";
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb);
//
//        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
//
//        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
//        UsbDevice device = deviceList.get("deviceName");
//
//        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
//        while(deviceIterator.hasNext()){
//            UsbDevice dev = deviceIterator.next();
//            Log.d(TAG, "onCreate: "+dev.getDeviceName()+" "+dev.getManufacturerName());
//        }
        BroadcastReceiver mUsbAttachReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                }
            }
        };
        BroadcastReceiver mUsbDetachReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (device != null) {
                        // call your method that cleans up and closes communication with the device
                        UsbDataBinder binder = mHashMap.get(device);
                        if (binder != null) {
                            binder.onDestroy();
                            mHashMap.remove(device);
                        }
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        registerReceiver(mUsbAttachReceiver , filter);
        filter = new IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(mUsbDetachReceiver , filter);

        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while(deviceIterator.hasNext()){
            UsbDevice device = deviceIterator.next();
            Log.d(TAG,"name: " + device.getDeviceName() + ", " +
                    "ID: " + device.getDeviceId());
            mInfo.append(device.getDeviceName() + "\n");
            mInfo.append(device.getDeviceId() + "\n");
            mInfo.append(device.getDeviceProtocol() + "\n");
            mInfo.append(device.getProductId() + "\n");
            mInfo.append(device.getVendorId() + "\n");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}







