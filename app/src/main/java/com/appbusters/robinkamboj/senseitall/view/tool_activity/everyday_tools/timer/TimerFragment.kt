package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer


import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer.utils.TimerInputSheet
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.main.fragment_timer.view.*
import kotlin.math.min

/**
 * A simple [Fragment] subclass.
 *
 */
class TimerFragment : Fragment(), TimerInterface {

    private var isAlreadySet: Boolean = false
    private var isAlreadyPlaying: Boolean = false
    private var hours: Int = 0
    private var mins: Int = 0
    private var secs: Int = 0
    private var hoursTick: Int = 0
    private var minsTick: Int = 0
    private var secsTick: Int = 0
    lateinit var lv: View
    lateinit var countDownTimer: CountDownTimer
    lateinit var timerSheet: TimerInputSheet

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        lv = inflater.inflate(R.layout.fragment_timer, container, false)
        setup()
        return lv
    }

    override fun setup() {
        setClickListeners()
        updateMainTextsForTime(hours, mins, secs)
    }

    override fun showCoordinator(text: String) {
        val snackbar = Snackbar.make(lv.coordinator, text, 1000)
        val view = snackbar.getView()
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackbar.show()
    }

    override fun startTimer() {
        isAlreadyPlaying = true
        countDownTimer.start()
    }

    override fun stopTimer() {
        isAlreadyPlaying = false
        countDownTimer.cancel()
    }

    override fun resetTimer() {
        hoursTick = hours
        minsTick = mins
        secsTick = secs
        stopTimer()
        updateMainTextsForTime(hoursTick, minsTick, secsTick)
    }

    override fun setClickListeners() {
        lv.play.setOnClickListener {
            if(!isAlreadySet) {
                showCoordinator("please set the timer first")
                return@setOnClickListener
            }
            if(isAlreadyPlaying) {
                showCoordinator("timer already running")
                return@setOnClickListener
            }
            startTimer()
            updateIconTints(0)
        }
        lv.pause.setOnClickListener {
            if(!isAlreadySet) {
                showCoordinator("please set the timer first")
                return@setOnClickListener
            }
            if(!isAlreadyPlaying) {
                showCoordinator("timer is not running")
                return@setOnClickListener
            }
            stopTimer()
            updateIconTints(1)
        }
        lv.reset.setOnClickListener {
            if(!isAlreadySet) {
                showCoordinator("please set the timer first")
                return@setOnClickListener
            }
            if(isAlreadyPlaying) {
                showCoordinator("please stop the timer first")
                return@setOnClickListener
            }
            resetTimer()
            updateIconTints(2)
        }
        lv.set_timer.setOnClickListener { _ ->
            if(isAlreadySet) {
                val snackbar =
                        Snackbar.make(
                                lv.coordinator,
                                "Do you want to delete the current timer to set a new one.",
                                Snackbar.LENGTH_LONG
                        ).setAction("Yes") { takeInputForTimer() }.show()
            }
            else takeInputForTimer()
        }
    }

    override fun takeInputForTimer() {
        timerSheet = TimerInputSheet()
        timerSheet.show(activity!!.supportFragmentManager, activity!!.getString(R.string.tag_timer_sheet))
    }

    override fun setInputForTimer(hours: Int, mins: Int, secs: Int) {
        isAlreadySet = true
        timerSheet.dismiss()
        showCoordinator("a new timer has been set")

        this.hours = hours
        this.mins = mins
        this.secs = secs

        this.hoursTick = hours
        this.minsTick = mins
        this.secsTick = secs

        updateMainTextsForTime(hoursTick, minsTick, secsTick)
        countDownTimer = object : CountDownTimer(timeInMillis(hoursTick, minsTick, secsTick), 1000) {
            override fun onFinish() {
                stopTimer()
                showCoordinator("timer complete")
            }

            override fun onTick(millisUntilFinished: Long) {
                refreshMainTextsForTick()
            }
        }
    }

    override fun refreshMainTextsForTick() {
        secsTick -= secsTick
        if(secsTick == 0) {
            secsTick = 59
            minsTick -= minsTick
        }
        if(minsTick == 0) {
            minsTick == 59
            hoursTick -= hoursTick
        }
        updateMainTextsForTime(hoursTick, minsTick, secsTick)
    }

    override fun timeInMillis(hh: Int, mm: Int, ss: Int): Long {
        return ((hh*60*60 + mm*60 + ss)*1000).toLong()
    }

    override fun updateMainTextsForTime(hh: Int, mm: Int, ss: Int) {
        lv.hours_main.text = formatTimeToDisplay(hh)
        lv.mins_main.text = formatTimeToDisplay(mm)
        lv.secs_main.text = formatTimeToDisplay(ss)
    }

    override fun formatTimeToDisplay(value: Int): String {
        if(value < 10) return "0".plus(value)
        else return "".plus(value)
    }

    override fun updateIconTints(index: Int) {
        when(index) {
            0 -> {
                lv.play.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
                lv.pause.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextOne))
                lv.reset.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextOne))
            }
            1 -> {
                lv.play.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextOne))
                lv.pause.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
                lv.reset.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextOne))
            }
            2 -> {
                lv.play.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextOne))
                lv.pause.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
                lv.reset.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
            }
        }
    }

}
