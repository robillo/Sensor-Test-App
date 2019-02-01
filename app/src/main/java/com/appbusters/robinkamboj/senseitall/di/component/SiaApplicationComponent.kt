package com.appbusters.robinkamboj.senseitall.di.component

import com.appbusters.robinkamboj.senseitall.di.module.CalligraphyModule
import com.appbusters.robinkamboj.senseitall.di.module.SharedPreferencesModule
import com.appbusters.robinkamboj.senseitall.di.scope.SiaApplicationScope
import com.appbusters.robinkamboj.senseitall.preferences.AppPreferencesHelper

import dagger.Component
import io.github.inflationx.viewpump.ViewPump

@SiaApplicationScope
@Component(modules = [
    CalligraphyModule::class,
    SharedPreferencesModule::class
])
interface SiaApplicationComponent {

    fun viewPump(): ViewPump

    fun getSharedPreferencesHelper(): AppPreferencesHelper

}
