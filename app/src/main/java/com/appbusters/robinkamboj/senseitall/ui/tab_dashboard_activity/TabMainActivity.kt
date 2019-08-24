package com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.view.ViewPager
import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.SensorApplication
import com.appbusters.robinkamboj.senseitall.di.component.activity_component.tab_main_activity.DaggerTabMainActivityComponent
import com.appbusters.robinkamboj.senseitall.ui.base.BaseActivity
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity.category_header_adapter.CategoryHeaderAdapter
import com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity.category_header_adapter.HeaderClickListener
import com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity.sections_view_pager.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_tab_main.*
import javax.inject.Inject

class TabMainActivity : BaseActivity(), HeaderClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var selectedTabIndex = 0
    private lateinit var tabHeaderAdapter: CategoryHeaderAdapter
    private lateinit var tabMainViewModel: TabMainViewModel

    companion object {
        val tabHeadersList = listOf(
                TOOLS_HEADER,
                SENSOR_HEADER,
                FEATURE_HEADER,
                TRENDING_HEADER
        )
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_tab_main
    }

    override fun setup() {
        initDagger()
        initViewModel()
        setLightStatusBar()
        inflateRecyclerView()
        setViewPagerAdapter()
        setViewPagerChangeListener()
    }

    private fun initDagger() {
        val tabMainActivityComponent = DaggerTabMainActivityComponent.builder()
                .siaApplicationComponent((applicationContext as SensorApplication).getApplicationComponent())
                .build()
        tabMainActivityComponent.injectTabMainActivity(this)
    }

    private fun initViewModel() {
        tabMainViewModel = ViewModelProviders.of(this, viewModelFactory).get(TabMainViewModel::class.java)
    }

    private fun inflateRecyclerView() {
        tabHeaderAdapter = CategoryHeaderAdapter(tabHeadersList, selectedTabIndex, this, this)
        header_recycler_view.adapter = tabHeaderAdapter
    }

    override fun handleHeaderClicked(header: String) {
        setFragmentForTab(tabHeadersList.indexOf(header))
    }

    private fun setViewPagerChangeListener() {
        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                setFragmentForTab(position)
            }
        })
    }

    private fun setFragmentForTab(selectedTabIndex: Int) {
        view_pager.currentItem = selectedTabIndex
        tabHeaderAdapter.refreshForNewItemSelected(selectedTabIndex)
        header_recycler_view.smoothScrollToPosition(selectedTabIndex)
    }

    private fun setViewPagerAdapter() {
        view_pager.adapter = SectionsPagerAdapter(supportFragmentManager, tabHeadersList)
    }
}