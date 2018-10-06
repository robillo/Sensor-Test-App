package com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.draw


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
class DrawFragment : Fragment(), DrawInterface {

    lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_draw, container, false)
        setup()
        return v
    }

    override fun setup() {
    }

}
