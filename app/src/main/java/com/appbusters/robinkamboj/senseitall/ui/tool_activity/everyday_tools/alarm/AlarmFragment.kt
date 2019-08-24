package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm

import android.app.TimePickerDialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.adapter.AlarmAdapter
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.db.Alarm
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.db.AlarmDao
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.db.AlarmDatabase
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.db.AlarmPresenter
import kotlinx.android.synthetic.main.fragment_alarm.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AlarmFragment : Fragment(), AlarmInterface, TimePickerDialog.OnTimeSetListener {

    private lateinit var v: View
    private lateinit var alarmDatabase: AlarmDatabase
    private lateinit var alarmDao: AlarmDao
    private lateinit var alarmPresenter: AlarmPresenter
    private lateinit var alarmData: LiveData<MutableList<Alarm>>
    private lateinit var timePicker: TimePickerDialog
    private var is24HourFormat = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_alarm, container, false)
        setup()
        return v
    }

    override fun setup() {
        getDbInstances()
        initialize()
        displayAllAlarms()
        setClickListeners()
    }

    override fun initialize() {
        v.recycler.layoutManager = LinearLayoutManager(activity)
    }

    override fun getDbInstances() {
        alarmDatabase = AlarmDatabase.getDatabaseInstance(context)
        alarmDao = alarmDatabase.getAlarmDao()
        alarmPresenter = AlarmPresenter(alarmDao)
    }

    override fun displayAllAlarms() {
        alarmData = alarmPresenter.allAlarms

        alarmData.observe(this, Observer {
            v.recycler.adapter = AlarmAdapter(it, activity)
        })
    }

    override fun setClickListeners() {
        v.add_alarm.setOnClickListener {
            showTimePicker()
        }
    }

    override fun showTimePicker() {
        val cal: Calendar = Calendar.getInstance()

        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        timePicker = TimePickerDialog(activity, this,
                hour,
                minute,
                is24HourFormat
        )

        timePicker.show()
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {

    }

}
