package com.appbusters.robinkamboj.senseitall.model.recycler;

import java.util.jar.Manifest;

public class PermissionsItem {

    private String permissionName;
    private boolean isGranted;

    public PermissionsItem(String permissionName, boolean isGranted) {
        this.permissionName = permissionName;
        this.isGranted = isGranted;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }
}
