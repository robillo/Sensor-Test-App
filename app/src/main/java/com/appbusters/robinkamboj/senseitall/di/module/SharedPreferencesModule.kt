package com.appbusters.robinkamboj.senseitall.di.module

import android.content.Context
import com.appbusters.robinkamboj.senseitall.di.scope.SiaApplicationScope
import com.appbusters.robinkamboj.senseitall.preferences.AppPreferencesHelper

import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class SharedPreferencesModule {

    @Provides
    @SiaApplicationScope
    fun sharedPreferencesHelper(context: Context): AppPreferencesHelper {
        return AppPreferencesHelper(context)
    }
}
