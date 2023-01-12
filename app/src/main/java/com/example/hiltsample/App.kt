package com.example.hiltsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()

//        BuildConfig.DEBUG.let {
//            Timber.plant(Timber.DebugTree())
//        }
        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "%s.%s(L:%s)",
                        super.createStackElementTag(element),
                        element.methodName,
                        element.lineNumber
                    )
                }
            })
        }
    }
}