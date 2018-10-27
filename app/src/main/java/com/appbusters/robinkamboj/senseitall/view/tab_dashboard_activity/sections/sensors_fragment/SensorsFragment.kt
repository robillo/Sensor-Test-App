package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.sensors_fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R

/**
 * A simple [Fragment] subclass.
 *
 */
class SensorsFragment : Fragment() {

    lateinit var fragment: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragment = inflater.inflate(R.layout.fragment_sensors, container, false)
        performSetupOperations()
        return fragment
    }

    private fun performSetupOperations() {
        
    }
}
