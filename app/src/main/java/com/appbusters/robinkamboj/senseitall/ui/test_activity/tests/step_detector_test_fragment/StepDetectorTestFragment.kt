package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.step_detector_test_fragment


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import kotlinx.android.synthetic.main.fragment_step_detector_test.*

/**
 * A simple [Fragment] subclass.
 *
 */
class StepDetectorTestFragment : Fragment(), StepDetectorInterface, SensorEventListener {

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val sensor = event?.sensor
        val values = event?.values

        if (values != null && values.isNotEmpty()) {
            steps = values[0]
        }

        if (sensor != null && sensor.type == Sensor.TYPE_STEP_COUNTER) {
            steps_detected.text = steps.toInt().toString()
            distance_covered.text = getDistanceRun(steps.toLong()).toString()
        }
    }

    var steps : Float = 0f
    lateinit var sManager: SensorManager
    lateinit var stepSensor: Sensor

    override fun getDistanceRun(steps: Long): Float {
        return (steps * 78).toFloat() / 100000.toFloat()
    }

    override fun setup(v: View) {
        sManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the parentView for this fragment
        val v = inflater.inflate(R.layout.fragment_step_detector_test, container, false)
        setup(v)
        return v
    }

    override fun onResume() {
        super.onResume()
        sManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
    }

    override fun onPause() {
        super.onPause()
        sManager.unregisterListener(this, stepSensor)
    }
}
