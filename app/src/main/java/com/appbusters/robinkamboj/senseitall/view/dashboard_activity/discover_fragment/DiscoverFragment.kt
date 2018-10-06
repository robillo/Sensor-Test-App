package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.adapter.popular_tests.PopTestsAdapter

import com.appbusters.robinkamboj.senseitall.utils.StartSnapHelper
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp.CardPagerAdapter
import kotlinx.android.synthetic.main.fragment_discover.view.*
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp.CardFragmentPagerAdapter
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp.ShadowTransformer
import android.widget.CompoundButton
import com.appbusters.robinkamboj.senseitall.model.recycler.Category
import com.appbusters.robinkamboj.senseitall.model.recycler.ToolsItem
import com.appbusters.robinkamboj.senseitall.utils.AppConstants.*
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.adapter.popular_tools.PopToolsAdapter
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp.CustPagerTransformer
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.adapter.image_tools.ImageToolsAdapter
import kotlinx.android.synthetic.main.fragment_tools.view.*


/**
 * A simple [Fragment] subclass.
 */
class DiscoverFragment : Fragment(), DiscoverInterface, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    lateinit var mCardAdapter: CardPagerAdapter
    lateinit var popToolsAdapter: ImageToolsAdapter
    lateinit var mCardShadowTransformer: ShadowTransformer
    lateinit var mFragmentCardAdapter: CardFragmentPagerAdapter
    lateinit var mFragmentCardShadowTransformer: ShadowTransformer

    var pop_tools_list: MutableList<ToolsItem> = ArrayList()
    var pop_tests_list: MutableList<Category> = ArrayList()

    lateinit var lv: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the lv for this fragment
        val v = inflater.inflate(R.layout.fragment_discover, container, false)
        setup(v)
        return v
    }

    override fun setup(v: View) {
        lv = v

        setToolsAdapter()
        setCategoriesAdapter()
        setViewPagerNewlyAdded()
    }


    override fun setToolsAdapter() {
        val list : List<String> = AppConstants.popTools
        list.forEach {
            pop_tools_list.add(ToolsItem(it, AppConstants.imageUrlMap.get(it)))
        }
        popToolsAdapter = ImageToolsAdapter(pop_tools_list, activity, 1)
        lv.tools_rv.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        lv.tools_rv.adapter = popToolsAdapter
        lv.tools_rv.onFlingListener = null
        StartSnapHelper().attachToRecyclerView(lv.tools_rv)
    }

    override fun setCategoriesAdapter() {
        val list : List<String> = AppConstants.popTests
        list.forEach {
            var type = 1
            when(it) {
                SENSOR_PROXIMITY, SENSOR_ACCELEROMETER -> type = 1
                MULTI_TOUCH, SCREEN, SOUND, FINGERPRINT, BATTERY, COMPASS -> type = 2
                LABEL_GENERATOR, VIRTUAL_REALITY -> type = 4
            }
            pop_tests_list.add(Category(AppConstants.imageUrlMap.get(it)!!, it, "", type))
        }
        val adapter = PopTestsAdapter(pop_tests_list, activity)
        lv.categories_rv.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        lv.categories_rv.adapter = adapter
        lv.categories_rv.onFlingListener = null
        StartSnapHelper().attachToRecyclerView(lv.categories_rv)
    }

    override fun setViewPagerNewlyAdded() {
        mCardAdapter = CardPagerAdapter()

        mCardAdapter.addCardItems(AppConstants.categories)

        mFragmentCardAdapter = CardFragmentPagerAdapter(activity!!.supportFragmentManager,
                dpToPixels(2, activity!!.baseContext))

        mCardShadowTransformer = ShadowTransformer(lv.viewPager, mCardAdapter)
        mFragmentCardShadowTransformer = ShadowTransformer(lv.viewPager, mFragmentCardAdapter)

        lv.viewPager.setAdapter(mCardAdapter)
        lv.viewPager.setPageTransformer(false, CustPagerTransformer(activity))
        lv.viewPager.setOffscreenPageLimit(3)

        lv.page_indicator.count = lv.viewPager.childCount
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
        mCardShadowTransformer.enableScaling(b)
        mFragmentCardShadowTransformer.enableScaling(b)
    }

    fun dpToPixels(dp: Int, context: Context): Float {
        return dp * context.getResources().getDisplayMetrics().density
    }


    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        lv.page_indicator.selection = position
    }
}
