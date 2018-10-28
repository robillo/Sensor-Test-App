package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.sensors_fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.sensors_fragment.all_sensors_adapter.AllSensorsAdapter
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.sensors_fragment.featured_sensors_adapter.FeaturedSensorsAdapter
import kotlinx.android.synthetic.main.fragment_sensors.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SensorsFragment : Fragment() {

    private lateinit var fragment: View
    private lateinit var featuredSensorsList: List<String>
    private lateinit var allSensorsList: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragment = inflater.inflate(R.layout.fragment_sensors, container, false)
        performSetupOperations()
        return fragment
    }

    private fun performSetupOperations() {
        setFeaturedSensorsAdapter()
        setAllSensorsAdapter()
    }

    private fun setFeaturedSensorsAdapter() {
        featuredSensorsList = AppConstants.featuredSensorNames
        fragment.featured_items_recycler_view.adapter = FeaturedSensorsAdapter(featuredSensorsList, activity)
    }

    private fun setAllSensorsAdapter() {
        allSensorsList = AppConstants.sensorNames
        fragment.all_items_recycler_view.adapter = AllSensorsAdapter(allSensorsList, activity)
    }
}
