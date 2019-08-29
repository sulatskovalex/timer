package com.github.sulatskovalex.timer.fragments.timer

import com.github.sulatskovalex.timer.data.services.AppendCounter
import com.github.sulatskovalex.timer.data.services.OnTimeChangeHandler
import com.github.sulatskovalex.timer.data.services.TimeFormatter
import com.github.sulatskovalex.timer.data.services.Timer

interface TimerView {
    fun showTime(minSec: String)
    fun showDialog()
    fun closeDialog()
    fun showAppendCount(count: Int)
}

class TimerPresenter(
    private val timerService: Timer,
    private val timeFormatter: TimeFormatter,
    private val appendCounter: AppendCounter
) : OnTimeChangeHandler {

    init {
        if (timerService.canStart) {
            timerService.start()
        }
    }

    var view: TimerView? = null

    fun onStart() {
        view?.showAppendCount(appendCounter.count)
        timerService.registerListener(this)
    }

    fun onStop() {
        timerService.unregisterListener(this)
    }

    override fun onTimeChanged(mills: Long) {
        view?.showTime(timeFormatter.formatToMinSec(mills))
    }

    override fun onTimeOver() {
        view?.showDialog()
    }

    fun onAppendFiveSecClick() {
        incrementAppendCount()
        timerService.appendFiveSec()
    }

    fun onAppendTwentySecClick() {
        incrementAppendCount()
        timerService.appendTwentySec()
    }

    private fun incrementAppendCount() {
        val increment = appendCounter.increment()
        view?.showAppendCount(increment)
    }

    fun onDialogCloseClick() {
        view?.closeDialog()
    }
}