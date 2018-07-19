package com.appbusters.robinkamboj.senseitall.model.others;

/**
 * Created by rishabhshukla on 23/03/17.
 */

public class ScanResultInfo {
    String name;
    String capabilities;
    int level;
    int frequency;
    long timestamp;
    String mac;

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

    public ScanResultInfo(String name, String capabilities, int level, int frequency, long timestamp, String mac) {
        this.name = name;
        this.capabilities = capabilities;
        this.level = level;
        this.frequency = frequency;
        this.timestamp = timestamp;
        this.mac = mac;
    }
}
