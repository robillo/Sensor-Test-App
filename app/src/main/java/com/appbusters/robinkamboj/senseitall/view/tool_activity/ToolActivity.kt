package com.appbusters.robinkamboj.senseitall.view.tool_activity

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import kotlinx.android.synthetic.main.activity_tool.*

class ToolActivity : AppCompatActivity(), ToolsInterface {

    var toolDrawable: Int = 0
    lateinit var toolName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool)

        setStatusBarColor()
        setDataForIntent()
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
            }
            SET_ALARM -> {
            }
            TAKE_NOTE -> {
            }
            VOLUME_CONTROL -> {
            }
            CALCULATOR -> {
            }
            RECORD_AUDIO -> {
            }
            STOP_WATCH -> {
            }
            REMINDER -> {
            }
            CALENDAR -> {
            }
            CHECKLIST -> {
            }
            WEATHER -> {
            }
            SOUND_LEVEL -> {
            }
            TIMER -> {
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity)
    }
}
