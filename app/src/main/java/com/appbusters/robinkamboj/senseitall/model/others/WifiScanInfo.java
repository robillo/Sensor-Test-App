package com.appbusters.robinkamboj.senseitall.model.others;

public class WifiScanInfo {

    private String name, capabilities, mac;
    private int level, frequency;
    private long timestamp;

    public WifiScanInfo(String name, String capabilities, int level, int frequency, long timestamp, String mac) {
        this.name = name;
        this.capabilities = capabilities;
        this.level = level;
        this.frequency = frequency;
        this.timestamp = timestamp;
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public int getLevel() {
        return level;
    }

    public int getFrequency() {
        return frequency;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMac() {
        return mac;
    }
}
