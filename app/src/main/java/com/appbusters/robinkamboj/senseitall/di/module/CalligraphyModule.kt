package com.appbusters.robinkamboj.senseitall.di.module

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.di.scope.SiaApplicationScope

import dagger.Module
import dagger.Provides
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

@Module
class CalligraphyModule {

    @Provides
    @SiaApplicationScope
    fun viewPump(): ViewPump {
        return ViewPump.builder()
                .addInterceptor(CalligraphyInterceptor(calligraphyConfig()))
                .build()
    }

    @Provides
    @SiaApplicationScope
    fun calligraphyConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Comfortaa-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
    }
}
