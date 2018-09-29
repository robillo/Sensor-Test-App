package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.volume_control.utils;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.v4.app.FragmentManager;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.tool_activity.ToolActivity;
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.volume_control.VolumeControlFragment;

public class VolumeObserver extends ContentObserver {

    private Context context;

    public VolumeObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        ToolActivity toolActivity = (ToolActivity) context;
        if(toolActivity != null) {
            try {

                FragmentManager manager = ((ToolActivity) context).getSupportFragmentManager();
                VolumeControlFragment fragment = null;

                if(manager != null) {
                    fragment
                            = (VolumeControlFragment) manager.findFragmentByTag(
                                    context.getString(R.string.tag_volume_control_fragment)
                    );
                }

                if(fragment != null) {
                    fragment.setInitialVolumes();
                }
            }
            catch (ClassCastException ignored) {

            }
        }
    }
}