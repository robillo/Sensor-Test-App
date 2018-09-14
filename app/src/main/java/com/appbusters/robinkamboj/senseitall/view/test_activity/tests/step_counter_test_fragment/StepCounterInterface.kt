package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.step_counter_test_fragment

import android.view.View

interface StepCounterInterface {

    fun setup(v: View)

    fun getDistanceRun(steps: Long): Float

}
