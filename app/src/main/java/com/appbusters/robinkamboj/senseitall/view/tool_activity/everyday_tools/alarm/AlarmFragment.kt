package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.adapter.AlarmAdapter
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.db.Alarm
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.db.AlarmDao
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.db.AlarmDatabase
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.db.AlarmPresenter
import kotlinx.android.synthetic.main.fragment_alarm.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AlarmFragment : Fragment(), AlarmInterface {

    lateinit var v: View
    lateinit var alarmDatabase: AlarmDatabase
    lateinit var alarmDao: AlarmDao
    lateinit var alarmPresenter: AlarmPresenter
    lateinit var adapter: AlarmAdapter
    lateinit var alarmData: LiveData<MutableList<Alarm>>

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
    }

    override fun initialize() {
        recycler.layoutManager = LinearLayoutManager(activity)
    }

    override fun getDbInstances() {
        alarmDatabase = AlarmDatabase.getDatabaseInstance(context)
        alarmDao = alarmDatabase.getAlarmDao()
        alarmPresenter = AlarmPresenter(alarmDao)
    }

    override fun displayAllAlarms() {
        alarmData = alarmPresenter.allAlarms

        alarmData.observe(this, Observer {
            recycler.adapter = AlarmAdapter(it, activity)
        })
    }

}
