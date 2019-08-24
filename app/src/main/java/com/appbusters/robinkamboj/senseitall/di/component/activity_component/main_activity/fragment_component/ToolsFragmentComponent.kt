package com.appbusters.robinkamboj.senseitall.di.component.activity_component.main_activity.fragment_component

import com.appbusters.robinkamboj.senseitall.di.component.SiaApplicationComponent
import com.appbusters.robinkamboj.senseitall.di.module.ContextModule
import com.appbusters.robinkamboj.senseitall.di.module.SharedPreferencesModule
import com.appbusters.robinkamboj.senseitall.di.scope.PerFragmentScope
import com.appbusters.robinkamboj.senseitall.ui.dashboard_activity.tools_fragment.ToolsFragment
import dagger.Component

@PerFragmentScope
@Component(modules = [SharedPreferencesModule::class, ContextModule::class],
        dependencies = [SiaApplicationComponent::class]
)
interface ToolsFragmentComponent {

    fun injectToolsFragment(toolsFragment: ToolsFragment)
}