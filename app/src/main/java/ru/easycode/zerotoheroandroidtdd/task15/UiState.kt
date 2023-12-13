package ru.easycode.zerotoheroandroidtdd.task15

import android.opengl.Visibility
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

interface UiState {

    fun show(titleTextView: TextView, progressBar: ProgressBar, actionButton: Button)

    object Initial : UiState {
        override fun show(titleTextView: TextView, progressBar: ProgressBar, actionButton: Button) {
            titleTextView.visibility = View.GONE
            progressBar.visibility = View.GONE
            actionButton.isEnabled = true
        }
    }

    object ShowProgress : UiState {
        override fun show(titleTextView: TextView, progressBar: ProgressBar, actionButton: Button) {
            progressBar.visibility = View.VISIBLE
            actionButton.isEnabled = false
        }
    }

    object ShowData : UiState {
        override fun show(titleTextView: TextView, progressBar: ProgressBar, actionButton: Button) {
            titleTextView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            actionButton.isEnabled = true
        }
    }
}