package com.appbusters.robinkamboj.senseitall.model;

/**
 * Created by rishabhshukla on 23/03/17.
 */

public class ScanResultInfo {
    String name;
    String capabilities;
    int level;
    int frequency;
    int width;
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

    public int getWidth() {
        return width;
    }

    public String getMac() {
        return mac;
    }

    public ScanResultInfo(String name, String capabilities, int level, int frequency, int width, String mac) {
        this.name = name;
        this.capabilities = capabilities;
        this.level = level;
        this.frequency = frequency;
        this.width = width;
        this.mac = mac;
    }
}
