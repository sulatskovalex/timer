package com.github.sulatskovalex.timer.di

import com.github.sulatskovalex.timer.data.service_impl.CountDownTimer
import com.github.sulatskovalex.timer.data.service_impl.InMemoryCounter
import com.github.sulatskovalex.timer.data.service_impl.SimpleDateTimeFormatter
import com.github.sulatskovalex.timer.data.services.AppendCounter
import com.github.sulatskovalex.timer.data.services.TimeFormatter
import com.github.sulatskovalex.timer.data.services.Timer
import com.github.sulatskovalex.timer.fragments.timer.TimerPresenter
import org.koin.dsl.module

fun appModule() = module {

    single<Timer> { CountDownTimer() }
    single<TimeFormatter> { SimpleDateTimeFormatter() }
    single<AppendCounter> { InMemoryCounter() }

    factory { TimerPresenter(get(), get(), get()) }
}