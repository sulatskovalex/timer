package com.github.sulatskovalex.timer.fragments.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.github.sulatskovalex.timer.R
import kotlinx.android.synthetic.main.fragment_timer.view.*
import org.koin.android.ext.android.inject

class TimerFragment : Fragment(), TimerView {

    private val presenter: TimerPresenter by inject()
    private var dialog: AlertDialog? = null

    override fun showTime(minSec: String) {
        view?.time?.text = minSec
    }

    override fun showDialog() {
        activity?.apply {
            if (!isFinishing) {
                dialog = AlertDialog.Builder(this)
                    .setMessage(R.string.time_is_over)
                    .setPositiveButton(R.string.append_20_sec) { _, _ ->
                        presenter.onAppendTwentySecClick()
                    }
                    .setNegativeButton(R.string.close) { _, _ ->
                        presenter.onDialogCloseClick()
                    }
                    .create()
                dialog?.show()
            }
        }
    }

    override fun closeDialog() {
        dialog?.dismiss()
    }

    override fun showAppendCount(count: Int) {
        view?.append_count?.text = "$count"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_timer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.append.setOnClickListener {
            presenter.onAppendFiveSecClick()
        }
        presenter.view = this
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        presenter.onStart()
        super.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        presenter.view = null
        super.onDestroyView()
    }

}