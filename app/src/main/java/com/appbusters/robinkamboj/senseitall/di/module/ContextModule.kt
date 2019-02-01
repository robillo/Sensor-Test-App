package com.appbusters.robinkamboj.senseitall.di.module

import android.content.Context
import com.appbusters.robinkamboj.senseitall.di.scope.SiaApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @Provides
    @SiaApplicationScope
    fun context(): Context {
        return context
    }
}
