package com.appbusters.robinkamboj.senseitall.ui.tool_activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.AlarmFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.calculator.calc.CustomCalcDialog
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.calculator.calc.CustomCalcDialog.newInstance
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.checklist.ChecklistFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.internet_speed.InternetSpeedFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.reminder.ReminderFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.sound_level.SoundLevelFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.take_note.NoteFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.take_note.note_input.NoteInputFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.timer.TimerFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.volume_control.VolumeControlFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.image_tools.crop.CropFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.image_tools.draw.DrawFragment
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.image_tools.filter.FilterFragment
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
            DRAW_NOTE -> {
                transaction
                        .add(
                                R.id.container,
                                DrawFragment(),
                                getString(R.string.tag_draw_fragment)
                        )
                        .commit()
            }
            SQUARE_IMAGE -> {
            }
            CROP_IMAGE -> {
                transaction
                        .add(
                                R.id.container,
                                CropFragment(),
                                getString(R.string.tag_crop_fragment)
                        )
                        .commit()
            }
            IMAGE_FILTERS -> {
                transaction
                        .add(
                                R.id.container,
                                FilterFragment(),
                                getString(R.string.tag_filter_fragment)
                        )
                        .commit()
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
                transaction
                        .add(
                                R.id.container,
                                AlarmFragment(),
                                getString(R.string.tag_alarm_fragment)
                        )
                        .commit()
            }
            TAKE_NOTE -> {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
                transaction
                        .add(
                                R.id.container,
                                NoteFragment(),
                                getString(R.string.tag_note_fragment)
                        )
                        .commit()
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
                val calcDialog: CustomCalcDialog = newInstance(0)
                calcDialog.setValue(null)
                calcDialog.show(supportFragmentManager, getString(R.string.tag_calculator_fragment))
            }
//            RECORD_AUDIO -> {
//
//            }
            STOP_WATCH -> {
            }
            REMINDER -> {
                transaction
                        .add(
                                R.id.container,
                                ReminderFragment(),
                                getString(R.string.tag_reminder_fragment)
                        )
                        .commit()
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
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
                transaction
                        .add(
                                R.id.container,
                                ChecklistFragment(),
                                getString(R.string.tag_checklist_fragment)
                        )
                        .commit()
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
            RECORD_AUDIO -> {

            }
//            WEATHER -> {
//
//            }
        }
    }

    override fun setNoteInputFragment(header: String, description: String, noteId: Int) {
        val transaction = supportFragmentManager.beginTransaction()

        val noteInputFragment = NoteInputFragment()

        val args = Bundle()
        args.putString(AppConstants.ARG_HEADING_NOTE, header)
        args.putString(AppConstants.ARG_DESCRIPTION_NOTE, description)
        args.putInt(AppConstants.ARG_ID_NOTE, noteId)

        noteInputFragment.arguments = args

        transaction
                .setCustomAnimations(
                        R.anim.slide_in_right_activity,
                        R.anim.slide_out_right_activity,
                        R.anim.slide_out_right_activity,
                        R.anim.slide_in_right_activity
                )
                .add(R.id.container, noteInputFragment, getString(R.string.tag_note_input_fragment))
                .commit()
    }

    override fun saveNoteItem(heading: String, description: String) {
        val fragment: NoteFragment? =
                supportFragmentManager.findFragmentByTag(getString(R.string.tag_note_fragment))
                        as NoteFragment

        if(fragment != null) {
            fragment.saveNoteToDb(heading, description)
        }
    }

    override fun saveEditedNote(heading: String, description: String, noteId: Int) {
        val fragment: NoteFragment? =
                supportFragmentManager.findFragmentByTag(getString(R.string.tag_note_fragment))
                        as NoteFragment

        if(fragment != null) {
            fragment.saveEditedNote(heading, description, noteId)
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {

        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(getString(R.string.tag_note_input_fragment))

        if(fragment != null) {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right_activity,
                            R.anim.slide_out_right_activity,
                            R.anim.slide_out_right_activity,
                            R.anim.slide_in_right_activity
                    )
                    .remove(fragment)
                    .commit()
            return
        }

        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity)
    }

    override fun setTimerForTimer(hours: Int, mins: Int, secs: Int) {
        val fragment: TimerFragment =
                supportFragmentManager
                        .findFragmentByTag(getString(R.string.tag_timer_fragment)) as TimerFragment
        fragment.setInputForTimer(hours, mins, secs)
    }

    override fun editNote(header: String, description: String, noteId: Int) {
        setNoteInputFragment(header, description, noteId)
    }

    override fun deleteNoteById(noteId: Int) {
        val fragment: NoteFragment? =
                supportFragmentManager.findFragmentByTag(getString(R.string.tag_note_fragment))
                        as NoteFragment

        if(fragment != null) fragment.deleteNoteById(noteId)
    }

    override fun markCheckAsDoneById(isDone: Boolean, id: Int) {
        val fragment: ChecklistFragment? =
                supportFragmentManager.findFragmentByTag(getString(R.string.tag_checklist_fragment))
                        as ChecklistFragment

        if(fragment != null) fragment.markAsDoneById(isDone, id)
    }

    override fun deleteCheckById(id: Int) {
        val fragment: ChecklistFragment? =
                supportFragmentManager.findFragmentByTag(getString(R.string.tag_checklist_fragment))
                        as ChecklistFragment

        if(fragment != null) fragment.deleteCheckById(id)
    }

    override fun showCoordinator(text: String) {
        val snackbar = Snackbar.make(coordinator_layout, text, 1000)
        val view = snackbar.view
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackbar.show()
    }
}
