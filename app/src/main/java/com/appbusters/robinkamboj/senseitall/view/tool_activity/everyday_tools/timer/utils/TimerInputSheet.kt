package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer.utils

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.tool_activity.ToolActivity
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog.view.*


class TimerInputSheet : BottomSheetDialogFragment(), TimerInputInterface, TextWatcher {

    lateinit var lv: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lv = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
        setup()
        return lv;
    }

    override fun setup() {
        setClickListeners()
    }

    override fun setClickListeners() {
        lv.submit_timer.setOnClickListener {
            if(lv.hours_text.text.length == 0 || lv.minutes_text.text.length == 0 || lv.seconds_text.text.length == 0) {
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
        hours_text.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun showCoordinator(text: String) {
        val snackbar = Snackbar.make(lv.coordinator, text, 1000)
        val view = snackbar.getView()
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackbar.show()
    }
}
