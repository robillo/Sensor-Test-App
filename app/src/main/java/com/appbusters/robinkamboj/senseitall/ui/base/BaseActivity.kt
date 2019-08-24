@file:Suppress("unused")

package com.appbusters.robinkamboj.senseitall.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setup()
    }

    abstract fun getLayoutResId(): Int

    abstract fun setup()

    @SuppressLint("ObsoleteSdkInt")
    protected fun setStatusBarColor(color: Int, shouldShowLightStatusBar: Boolean) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, color)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowLightStatusBar)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    protected fun animateActivityTransition(enterAnim: Int, exitAnim: Int) {
        overridePendingTransition(enterAnim, exitAnim)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    fun showSnackBar(parent: View, text: String, duration: Int) {
        val snackBar = Snackbar.make(parent, text, duration)
        val textView = snackBar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackBar.show()
    }

    fun setLightStatusBar() {
        var flags = window.decorView.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setWindowFlags(flags)
    }

    private fun setWindowFlags(flags: Int) {
        window.decorView.systemUiVisibility = flags
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }
}