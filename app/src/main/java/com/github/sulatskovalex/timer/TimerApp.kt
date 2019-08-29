package com.github.sulatskovalex.timer

import android.app.Application
import com.github.sulatskovalex.timer.di.appModule
import org.koin.core.context.startKoin

class TimerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule())
        }
    }
}