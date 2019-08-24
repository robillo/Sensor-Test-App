package com.appbusters.robinkamboj.senseitall.di.module

import android.arch.lifecycle.ViewModel
import com.appbusters.robinkamboj.senseitall.di.map_key.ViewModelKey
import com.appbusters.robinkamboj.senseitall.ui.tab_dashboard_activity.TabMainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TabMainViewModel::class)
    abstract fun bindExamViewModel(tabMainViewModel: TabMainViewModel): ViewModel

}