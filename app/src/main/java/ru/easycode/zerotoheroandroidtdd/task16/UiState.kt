package ru.easycode.zerotoheroandroidtdd.task16

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

interface UiState {

    fun show(textView: TextView, progressBar: ProgressBar, button: Button)

    object Initial : UiState {
        override fun show(textView: TextView, progressBar: ProgressBar, button: Button) {
            textView.visibility = View.GONE
            progressBar.visibility = View.GONE
            button.isEnabled = true
        }
    }

    object ShowProgress : UiState {
        override fun show(textView: TextView, progressBar: ProgressBar, button: Button) {
            progressBar.visibility = View.VISIBLE
            button.isEnabled = false
        }
    }

    object ShowData : UiState {
        override fun show(textView: TextView, progressBar: ProgressBar, button: Button) {
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            button.isEnabled = true
        }
    }
}