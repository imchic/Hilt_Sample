package com.example.hiltsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        BuildConfig.DEBUG.let {
            Timber.plant(Timber.DebugTree())
        }
    }
}