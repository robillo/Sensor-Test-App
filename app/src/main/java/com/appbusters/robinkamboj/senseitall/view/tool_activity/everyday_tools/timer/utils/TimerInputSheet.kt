package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer.utils

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.tool_activity.ToolActivity
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog.view.*


class TimerInputSheet : BottomSheetDialogFragment(), TimerInputInterface {

    lateinit var lv: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lv = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
        setup()
        return lv
    }

    override fun setup() {
        setClickListeners()
    }

    override fun setClickListeners() {
        lv.submit_timer.setOnClickListener {
            if(lv.hours_text.text.isEmpty() || lv.minutes_text.text.isEmpty() || lv.seconds_text.text.isEmpty()) {
                showCoordinator("please input timer details")
                return@setOnClickListener
            }

            val toolActivity: ToolActivity = activity as ToolActivity
            toolActivity.setTimerForTimer(
                    lv.hours_text.text.toString().toInt(),
                    lv.minutes_text.text.toString().toInt(),
                    lv.seconds_text.text.toString().toInt()
            )
        }
        lv.hours_text.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.length == 1 && Integer.parseInt(s.toString()) > 2) {
                    lv.hours_text.setText("0".plus(s))
                    lv.hours_text.setSelection(lv.hours_text.text.toString().length)
                    setWarning("HOURS can not be more than 23")
                }
                if(s.length == 2 && Integer.parseInt(s[0].toString()) == 2 && Integer.parseInt(s[1].toString()) > 3) {
                    lv.hours_text.setText(s[0].toString().plus(3))
                    lv.hours_text.setSelection(lv.hours_text.text.toString().length)
                    setWarning("HOURS can not be more than 23")
                }
            }
        })
        lv.minutes_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.length == 1 && Integer.parseInt(s.toString()) > 5) {
                    lv.minutes_text.setText("0".plus(s))
                    lv.minutes_text.setSelection(lv.hours_text.text.toString().length)
                    setWarning("MINUTES can not be more than 59")
                }
            }
        })
        lv.seconds_text.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.length == 1 && Integer.parseInt(s.toString()) > 5) {
                    lv.seconds_text.setText("0".plus(s))
                    lv.seconds_text.setSelection(lv.hours_text.text.toString().length)
                    setWarning("SECONDS can not be more than 23")
                }
            }
        })
    }

    override fun setWarning(text: String) {
        lv.warning.text = text
        lv.warning.visibility = View.VISIBLE
        Handler().postDelayed({
            lv.warning.visibility = View.INVISIBLE
        }, 1000)
    }

    override fun showCoordinator(text: String) {
        val snackbar = Snackbar.make(lv.coordinator, text, 1000)
        val view = snackbar.getView()
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackbar.show()
    }
}
