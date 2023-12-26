package ru.easycode.zerotoheroandroidtdd.task18

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask18Binding
import java.io.Serializable

interface UiState : Serializable {

    fun show(binding: ActivityTask18Binding)

    fun isEmpty(): Boolean = false


    object Empty : UiState {
        override fun show(binding: ActivityTask18Binding) = Unit

        override fun isEmpty() = true
    }

    object Initial : UiState {
        override fun show(binding: ActivityTask18Binding) = with(binding) {
            titleTextView.visibility = View.GONE
            progressBar.visibility = View.GONE
            actionButton.isEnabled = true
        }
    }

    object ShowProgress : UiState {
        override fun show(binding: ActivityTask18Binding) = with(binding) {
            progressBar.visibility = View.VISIBLE
            actionButton.isEnabled = false
        }
    }

    data class ShowData(private val text: String) : UiState {
        override fun show(binding: ActivityTask18Binding) = with(binding){
            titleTextView.visibility = View.VISIBLE
            titleTextView.text = text
            progressBar.visibility = View.GONE
            actionButton.isEnabled = true
        }
    }
}