package ru.easycode.zerotoheroandroidtdd.task12

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState: Serializable {

    fun show(button: Button, textView: TextView) = Unit

    object Empty: UiState

    data class Base(private val text: String) : UiState {
        override fun show(button: Button, textView: TextView) {
            textView.text = text
        }
    }

    data class Max(private val text: String) : UiState {
        override fun show(button: Button, textView: TextView) {
            button.isEnabled = false
            textView.text = text
        }
    }
}