package ru.easycode.zerotoheroandroidtdd.task17

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface UiState: Serializable{

    fun show(textView: TextView, progressBar: ProgressBar, button: Button)

    fun isEmpty(): Boolean = false

    object Empty : UiState {
        override fun show(textView: TextView, progressBar: ProgressBar, button: Button) = Unit

        override fun isEmpty() = true
    }

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