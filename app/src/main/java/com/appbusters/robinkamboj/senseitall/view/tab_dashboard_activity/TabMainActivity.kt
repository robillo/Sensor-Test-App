package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity

import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.view.WindowManager
import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.category_header_adapter.CategoryHeaderAdapter
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.category_header_adapter.HeaderClickListener
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections_view_pager.SectionsPagerAdapter
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_tab_main.*

class TabMainActivity : AppCompatActivity() {

    var selectedIndex = 0
    lateinit var headersList: List<String>
    lateinit var headerAdapter: CategoryHeaderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_main)

        performSetupOperations()
    }

    private fun performSetupOperations() {
        setStatusBarColor()
        inflateRecyclerViewHeaders()
        setViewPagerForHeadersList()
        setViewPagerChangeListener()
    }

    private fun inflateRecyclerViewHeaders() {
        getRecyclerViewHeaders()
        headerAdapter = CategoryHeaderAdapter(headersList, selectedIndex, this, HeaderClickListener {
            selectedIndex = headersList.indexOf(it)
            setHeaderFragment()
            refreshRecyclerForNewHeaderSelected(selectedIndex)
        })
        header_recycler_view.adapter = headerAdapter
    }

    private fun setViewPagerChangeListener() {
        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                selectedIndex = position
                refreshRecyclerForNewHeaderSelected(selectedIndex)
                header_recycler_view.smoothScrollToPosition(selectedIndex)
            }
        })
    }

    private fun refreshRecyclerForNewHeaderSelected(selectedIndex: Int) {
        headerAdapter.refreshForNewItemSelected(selectedIndex)
    }

    private fun setHeaderFragment() {
        view_pager.currentItem = selectedIndex
    }

    private fun setViewPagerForHeadersList() {
        view_pager.adapter = SectionsPagerAdapter(supportFragmentManager, headersList)
    }

    private fun getRecyclerViewHeaders() {
        headersList = AppConstants.categoryNames
    }

    private fun setStatusBarColor() {
        var flags = window.decorView.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setWindowFlags(flags)
    }

    private fun setWindowFlags(flags: Int) {
        window.decorView.systemUiVisibility = flags
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }
}
