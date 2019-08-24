package com.appbusters.robinkamboj.senseitall

import android.app.Application
import com.appbusters.robinkamboj.senseitall.di.component.DaggerSiaApplicationComponent
import com.appbusters.robinkamboj.senseitall.di.component.SiaApplicationComponent
import com.appbusters.robinkamboj.senseitall.di.module.ContextModule

import io.github.inflationx.viewpump.ViewPump
import javax.inject.Inject

class SensorApplication : Application() {

    lateinit var viewPump: ViewPump

    private lateinit var applicationComponent: SiaApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerSiaApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        viewPump = applicationComponent.viewPump()

        ViewPump.init(viewPump)
    }

    fun getApplicationComponent(): SiaApplicationComponent {
        return applicationComponent
    }
}
