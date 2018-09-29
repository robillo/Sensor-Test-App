package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer


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
class TimerFragment : Fragment(), TimerInterface {

    lateinit var lv: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        lv = inflater.inflate(R.layout.fragment_timer, container, false)
        setup()
        return lv
    }

    override fun setup() {

    }

}
