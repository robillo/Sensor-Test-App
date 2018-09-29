package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import kotlinx.android.synthetic.main.fragment_timer.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TimerFragment : Fragment(), TimerInterface {

    private var hours: Int = 0
    private var mins: Int = 0
    private var secs: Int = 0
    lateinit var lv: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        lv = inflater.inflate(R.layout.fragment_timer, container, false)
        setup()
        return lv
    }

    override fun setup() {
        setClickListeners()
    }

    override fun showCoordinator(text: String) {
        val snackbar = Snackbar.make(lv.coordinator, text, 1000)
        val view = snackbar.getView()
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackbar.show()
    }

    override fun setClickListeners() {
        lv.play.setOnClickListener {
            if(hours == 0 && mins == 0 && secs == 0) {
                showCoordinator("please set the timer first")
                return@setOnClickListener
            }
            updateIconTints(0)
        }
        lv.pause.setOnClickListener {
            if(hours == 0 && mins == 0 && secs == 0) {
                showCoordinator("please set the timer first")
                return@setOnClickListener
            }
            updateIconTints(1)
        }
        lv.reset.setOnClickListener {
            if(hours == 0 && mins == 0 && secs == 0) {
                showCoordinator("please set the timer first")
                return@setOnClickListener
            }
            updateIconTints(2)
        }
    }

    override fun updateIconTints(index: Int) {
        when(index) {
            0 -> {
                lv.play.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextOne))
                lv.pause.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
                lv.reset.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
            }
            1 -> {
                lv.play.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
                lv.pause.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextOne))
                lv.reset.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
            }
            2 -> {
                lv.play.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
                lv.pause.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextFour))
                lv.reset.setColorFilter(ContextCompat.getColor(context!!, R.color.colorTextOne))
            }
        }
    }

}
