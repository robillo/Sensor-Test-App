package com.appbusters.robinkamboj.senseitall.ui.main_activity

import android.content.Context
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.ui.main_activity.list_fragment.ListFragment
import com.appbusters.robinkamboj.senseitall.ui.main_activity.request_permissons_fragment.RequestFragment

import com.appbusters.robinkamboj.senseitall.di.component.activity_component.main_activity.DaggerMainActivityComponent
import com.appbusters.robinkamboj.senseitall.di.component.activity_component.main_activity.MainActivityComponent
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var activityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    fun setup() {
        activityComponent = DaggerMainActivityComponent.builder().build()
        activityComponent.injectMainActivity(this)

        setListFragment()

    }

    private fun setListFragment() {
        val fragmentTag = getString(R.string.tag_list_fragment)
        if (supportFragmentManager.findFragmentByTag(fragmentTag) != null) return

        val fragment = ListFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container, fragment, fragmentTag).commit()
    }

    fun setRequestFragment() {
        val fragmentTag = getString(R.string.tag_request_fragment)
        if (supportFragmentManager.findFragmentByTag(fragmentTag) != null) return

        val fragment = RequestFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_bottom_activity, R.anim.slide_out_right_activity)
        transaction.add(R.id.container, fragment, fragmentTag)
        transaction.commit()
    }

    fun refreshPermissionsRecycler() {
        val listFragment =
                supportFragmentManager.findFragmentByTag(getString(R.string.tag_list_fragment)) as ListFragment?

        val requestFragment =
                supportFragmentManager.findFragmentByTag(getString(R.string.tag_request_fragment)) as RequestFragment?

        if (listFragment == null || requestFragment == null) return

        val rejectedCount = listFragment.checkIfAllPermissionsGiven()
        requestFragment.showRecycler(listFragment.permissionItemsList)
        requestFragment.updatePendingCount(rejectedCount)
    }

    //required
    override fun onBackPressed() {
        val reqFragment = supportFragmentManager.findFragmentByTag(getString(R.string.tag_request_fragment))
        val listFragment = supportFragmentManager.findFragmentByTag(getString(R.string.tag_list_fragment)) as ListFragment?
        if (reqFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_bottom_activity, R.anim.slide_out_right_activity)
            transaction.remove(reqFragment)
            transaction.commit()
        } else if (listFragment != null && listFragment.isSearching) {
            listFragment.setSearch()
        } else {
            super.onBackPressed()
            overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity)
        }
    }

    //required
    override fun onResume() {
        super.onResume()
        refreshPermissionsRecycler()
    }

    //required
    private fun showSnackBar(text: String) {
        val snackBar = Snackbar.make(coordinatorLayout, text, 600)
        val view = snackBar.view
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackBar.show()
    }

    //required
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    //required
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        refreshPermissionsRecycler()

        if (requestCode == AppConstants.REQUEST_CODE) {
            var isPermissionGranted = true

            for (result in grantResults)
                isPermissionGranted = result == PackageManager.PERMISSION_GRANTED

            if (isPermissionGranted) showSnackBar(getString(R.string.permission_granted))
            else showSnackBar(getString(R.string.permission_denied))
        }
    }
}
