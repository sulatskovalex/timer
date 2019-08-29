package com.github.sulatskovalex.timer.data.service_impl

import com.github.sulatskovalex.timer.data.services.TimeFormatter
import java.text.SimpleDateFormat
import java.util.*

class SimpleDateTimeFormatter : TimeFormatter {
    private val formatter = SimpleDateFormat("mm:ss", Locale.getDefault())
    private val tmpDate = Date()

    override fun formatToMinSec(mils: Long): String =
        formatter.format(tmpDate.apply { time = mils })
}