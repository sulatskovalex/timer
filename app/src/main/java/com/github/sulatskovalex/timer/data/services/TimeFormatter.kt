package com.github.sulatskovalex.timer.data.services

interface TimeFormatter {
    fun formatToMinSec(mils: Long): String
}