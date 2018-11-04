package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.items_fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.items_fragment.all_items_adapter.AllItemsAdapter
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.items_fragment.featured_sensors_adapter.FeaturedItemsAdapter
import kotlinx.android.synthetic.main.fragment_items.view.*

class ItemsFragment : Fragment() {

    private lateinit var itemType: String

    private lateinit var fragment: View
    private lateinit var featuredItemsList: List<String>
    private lateinit var allItemsList: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragment = inflater.inflate(R.layout.fragment_items, container, false)
        getItemType()
        performSetupOperations()
        return fragment
    }

    private fun getItemType() {
        itemType = arguments!!.getString(TYPE)!!
    }

    private fun performSetupOperations() {
        when(itemType) {
            SENSOR_HEADER -> {
                allItemsList = AppConstants.sensorNames
                featuredItemsList = AppConstants.featuredSensorNames
            }
            FEATURE_HEADER -> {
                allItemsList = AppConstants.featureNames
                featuredItemsList = AppConstants.featuredSensorNames
            }
            TOOLS_HEADER -> {
                allItemsList = AppConstants.sensorNames
                featuredItemsList = AppConstants.featuredSensorNames
            }
            TRENDING_HEADER -> {
                allItemsList = AppConstants.sensorNames
                featuredItemsList = AppConstants.featuredSensorNames
            }
            DEVICE_HEADER -> {
                allItemsList = AppConstants.sensorNames
                featuredItemsList = AppConstants.featuredSensorNames
            }
            ANDROID_HEADER -> {
                allItemsList = AppConstants.sensorNames
                featuredItemsList = AppConstants.featuredSensorNames
            }
            else -> {
                allItemsList = AppConstants.sensorNames
                featuredItemsList = AppConstants.featuredSensorNames
            }
        }
        setFeaturedItemsAdapter()
        setAllItemsAdapter()
    }

    private fun setFeaturedItemsAdapter() {
        fragment.featured_items_recycler_view.adapter = FeaturedItemsAdapter(featuredItemsList, activity as Context)
    }

    private fun setAllItemsAdapter() {
        fragment.all_items_recycler_view.adapter = AllItemsAdapter(allItemsList, activity as Context)
    }
}
