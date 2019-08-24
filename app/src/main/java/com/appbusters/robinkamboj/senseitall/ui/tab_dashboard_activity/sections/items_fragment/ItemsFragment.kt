package com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity.sections.items_fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity.sections.items_fragment.all_items_adapter.AllItemsAdapter
import com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity.sections.items_fragment.featured_sensors_adapter.FeaturedItemsAdapter
import kotlinx.android.synthetic.main.fragment_items.view.*

class ItemsFragment : Fragment() {

    private var itemType: Int = 0

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
        arguments?.let {
            itemType = it.getInt(TYPE)
        }
    }

    private fun performSetupOperations() {
        inflateLists()
        setFeaturedItemsAdapter()
        setAllItemsAdapter()
    }

    private fun inflateLists() {
        when(itemType) {
            TYPE_SENSORS -> inflateItemsLists(sensorNames, featuredSensorNames)
            TYPE_FEATURES -> inflateItemsLists(featureNames, featuredFeatureNames)
            TYPE_TRENDING -> inflateItemsLists(trendingNames, featuredTrendingNames)
            TYPE_DEVICE -> inflateItemsLists(deviceDetailsNames, featuredDeviceDetailsNames)
            TYPE_ANDROID -> inflateItemsLists(androidVersionNames, featuredAndroidVersionNames)
            TYPE_TOOLS -> inflateItemsLists(ArrayList(), ArrayList())
            else -> inflateItemsLists(ArrayList(), ArrayList())
        }
    }

    private fun inflateItemsLists(allItemsList: List<String>, featuredItemsList: List<String>) {
        this.allItemsList = allItemsList
        this.featuredItemsList = featuredItemsList
    }

    private fun setFeaturedItemsAdapter() {
        fragment.featured_items_recycler_view.adapter = FeaturedItemsAdapter(featuredItemsList, activity as Context)
    }

    private fun setAllItemsAdapter() {
        fragment.all_items_recycler_view.adapter = AllItemsAdapter(allItemsList, activity as Context)
    }
}