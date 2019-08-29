package com.github.sulatskovalex.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.sulatskovalex.timer.fragments.timer.TimerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentById(R.id.main_activity_container) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_activity_container, TimerFragment())
                .commit()
        }
    }
}
