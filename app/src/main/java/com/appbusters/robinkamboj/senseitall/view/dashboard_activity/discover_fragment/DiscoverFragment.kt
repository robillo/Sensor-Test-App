package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.adapter.DashboardAdapter

import com.appbusters.robinkamboj.senseitall.utils.StartSnapHelper
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp.CardPagerAdapter
import kotlinx.android.synthetic.main.fragment_discover.view.*
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp.CardItem
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp.CardFragmentPagerAdapter
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp.ShadowTransformer
import kotlinx.android.synthetic.main.fragment_discover.*
import android.widget.CompoundButton




/**
 * A simple [Fragment] subclass.
 */
class DiscoverFragment : Fragment(), DiscoverInterface, CompoundButton.OnCheckedChangeListener {

    private var mShowingFragments = false
    lateinit var mCardAdapter: CardPagerAdapter
    lateinit var mCardShadowTransformer: ShadowTransformer
    lateinit var mFragmentCardAdapter: CardFragmentPagerAdapter
    lateinit var mFragmentCardShadowTransformer: ShadowTransformer

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

        setCategoriesAdapter()
        setViewPagerNewlyAdded()

    }

    override fun setCategoriesAdapter() {
        val adapter = DashboardAdapter(AppConstants.categories, activity)
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
        mCardAdapter.addCardItem(CardItem(R.string.title_home, R.string.title_dashboard))
        mCardAdapter.addCardItem(CardItem(R.string.title_dashboard, R.string.title_home))
        mCardAdapter.addCardItem(CardItem(R.string.title_home, R.string.title_dashboard))
        mCardAdapter.addCardItem(CardItem(R.string.title_dashboard, R.string.title_home))
        mFragmentCardAdapter = CardFragmentPagerAdapter(activity!!.supportFragmentManager,
                dpToPixels(2, activity!!.baseContext))

        mCardShadowTransformer = ShadowTransformer(lv.viewPager, mCardAdapter)
        mFragmentCardShadowTransformer = ShadowTransformer(lv.viewPager, mFragmentCardAdapter)

        lv.viewPager.setAdapter(mCardAdapter)
        lv.viewPager.setPageTransformer(false, mCardShadowTransformer)
        lv.viewPager.setOffscreenPageLimit(3)
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
        mCardShadowTransformer.enableScaling(b)
        mFragmentCardShadowTransformer.enableScaling(b)
    }

    fun dpToPixels(dp: Int, context: Context): Float {
        return dp * context.getResources().getDisplayMetrics().density
    }
}
