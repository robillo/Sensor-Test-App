package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.location.LocationManager
import android.net.*
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.SensorApplication
import com.appbusters.robinkamboj.senseitall.di.component.activity_component.main_activity.fragment_component.DaggerToolsFragmentComponent
import com.appbusters.robinkamboj.senseitall.model.recycler.SettingInfo
import com.appbusters.robinkamboj.senseitall.model.recycler.ToolsItem
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.utils.StartSnapHelper
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.adapter.image_tools.ImageToolsAdapter
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.adapter.quick_settings.QuickSettingsAdapter
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.adapter.quick_settings.QuickSettingsListener
import kotlinx.android.synthetic.main.fragment_tools.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ToolsFragment : Fragment() {

    private var everydayToolsList: MutableList<ToolsItem> = ArrayList()
    private var imageToolsList: MutableList<ToolsItem> = ArrayList()
    lateinit var list: MutableList<SettingInfo>
    private lateinit var quickAdapter: QuickSettingsAdapter
    lateinit var parentView : View

    private val imageToolsCount = imageTools.size
    private val everydayToolsCount = everydayTools.size

    private var quickSettingsList: MutableList<SettingInfo>? = null
    private var networkCallback: ConnectivityManager.NetworkCallback? = null
    private var connectivityManager: ConnectivityManager? = null
    private var locationReceiver: BroadcastReceiver? = null
    private var wifiAndHotspotReceiver: BroadcastReceiver? = null
    private var bluetoothReceiver: BroadcastReceiver? = null
    private var airplaneReceiver: BroadcastReceiver? = null
    private var autorotateObserver: ContentObserver? = null

    companion object {
        private var settingsList = arrayOf(WIFI_QUICK, BLUETOOTH_QUICK, AUTOROTATE_QUICK,
                AIRPLANE_QUICK, BRIGHTNESS_QUICK, VOLUME_QUICK, FLASHLIGHT_QUICK, LOCATION_QUICK, HOTSPOT_QUICK)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the parentView for this fragment
        val v = inflater.inflate(R.layout.fragment_tools, container, false)
        setup(v)
        return v
    }

    private fun setup(v: View) {
        parentView = v

        initialize()
    }

    fun initialize() {

        initDagger()
        inflateQuickSettingsList()
        setImageToolsAdapter()
        setEverydayToolsAdapter()
        setQuickSettingsRecycler()
        checkQuickSettingsStatus()
    }

    private fun initDagger() {
        val fragmentComponent = DaggerToolsFragmentComponent.builder()
                .siaApplicationComponent((activity?.applicationContext as SensorApplication).getApplicationComponent())
                .build()
        fragmentComponent.injectToolsFragment(this)
    }

    private fun inflateQuickSettingsList() {
        quickSettingsList = ArrayList()
        quickSettingsList?.let {
            for (setting in settingsList)
                it.add(getTinyInfo(setting))
        }
    }

    private fun getTinyInfo(setting: String): SettingInfo {
        return SettingInfo(setting, settingOnMapImage[setting]!!, settingOffMapImage[setting]!!)
    }

    override fun onStart() {
        super.onStart()
        createReceivers()
        registerReceivers()
    }

    private fun createReceivers() {
        createWifiReceiver()
        createLocationReceiver()
        createBluetoothReceiver()
        createAutoRotateObserver()
        createAirplaneModeReceiver()
    }

    private fun registerReceivers() {
        registerWifiReceiver()
        registerLocationReceiver()
        registerBluetoothReceiver()
        registerAutoRotateObserver()
        registerAirplaneModeReceiver()
    }

    private fun createLocationReceiver() {
        if(locationReceiver == null) {
            locationReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    intent?.action?.let {
                        if(it == LocationManager.MODE_CHANGED_ACTION) {
                            if (isLocationPermissionGranted())
                                updateLocationItemForState()
                            else
                                showCoordinatorNegative(getString(R.string.location_permission_negative))
                        }
                    }
                }
            }
        }
    }

    private fun registerLocationReceiver() {
        val locationFilter = IntentFilter()
        locationFilter.addAction(LocationManager.MODE_CHANGED_ACTION)
        activity?.registerReceiver(locationReceiver, locationFilter)
    }

    private fun isLocationPermissionGranted(): Boolean {
        activity?.let {
            return ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    private fun updateLocationItemForState() {
        val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        checkItemStateThenUpdateAdapter(LOCATION_QUICK, locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
    }

    private fun createWifiReceiver() {
        wifiAndHotspotReceiver?.let {
            return
        } ?: kotlin.run {

            wifiAndHotspotReceiver = object: BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    intent?.action?.let {

                        if(it == WifiManager.NETWORK_STATE_CHANGED_ACTION)
                            updateWifiItemForState(intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO))

                        if(it == getString(R.string.wifi_ap_state_change_action))
                            updateHotspotItemForState(intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0))
                    }
                }
            }
        }
    }

    private fun updateWifiItemForState(networkInfo: NetworkInfo) {
        when(networkInfo.detailedState) {
            NetworkInfo.DetailedState.IDLE, NetworkInfo.DetailedState.SCANNING, NetworkInfo.DetailedState.CONNECTING,
            NetworkInfo.DetailedState.CONNECTED, NetworkInfo.DetailedState.AUTHENTICATING,
            NetworkInfo.DetailedState.OBTAINING_IPADDR, NetworkInfo.DetailedState.SUSPENDED ->
                checkItemStateThenUpdateAdapter(WIFI_QUICK, true)
            else -> checkItemStateThenUpdateAdapter(WIFI_QUICK, false)
        }
    }

    private fun updateHotspotItemForState(state: Int) {
        checkItemStateThenUpdateAdapter(
                HOTSPOT_QUICK,
                WifiManager.WIFI_STATE_ENABLED == state % 10
        )
    }

    private fun registerWifiReceiver() {
        val wifiFilter = IntentFilter()
        wifiFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        wifiFilter.addAction(getString(R.string.wifi_ap_state_change_action))
        activity?.registerReceiver(wifiAndHotspotReceiver, wifiFilter)
    }

    private fun createBluetoothReceiver() {
        if(bluetoothReceiver == null) {
            bluetoothReceiver = object: BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    intent?.action?.let {
                        if (it == BluetoothAdapter.ACTION_STATE_CHANGED)
                            updateBluetoothItemForState(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1))
                    }
                }
            }
        }
    }

    private fun updateBluetoothItemForState(state: Int) {
        checkItemStateThenUpdateAdapter(
                BLUETOOTH_QUICK,
                state == BluetoothAdapter.STATE_ON || state == BluetoothAdapter.STATE_CONNECTING
                        || state == BluetoothAdapter.STATE_TURNING_ON
        )
    }

    private fun registerBluetoothReceiver() {
        val bluetoothFilter = IntentFilter()
        bluetoothFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        activity?.registerReceiver(bluetoothReceiver, bluetoothFilter)
    }

    private fun createAirplaneModeReceiver() {
        if(airplaneReceiver == null) {
            airplaneReceiver = object: BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    intent?.action?.let {
                        if (it == Intent.ACTION_AIRPLANE_MODE_CHANGED) updateAirplaneModeItemForState()
                    }
                }
            }
        }
    }

    private fun registerAirplaneModeReceiver() {
        val airplaneFilter = IntentFilter()
        airplaneFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        activity?.registerReceiver(airplaneReceiver, airplaneFilter)
    }

    private fun updateAirplaneModeItemForState() {
        checkItemStateThenUpdateAdapter(
                AIRPLANE_QUICK,
                Settings.Global.getInt(context?.contentResolver,
                        Settings.Global.AIRPLANE_MODE_ON, 0) != 0
        )
    }

    private fun createAutoRotateObserver() {
        if(autorotateObserver == null) {
            autorotateObserver = object: ContentObserver(Handler()) {
                override fun onChange(selfChange: Boolean) {
                    checkItemStateThenUpdateAdapter(
                            AUTOROTATE_QUICK,
                            android.provider.Settings.System.getInt(activity?.contentResolver,
                                    Settings.System.ACCELEROMETER_ROTATION, 0) == 1
                    )
                }
            }
        }
    }

    private fun registerAutoRotateObserver() {
        autorotateObserver?.let {
            activity?.contentResolver?.registerContentObserver(
                    Settings.System.getUriFor(Settings.System.ACCELEROMETER_ROTATION),
                    true,
                    it
            )
        }
    }

    private fun checkItemStateThenUpdateAdapter(item: String, isPositive: Boolean) {
        if(isPositive && !quickAdapter.getItemState(item)) {
            quickAdapter.updateItemState(item, true)
            showCoordinatorPositive(item + " " + getString(R.string.enabled))
        }
        if(!isPositive && quickAdapter.getItemState(item)) {
            quickAdapter.updateItemState(item, false)
            showCoordinatorPositive(item + " " + getString(R.string.disabled))
        }
    }

    private fun setImageToolsAdapter() {

        if(imageToolsList.size < imageToolsCount) {
            imageTools.forEach {
                tool -> imageToolsList.add(ToolsItem(tool, toolImageUrlMap[tool]))
            }
        }

        parentView.image_tools_rv.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
        )
        parentView.image_tools_rv.adapter = ImageToolsAdapter(imageToolsList, activity)
        parentView.image_tools_rv.onFlingListener = null
        attachSnapHelper(parentView.image_tools_rv)
    }

    private fun setEverydayToolsAdapter() {

        if(everydayToolsList.size < everydayToolsCount) {
            everydayTools.forEach {
                tool -> everydayToolsList.add(ToolsItem(tool, toolImageUrlMap[tool]))
            }
        }

        parentView.everyday_tools_rv.adapter?.let {
            return
        }

        parentView.everyday_tools_rv.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
        )
        parentView.everyday_tools_rv.adapter = ImageToolsAdapter(everydayToolsList, activity)
        parentView.everyday_tools_rv.onFlingListener = null
        attachSnapHelper(parentView.everyday_tools_rv)
    }

    private fun setQuickSettingsRecycler() {
        quickSettingsList?.let { settingsList ->
            parentView.quick_settings_rv.layoutManager = LinearLayoutManager(
                    activity,
                    LinearLayoutManager.HORIZONTAL,
                    false
            )
            quickAdapter = QuickSettingsAdapter(settingsList, activity, QuickSettingsListener {
                flipSetting(it)
            })
            parentView.quick_settings_rv.adapter = quickAdapter
            parentView.quick_settings_rv.onFlingListener = null
            attachSnapHelper(parentView.quick_settings_rv)
        }
    }

    private fun attachSnapHelper(recycler: RecyclerView) {
        StartSnapHelper().attachToRecyclerView(recycler)
    }

    private fun checkQuickSettingsStatus() {
        quickSettingsList?.let {
            for(setting in it)
                    checkEachQuickSetting(setting.name)
        }
    }

    private fun checkEachQuickSetting(setting: String) {
        when (setting) {
            BLUETOOTH_QUICK -> updateSettingsAdapterForState(setting, BluetoothAdapter.getDefaultAdapter().isEnabled)
            AIRPLANE_QUICK -> updateSettingsAdapterForState(setting, checkAirplaneModeSettingState())
            AUTOROTATE_QUICK -> updateSettingsAdapterForState(setting, checkAutoRotateSettingState())
            WIFI_QUICK -> updateSettingsAdapterForState(setting, checkWifiSettingState())
            HOTSPOT_QUICK -> {
                if (isLocationPermissionGranted())
                    updateSettingsAdapterForState(setting, checkHotspotSettingState())
                else
                    showCoordinatorNegative(getString(R.string.permissions_not_given))
            }
            LOCATION_QUICK -> {
                if(isLocationPermissionGranted())
                    updateSettingsAdapterForState(setting, checkLocationSettingState())
                else
                    showCoordinatorNegative(getString(R.string.permissions_not_given))
            }
            BRIGHTNESS_QUICK, VOLUME_QUICK, FLASHLIGHT_QUICK -> {
            }
        }
    }

    private fun updateSettingsAdapterForState(setting: String, isOn: Boolean) {
        quickAdapter.updateItemState(setting, isOn)
    }

    private fun checkHotspotSettingState(): Boolean {
        val wifiManager = activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        val method = wifiManager?.javaClass?.getDeclaredMethod("getWifiApState")
        method?.isAccessible = true
        return try {
            val actualState = method?.invoke(wifiManager, null) as Int
            actualState % 10 == WifiManager.WIFI_STATE_ENABLED
        } catch (e: java.lang.Exception) {
            false
        }
    }

    private fun checkWifiSettingState(): Boolean {
        val wifiManager =
                activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        return wifiManager?.isWifiEnabled ?: false
    }

    private fun checkLocationSettingState(): Boolean {
        val locationManager: LocationManager? = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false
    }

    private fun checkAirplaneModeSettingState(): Boolean {
        return Settings.Global.getInt(context?.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    }

    private fun checkAutoRotateSettingState(): Boolean {
        return android.provider.Settings.System.getInt(activity?.contentResolver, Settings.System.ACCELEROMETER_ROTATION, 0) == 1
    }

    private fun flipSetting(info: SettingInfo) {
        when(info.name) {
            WIFI_QUICK -> flipWifiSetting(!info.isOn)
            BLUETOOTH_QUICK -> flipBluetoothSetting(!info.isOn)
            BRIGHTNESS_QUICK, VOLUME_QUICK, FLASHLIGHT_QUICK -> { }
            HOTSPOT_QUICK -> flipHotspotSetting(!info.isOn)
            LOCATION_QUICK -> flipLocationAccessSetting(!info.isOn)
            AIRPLANE_QUICK -> flipAirplaneModeSetting(!info.isOn)
            AUTOROTATE_QUICK -> flipAutorotateSetting(!info.isOn)
        }
    }

    private fun flipBluetoothSetting(turnOn: Boolean) {

        val isEnabled = BluetoothAdapter.getDefaultAdapter().isEnabled

        if(isEnabled && turnOn) setBluetoothState(true)

        else if(!isEnabled && !turnOn) setBluetoothState(false)

        else if(!isEnabled && turnOn) {
            BluetoothAdapter.getDefaultAdapter().enable()
            setBluetoothState(true)
        }

        else if(isEnabled && !turnOn) {
            BluetoothAdapter.getDefaultAdapter().disable()
            setBluetoothState(false)
        }
    }

    private fun setBluetoothState(isOnOff: Boolean) {
        checkItemStateThenUpdateAdapter(BLUETOOTH_QUICK, isOnOff)
        quickAdapter.updateItemState(BLUETOOTH_QUICK, isOnOff)
    }

    private fun flipWifiSetting(turnOn: Boolean) {
        val wifiManager = activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val isWifiEnabled = wifiManager.isWifiEnabled

        if(isWifiEnabled && turnOn) setWifiState(true)

        else if(!isWifiEnabled && !turnOn) setWifiState(false)

        else if(isWifiEnabled && !turnOn) {
            wifiManager.isWifiEnabled = false
            setWifiState(false)
        }

        else if(!isWifiEnabled && turnOn) {
            wifiManager.isWifiEnabled = true
            setWifiState(true)
        }
    }

    private fun setWifiState(isOnOff: Boolean) {
        checkItemStateThenUpdateAdapter(WIFI_QUICK, isOnOff)
        quickAdapter.updateItemState(WIFI_QUICK, isOnOff)
    }

    private fun flipAutorotateSetting(turnOn: Boolean) {
        try {
            Settings.System.putInt(
                    context!!.contentResolver,
                    Settings.System.ACCELEROMETER_ROTATION,
                    if (turnOn) 1 else 0
            )
            checkItemStateThenUpdateAdapter(AUTOROTATE_QUICK, turnOn)
            quickAdapter.updateItemState(AUTOROTATE_QUICK, turnOn)
        }
        catch (e: Exception) {
            showCoordinatorSettings(getString(R.string.modify_system_settings))
        }
    }

    private fun flipHotspotSetting(turnOn: Boolean) {
        showCoordinatorNegative(getString(R.string.setting_cannot_change))
    }

    private fun flipAirplaneModeSetting(turnOn: Boolean) {
        showCoordinatorNegative(getString(R.string.setting_cannot_change))
    }

    private fun flipLocationAccessSetting(turnOn: Boolean) {
        showCoordinatorNegative(getString(R.string.setting_cannot_change))
    }

    private fun showCoordinatorNegative(coordinatorText: String) {
        activity?.let {
            try {
                val s = Snackbar.make(parentView.coordinator_tools, coordinatorText, Snackbar.LENGTH_SHORT)
                val v = s.view
                v.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.red_shade_three_less_vibrant))
                val t = v.findViewById<TextView>(android.support.design.R.id.snackbar_text)
                t.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                t.textAlignment = View.TEXT_ALIGNMENT_CENTER
                s.show()
            }
            catch (ignored: java.lang.Exception) {}
        }
    }

    private fun showCoordinatorPositive(coordinatorText: String) {
        activity?.let {
            getSnackBar(
                    it, coordinatorText, R.color.primary_new, R.color.white, View.TEXT_ALIGNMENT_CENTER
            ).show()
        }
    }

    private fun showCoordinatorSettings(coordinatorText: String) {
        activity?.let {
            getSnackBar(
                    it, coordinatorText, R.color.colorBlackShade, R.color.white, View.TEXT_ALIGNMENT_VIEW_START
            ).setAction(getString(R.string.navigate_to_settings)) {
                startActivityForResult(Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS), 0)
            }.show()
        }
    }

    private fun getSnackBar(context: Context, text: String, backColor: Int, textColor: Int, textAlignment: Int): Snackbar {
        val snackBar = Snackbar.make(parentView.coordinator_tools, text, Snackbar.LENGTH_LONG)
        val view = snackBar.view
        view.setBackgroundColor(ContextCompat.getColor(context, backColor))
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(context, textColor))
        textView.textAlignment = textAlignment
        return snackBar
    }

    override fun onStop() {
        unregisterReceivers()
        super.onStop()
    }

    private fun unregisterReceivers() {

        activity?.let { act ->
            try {
                act.unregisterReceiver(locationReceiver)
                act.unregisterReceiver(wifiAndHotspotReceiver)
                act.unregisterReceiver(bluetoothReceiver)
                act.unregisterReceiver(airplaneReceiver)

                connectivityManager?.unregisterNetworkCallback(networkCallback)

                autorotateObserver?.let {
                    act.contentResolver?.unregisterContentObserver(it)
                }
            }
            catch (ignored: java.lang.Exception) { }
        }
    }
}