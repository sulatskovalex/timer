package com.github.sulatskovalex.timer.data.service_impl

import android.os.CountDownTimer
import com.github.sulatskovalex.timer.data.services.OnTimeChangeHandler
import com.github.sulatskovalex.timer.data.services.Timer

class CountDownTimer : Timer,
    OnTimeChangeHandler {
    companion object {
        private const val oneSecInMillis = 1_000L
        private const val fiveSecInMillis = 5_000L
        private const val twentySecInMillis = 20_000L
        private const val initialTime = 20_000L
    }

    private val state = TimerState()
    private var secNow = initialTime
    private var timer = Timer(initialTime, this)
    private val listeners: MutableSet<OnTimeChangeHandler> = mutableSetOf()

    override fun onTimeChanged(mills: Long) {
        this.secNow = mills
        listeners.forEach {
            it.onTimeChanged(mills)
        }
    }

    override fun onTimeOver() {
        state.finished()
        secNow = 0L
        listeners.forEach {
            it.onTimeOver()
        }
    }

    override fun registerListener(onTimeChangeHandler: OnTimeChangeHandler) {
        onTimeChangeHandler.onTimeChanged(secNow)
        listeners.add(onTimeChangeHandler)
    }

    override fun unregisterListener(onTimeChangeHandler: OnTimeChangeHandler) {
        listeners.remove(onTimeChangeHandler)
    }

    override val canStart: Boolean
        get() = state.canStart

    override fun start() {
        state.started()
        timer.start()
    }

    private fun append(mills: Long) {
        timer.cancel()
        timer = Timer(secNow + mills, this)
        timer.start()
        state.started()
    }

    override fun appendFiveSec() {
        append(fiveSecInMillis)
    }

    override fun appendTwentySec() {
        append(twentySecInMillis)
    }

    private class Timer(
        mills: Long,
        private val listener: OnTimeChangeHandler
    ) : CountDownTimer(mills, oneSecInMillis) {

        override fun onFinish() {
            listener.onTimeOver()
        }

        override fun onTick(millisUntilFinished: Long) {
            listener.onTimeChanged(millisUntilFinished)
        }
    }

    private class TimerState {
        private companion object {
            private const val stateInitial = 0
            private const val stateRunning = 1
            private const val stateFinished = 2
        }

        private var value: Int = stateInitial

        fun started() {
            value = stateRunning
        }

        fun finished() {
            value = stateFinished
        }

        val canStart: Boolean
            get() = value == stateInitial
    }

}