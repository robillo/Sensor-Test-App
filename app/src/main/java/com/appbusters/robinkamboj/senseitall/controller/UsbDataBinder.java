package com.appbusters.robinkamboj.senseitall.controller;

/**
 * Created by rishabhshukla on 04/04/17.
 */

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;

public class UsbDataBinder {
    private byte[] bytes = new byte[1024];
    private static int TIMEOUT = 0;
    private boolean forceClaim = true;
    private UsbDevice mDevice;
    private UsbManager mUsbManager;
    private UsbDeviceConnection mConnection;
    private UsbInterface mIntf;
    private UsbEndpoint mEndpoint;

    public UsbDataBinder(UsbManager manager, UsbDevice device) {
        mUsbManager = manager;
        mDevice = device;
        mIntf = mDevice.getInterface(0);
        mEndpoint = mIntf.getEndpoint(0);
        mConnection = mUsbManager.openDevice(mDevice);
    }

    public void sendData() {
        mConnection.claimInterface(mIntf, forceClaim);
        new Thread(new Runnable(){

            @Override
            public void run() {
                mConnection.bulkTransfer(mEndpoint, bytes, bytes.length, TIMEOUT); //do in another thread
            }

        }).start();
    }

    public void onDestroy() {
        if (mConnection != null) {
            mConnection.releaseInterface(mIntf);
            mConnection.close();
        }
    }
}