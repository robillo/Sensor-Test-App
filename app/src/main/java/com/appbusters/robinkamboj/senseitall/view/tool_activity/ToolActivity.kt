package com.appbusters.robinkamboj.senseitall.view.tool_activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.calculator.CalculatorFragment
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.internet_speed.InternetSpeedFragment
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.sound_level.SoundLevelFragment
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.timer.TimerFragment
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.volume_control.VolumeControlFragment
import com.nmaltais.calcdialog.CalcDialog
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_tool.*
import com.roomorama.caldroid.CaldroidFragment
import java.util.*


class ToolActivity : AppCompatActivity(), ToolsInterface {

    var toolDrawable: Int = 0
    lateinit var toolName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool)

        setStatusBarColor()
        setDataForIntent()
        setCorrespondingFragment()
    }

    override fun setStatusBarColor() {
        val window = window
        val view = window.decorView
        var flags = view.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }


    override fun setDataForIntent() {
        val args: Intent = intent!!
        toolName = args.getStringExtra(DATA_NAME)
        toolDrawable = args.getIntExtra(DRAWABLE_ID, R.drawable.baseline_clear_black_48)
        tool_name.text = toolName.toUpperCase()
    }

    override fun setCorrespondingFragment() {

        val transaction = supportFragmentManager.beginTransaction()

        when(toolName) {
            SQUARE_IMAGE -> {
            }
            CROP_IMAGE -> {
            }
            IMAGE_FILTERS -> {
            }
            EDIT_IMAGE -> {
            }
            INTERNET_SPEED -> {
                transaction
                        .add(
                                R.id.container,
                                InternetSpeedFragment(),
                                getString(R.string.tag_internet_speed_fragment)
                        )
                        .commit()
            }
            SET_ALARM -> {
            }
            TAKE_NOTE -> {
            }
            VOLUME_CONTROL -> {
                transaction
                        .add(
                                R.id.container,
                                VolumeControlFragment(),
                                getString(R.string.tag_volume_control_fragment)
                        )
                        .commit()
            }
            CALCULATOR -> {
//                transaction
//                        .add(
//                                R.id.container,
//                                CalculatorFragment(),
//                                getString(R.string.tag_calculator_fragment)
//                        )
//                        .commit()

                val calcDialog: CalcDialog = CalcDialog.newInstance(0)
                calcDialog.setValue(null)
                calcDialog.show(supportFragmentManager, getString(R.string.tag_calculator_fragment))
            }
            RECORD_AUDIO -> {
            }
            STOP_WATCH -> {
            }
            REMINDER -> {
            }
            CALENDAR -> {
                val caldroidFragment = CaldroidFragment()
                val args = Bundle()
                val cal = Calendar.getInstance()
                args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1)
                args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR))
                caldroidFragment.arguments = args

                transaction
                        .add(
                                R.id.container,
                                caldroidFragment,
                                getString(R.string.tag_calendar_fragment)
                        )
                        .commit()
            }
            CHECKLIST -> {
            }
            SOUND_LEVEL -> {
                transaction
                        .add(
                                R.id.container,
                                SoundLevelFragment(),
                                getString(R.string.tag_volume_control_fragment)
                        )
                        .commit()
            }
            TIMER -> {
                transaction
                        .add(
                                R.id.container,
                                TimerFragment(),
                                getString(R.string.tag_timer_fragment)
                        )
                        .commit()
            }
//            WEATHER -> {
//            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity)
    }

    override fun setTimerForTimer(hours: Int, mins: Int, secs: Int) {
        val fragment: TimerFragment =
                supportFragmentManager
                        .findFragmentByTag(getString(R.string.tag_timer_fragment)) as TimerFragment
        fragment.setInputForTimer(hours, mins, secs)
    }
}
