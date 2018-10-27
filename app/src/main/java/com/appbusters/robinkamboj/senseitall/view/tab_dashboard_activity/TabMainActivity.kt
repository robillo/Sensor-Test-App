package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity

import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.category_header_adapter.CategoryHeaderAdapter
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.category_header_adapter.HeaderClickListener
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_tab_main.*

class TabMainActivity : AppCompatActivity() {

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
    }

    private fun inflateRecyclerViewHeaders() {
        getRecyclerViewHeaders()
        headerAdapter = CategoryHeaderAdapter(headersList, this, HeaderClickListener {

        })
        header_recycler_view.adapter = headerAdapter
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
