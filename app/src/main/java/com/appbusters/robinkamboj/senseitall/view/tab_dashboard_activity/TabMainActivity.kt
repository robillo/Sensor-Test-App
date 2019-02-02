package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity

import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.di.component.activity_component.tab_main_activity.DaggerTabMainActivityComponent
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.category_header_adapter.CategoryHeaderAdapter
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.category_header_adapter.HeaderClickListener
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections_view_pager.SectionsPagerAdapter
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_tab_main.*

class TabMainActivity : AppCompatActivity(), HeaderClickListener {

    private var selectedTabIndex = 0
    private lateinit var tabHeaderAdapter: CategoryHeaderAdapter

    companion object {
        val tabHeadersList = listOf(
                SENSOR_HEADER,
                FEATURE_HEADER,
                TRENDING_HEADER,
                TOOLS_HEADER,
                DEVICE_HEADER,
                ANDROID_HEADER
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_main)

        performSetup()
    }

    private fun performSetup() {
        initDagger()
        setStatusBarColor()
        inflateRecyclerView()
        setViewPagerForHeadersList()
        setViewPagerChangeListener()
    }

    private fun initDagger() {
        val tabMainActivityComponent = DaggerTabMainActivityComponent.builder().build()
        tabMainActivityComponent.injectTabMainActivity(this)
    }

    private fun setStatusBarColor() {
        var flags = window.decorView.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setWindowFlags(flags)
    }

    private fun inflateRecyclerView() {
        tabHeaderAdapter = CategoryHeaderAdapter(tabHeadersList, selectedTabIndex, this, this)
        header_recycler_view.adapter = tabHeaderAdapter
    }

    override fun handleHeaderClicked(header: String) {
        selectedTabIndex = tabHeadersList.indexOf(header)
        setFragmentForSelectedTab()
        refreshRecyclerForNewHeaderSelected(selectedTabIndex)
    }

    private fun setViewPagerChangeListener() {
        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                selectedTabIndex = position
                refreshRecyclerForNewHeaderSelected(selectedTabIndex)
                header_recycler_view.smoothScrollToPosition(selectedTabIndex)
            }
        })
    }

    private fun refreshRecyclerForNewHeaderSelected(selectedIndex: Int) {
        tabHeaderAdapter.refreshForNewItemSelected(selectedIndex)
    }

    private fun setFragmentForSelectedTab() {
        view_pager.currentItem = selectedTabIndex
    }

    private fun setViewPagerForHeadersList() {
        view_pager.adapter = SectionsPagerAdapter(supportFragmentManager, tabHeadersList)
    }

    private fun setWindowFlags(flags: Int) {
        window.decorView.systemUiVisibility = flags
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    fun showSnackBar(text: String) {
        val snackBar = Snackbar.make(coordinator, text, 2000)
        val view = snackBar.view
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackBar.show()
    }
}
