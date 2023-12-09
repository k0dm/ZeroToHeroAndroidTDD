package ru.easycode.zerotoheroandroidtdd.task19

import android.view.View
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask19Binding
import java.io.Serializable

interface UiState : Serializable {

    fun show(binding: ActivityTask19Binding)

    fun isEmpty(): Boolean = false


    object Empty : UiState {
        override fun show(binding: ActivityTask19Binding) = Unit

        override fun isEmpty() = true
    }

    object Initial : UiState {
        override fun show(binding: ActivityTask19Binding) = with(binding) {
            titleTextView.visibility = View.GONE
            progressBar.visibility = View.GONE
            actionButton.isEnabled = true
        }
    }

    object ShowProgress : UiState {
        override fun show(binding: ActivityTask19Binding) = with(binding) {
            progressBar.visibility = View.VISIBLE
            actionButton.isEnabled = false
        }
    }

    data class ShowData(private val text: String) : UiState {
        override fun show(binding: ActivityTask19Binding) = with(binding){
            titleTextView.visibility = View.VISIBLE
            titleTextView.text = text
            progressBar.visibility = View.GONE
            actionButton.isEnabled = true
        }
    }
}