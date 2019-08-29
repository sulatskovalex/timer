package com.github.sulatskovalex.timer.data.service_impl

import com.github.sulatskovalex.timer.data.services.AppendCounter

class InMemoryCounter : AppendCounter {
    private var _count = 0
    override val count: Int
        get() = _count

    override fun increment(): Int {
        _count++
        return _count
    }
}