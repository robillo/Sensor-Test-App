package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.db.AlarmDao
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.db.AlarmDatabase
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.alarm.db.AlarmPresenter

/**
 * A simple [Fragment] subclass.
 *
 */
class AlarmFragment : Fragment(), AlarmInterface {

    lateinit var v: View
    lateinit var alarmDatabase: AlarmDatabase
    lateinit var alarmDao: AlarmDao
    lateinit var alarmPresenter: AlarmPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_alarm, container, false)
        setup()
        return v
    }

    override fun setup() {
        alarmDatabase = AlarmDatabase.getDatabaseInstance(context)
        alarmDao = alarmDatabase.getAlarmDao()
        alarmPresenter = AlarmPresenter(alarmDao)
    }

}
