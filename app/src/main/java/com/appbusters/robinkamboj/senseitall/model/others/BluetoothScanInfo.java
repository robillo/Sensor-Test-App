package com.appbusters.robinkamboj.senseitall.model.others;

public class BluetoothScanInfo {
    private String name, address;

    public BluetoothScanInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
