package com.appbusters.robinkamboj.senseitall.di.module

import android.arch.lifecycle.ViewModelProvider
import com.appbusters.robinkamboj.senseitall.utils.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}