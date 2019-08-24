package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.battery_test_fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.appbusters.robinkamboj.senseitall.R;

public class BatteryReceiver extends BroadcastReceiver {

    private BatteryTestFragment fragment;

    public BatteryReceiver(BatteryTestFragment fragment) {
        this.fragment = fragment;
    }

    public BatteryReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null) {
            try {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

                float batteryPct = (level / (float) scale)*100;

                fragment.speedometer.speedPercentTo((int) batteryPct);

                if(isCharging)
                    fragment.statusBatteryText.setText(R.string.charging);
                else
                    fragment.statusBatteryText.setText(R.string.not_charging);
            }
            catch (NoSuchMethodError | Exception ignored) {

            }
        }
    }
}
