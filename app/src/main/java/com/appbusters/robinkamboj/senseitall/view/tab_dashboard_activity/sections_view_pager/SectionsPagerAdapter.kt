package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections_view_pager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*

import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.generic_fragment.GenericFragment
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.items_fragment.ItemsFragment

class SectionsPagerAdapter(fm: FragmentManager, private val headersList: List<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val args = Bundle()
        when (headersList[position]) {
            SENSOR_HEADER -> args.putInt(TYPE, TYPE_SENSORS)
            FEATURE_HEADER -> args.putInt(TYPE, TYPE_FEATURES)
            TOOLS_HEADER -> args.putInt(TYPE, TYPE_TOOLS)
            TRENDING_HEADER -> args.putInt(TYPE, TYPE_TRENDING)
            DEVICE_HEADER -> args.putInt(TYPE, TYPE_DEVICE)
            ANDROID_HEADER -> args.putInt(TYPE, TYPE_ANDROID)
            else -> args.putInt(TYPE, TYPE_SENSORS)
        }
        val itemsFragment = ItemsFragment()
        itemsFragment.arguments = args
        return itemsFragment
    }

    override fun getCount(): Int {
        return headersList.size
    }
}
