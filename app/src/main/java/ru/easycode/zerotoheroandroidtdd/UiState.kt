package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable{

    fun apply(incrementButton: Button, decrementButton: Button, countTextView: TextView)

    fun isEmpty(): Boolean = false

    object Empty : UiState {
        override fun apply(
            incrementButton: Button,
            decrementButton: Button,
            countTextView: TextView,
        ) = Unit

        override fun isEmpty() = true
    }

    data class Min(private val text: String) : UiState {
        override fun apply(
            incrementButton: Button,
            decrementButton: Button,
            countTextView: TextView,
        ) {
            incrementButton.isEnabled = true
            decrementButton.isEnabled = false
            countTextView.text = text
        }
    }

    data class Base(private val text: String) : UiState {
        override fun apply(
            incrementButton: Button,
            decrementButton: Button,
            countTextView: TextView,
        ) {
            incrementButton.isEnabled = true
            decrementButton.isEnabled = true
            countTextView.text = text

        }
    }

    data class Max(private val text: String) : UiState {
        override fun apply(
            incrementButton: Button,
            decrementButton: Button,
            countTextView: TextView,
        ) {
            incrementButton.isEnabled = false
            decrementButton.isEnabled = true
            countTextView.text = text
        }
    }
}