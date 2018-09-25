package com.appbusters.robinkamboj.senseitall.view.dashboard_activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.DiscoverFragment

import butterknife.ButterKnife
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.profile_fragment.ProfileFragment
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.ToolsFragment
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), DashboardInterface {

    override fun setColorFilterToIcons(header: String) {

        header_text.text = header

        when (header) {
            HEADER_TOOLS -> {
                tools.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne))
                discover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
                profile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
            }
            HEADER_DISCOVER -> {
                tools.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
                discover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne))
                profile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
            }
            HEADER_PERSONALIZED -> {
                tools.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
                discover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
                profile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextOne))
            }
        }
    }

    override fun setClickListeners() {
        tools.setOnClickListener { setToolsFragment() }
        discover.setOnClickListener { setDiscoverFragment() }
        profile.setOnClickListener { setPersonalizedFragment() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setup()
    }

    override fun setup() {
        ButterKnife.bind(this)

        changeStatusBarColor()

        setClickListeners()

        setDiscoverFragment()
    }

    override fun changeStatusBarColor() {
        val window = window
        val view = window.decorView
        var flags = view.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }

    override fun setPersonalizedFragment() {
        if(header_text.text.equals(HEADER_PERSONALIZED)) return

        setColorFilterToIcons(HEADER_PERSONALIZED)

        supportFragmentManager
                .beginTransaction()
                .replace(
                        R.id.container,
                        ProfileFragment(),
                        getString(R.string.tag_profile_fragment)
                )
                .commit()
    }

    override fun setDiscoverFragment() {
        if(header_text.text.equals(HEADER_DISCOVER)) return

        setColorFilterToIcons(HEADER_DISCOVER)

        supportFragmentManager
                .beginTransaction()
                .replace(
                        R.id.container,
                        DiscoverFragment(),
                        getString(R.string.tag_discover_fragment)
                )
                .commit()
    }

    override fun setToolsFragment() {
        if(header_text.text.equals(HEADER_TOOLS)) return

        setColorFilterToIcons(HEADER_TOOLS)

        supportFragmentManager
                .beginTransaction()
                .replace(
                        R.id.container,
                        ToolsFragment(),
                        getString(R.string.tag_tools_fragment)
                )
                .commit()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(homeIntent)
    }
}
