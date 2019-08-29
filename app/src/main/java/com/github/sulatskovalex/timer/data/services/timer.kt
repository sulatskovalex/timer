package com.github.sulatskovalex.timer.data.services

interface Timer {
    fun registerListener(onTimeChangeHandler: OnTimeChangeHandler)
    fun unregisterListener(onTimeChangeHandler: OnTimeChangeHandler)
    val canStart: Boolean
    fun start()
    fun appendFiveSec()
    fun appendTwentySec()
}

interface OnTimeChangeHandler {
    fun onTimeChanged(mills: Long)
    fun onTimeOver()
}