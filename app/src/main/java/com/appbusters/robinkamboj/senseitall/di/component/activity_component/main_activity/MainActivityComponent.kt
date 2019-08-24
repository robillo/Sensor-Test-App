package com.appbusters.robinkamboj.senseitall.di.component.activity_component.main_activity

import com.appbusters.robinkamboj.senseitall.di.component.SiaApplicationComponent
import com.appbusters.robinkamboj.senseitall.di.scope.PerFragmentScope
import com.appbusters.robinkamboj.senseitall.ui.main_activity.MainActivity
import dagger.Component

@PerFragmentScope
@Component(modules = [], dependencies = [SiaApplicationComponent::class])
interface MainActivityComponent {

    fun injectMainActivity(mainActivity: MainActivity)
}
