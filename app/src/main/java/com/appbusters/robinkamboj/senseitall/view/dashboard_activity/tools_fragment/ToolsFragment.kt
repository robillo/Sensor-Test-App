package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment


import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.Global.AIRPLANE_MODE_ON
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.model.recycler.TinyInfo
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.utils.StartSnapHelper
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.adapter.quick_settings.QuickSettingsAdapter
import kotlinx.android.synthetic.main.fragment_tools.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class ToolsFragment : Fragment(), ToolsInterface {

    lateinit var list: MutableList<TinyInfo>
    lateinit var quickAdapter: QuickSettingsAdapter
    lateinit var lv : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the lv for this fragment
        val v = inflater.inflate(R.layout.fragment_tools, container, false)
        setup(v)
        return v
    }

    override fun setup(v: View) {
        lv = v
        setQuickSettingsRecycler()
        checkQuickSettingsStatus()
    }

    override fun setQuickSettingsRecycler() {
        list = AppConstants.quickSettings
        quickAdapter = QuickSettingsAdapter(list, activity)
        lv.quick_settings_rv.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        lv.quick_settings_rv.adapter = quickAdapter
        lv.quick_settings_rv.onFlingListener = null
        StartSnapHelper().attachToRecyclerView(lv.quick_settings_rv)
    }

    override fun checkQuickSettingsStatus() {
        for(info in list) {
            checkEachQuickSetting(info.name)
        }
    }

    override fun checkEachQuickSetting(info: String) {
        when (info) {
            WIFI_QUICK -> {
                val wifiManager =
                        activity?.getApplicationContext()?.getSystemService(Context.WIFI_SERVICE) as WifiManager
                if(wifiManager.isWifiEnabled) quickAdapter.updateItemState(info, true)

            }
            BLUETOOTH_QUICK -> {
                if(BluetoothAdapter.getDefaultAdapter().isEnabled) quickAdapter.updateItemState(info, true)
            }
            BRIGHTNESS_QUICK -> {

            }
            VOLUME_QUICK -> {

            }
            HOTSPOT_QUICK -> {

            }
            FLASHLIGHT_QUICK -> {

            }
            LOCATION_QUICK -> {

            }
            AIRPLANE_QUICK -> {
                if(Settings.System.getInt(context?.getContentResolver(),
                                AIRPLANE_MODE_ON, 0) != 0) {
                    quickAdapter.updateItemState(info, true)
                }
            }
            AUTOROTATE_QUICK -> {
                if(android.provider.Settings.System.getInt(activity?.contentResolver,
                                Settings.System.ACCELEROMETER_ROTATION, 0) == 1){
                    quickAdapter.updateItemState(info, true)
                }
            }
        }
    }
}
