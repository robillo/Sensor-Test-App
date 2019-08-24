package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.step_detector_test_fragment

import android.view.View

interface StepDetectorInterface {

    fun setup(v: View)
    fun getDistanceRun(steps: Long): Float
}
