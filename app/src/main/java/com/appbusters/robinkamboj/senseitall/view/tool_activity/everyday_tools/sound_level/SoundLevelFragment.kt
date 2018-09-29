package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.sound_level


import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import kotlinx.android.synthetic.main.fragment_sound_level.*
import kotlinx.android.synthetic.main.fragment_sound_level.view.*
import java.text.DecimalFormat

/**
 * A simple [Fragment] subclass.
 *
 */
class SoundLevelFragment : Fragment(), SoundLevelInterface {

    private var decimalFormat = DecimalFormat("0.0")
    private var mHandler = Handler()
    lateinit var mTimer: Runnable
    lateinit var recorder: MediaRecorder
    lateinit var lv: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_sound_level, container, false)
        lv = v
        lv.pointer_speedometer.maxSpeed = 32767.toFloat()
        setup()
        return v
    }

    override fun setup() {

    }

    override fun onResume() {
        super.onResume()

        mTimer = Runnable {
            kotlin.run {
                try {
                    lv.pointer_speedometer.speedTo(getAmplitude(), 500)
                    lv.percent_sound
                            .text = ""
                            .plus(
                                decimalFormat.format(pointer_speedometer.speed * 100 / pointer_speedometer.maxSpeed)
                            )
                } catch (ignored: Exception) {

                }
                mHandler.postDelayed(mTimer, 200)
            }
        }
        mHandler.postDelayed(mTimer, 1000)
    }

    override fun onPause() {
        mHandler.removeCallbacks(mTimer)
        super.onPause()
    }

    override fun onStart() {
        super.onStart()

        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        recorder.setOutputFile("/dev/null")
        recorder.prepare()
        recorder.start()
    }

    override fun onStop() {
        recorder.stop()
        recorder.release()
        super.onStop()
    }

    fun getAmplitude(): Float {
        return recorder.maxAmplitude.toFloat()
    }
}
