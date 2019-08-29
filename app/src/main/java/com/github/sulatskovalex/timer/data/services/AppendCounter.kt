package com.github.sulatskovalex.timer.data.services

interface AppendCounter {
    val count: Int
    fun increment(): Int
}