package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.step_detector_test_fragment


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
class StepDetectorTestFragment : Fragment(), StepDetectorInterface {

    override fun setup(v: View) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_step_detector_test, container, false)
        setup(v)
        return v
    }


}
