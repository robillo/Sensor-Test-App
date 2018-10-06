package com.appbusters.robinkamboj.senseitall.view.dashboard_activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.DiscoverFragment

import butterknife.ButterKnife
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.profile_fragment.ProfileFragment
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.ToolsFragment
import com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment.ListFragment
import com.appbusters.robinkamboj.senseitall.view.main_activity.request_permissons_fragment.RequestFragment
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.ArrayList

class DashboardActivity : AppCompatActivity(), DashboardInterface {

    lateinit var permissionsItems: MutableList<PermissionsItem>

    override fun setColorFilterToIcons(header: String) {

        header_text.text = header
        header_text.startAnimation(AnimationUtils.loadAnimation(this, R.anim.top_down))

        when (header) {
            HEADER_TOOLS -> {
                tools.setColorFilter(ContextCompat.getColor(this, R.color.primary_new))
                discover.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
                profile.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
            }
            HEADER_DISCOVER -> {
                tools.setColorFilter(ContextCompat.getColor(this, R.color.colorTextFour))
                discover.setColorFilter(ContextCompat.getColor(this, R.color.primary_new))
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

    override fun showSnackBar(text: String) {
        val snackbar = Snackbar.make(coordinator, text, 2000)
        val view = snackbar.view
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()
        refreshPermissionsRecycler()
    }

    fun refreshPermissionsRecycler() {
        val rFragment = supportFragmentManager.findFragmentByTag(getString(R.string.tag_request_fragment)) as RequestFragment? ?: return

        val rejectedCount = checkIfAllPermissionsGiven()
        rFragment.showRecycler(permissionsItems)
        rFragment.updatePendingCount(rejectedCount)
    }

    fun setRequestFragment() {

        val args = Bundle()
        args.putInt(AppConstants.FROM_ARG_IN_REQUEST, RequestFragment.FROM_DASHBOARD)

        val TAG = getString(R.string.tag_request_fragment)
        if (supportFragmentManager.findFragmentByTag(TAG) != null) return
        val fragment = RequestFragment()
        fragment.arguments = args
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_bottom_activity, R.anim.slide_out_right_activity)
        transaction.add(R.id.container, fragment, TAG)
        transaction.commit()
    }

    private fun checkIfAllPermissionsGiven(): Int {
        val permissionNames = AppConstants.dangerousPermissions
        permissionsItems = ArrayList()
        var rejectedCount = 0

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (p in permissionNames) {
                val b = checkSelfPermission(p) == PERMISSION_GRANTED
                permissionsItems.add(PermissionsItem(p, b))
                if (!b) rejectedCount++
            }
        }

        return rejectedCount
    }

    override fun onBackPressed() {

        val fragment: RequestFragment? = supportFragmentManager.findFragmentByTag(getString(R.string.tag_request_fragment)) as RequestFragment

        if(fragment != null) {
            val transaction =  supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_bottom_activity, R.anim.slide_out_right_activity)
                    .remove(fragment)
                    .commit()
            return
        }

        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(homeIntent)
    }
}
