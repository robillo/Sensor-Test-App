package com.appbusters.robinkamboj.senseitall.di.component.activity_component.tab_main_activity

import com.appbusters.robinkamboj.senseitall.di.component.SiaApplicationComponent
import com.appbusters.robinkamboj.senseitall.di.scope.PerFragmentScope
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.TabMainActivity
import dagger.Component

@PerFragmentScope
@Component(modules = [], dependencies = [SiaApplicationComponent::class])
interface TabMainActivityComponent {

    fun injectTabMainActivity(tabMainActivity: TabMainActivity)
}