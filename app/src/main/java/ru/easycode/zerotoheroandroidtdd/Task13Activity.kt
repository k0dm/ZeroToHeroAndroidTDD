package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Task13Activity : AppCompatActivity() {

    private lateinit var countTextView: TextView
    private lateinit var decrementButton: Button
    private lateinit var incrementButton: Button

    private var state: UiState = UiState.Empty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task13)

        val count = Count.Base(2, 4, 0)


        countTextView = findViewById(R.id.countTextView)
        decrementButton = findViewById(R.id.decrementButton)
        incrementButton = findViewById(R.id.incrementButton)

        decrementButton.setOnClickListener {
            state = count.decrement(countTextView.text.toString())
            state.apply(incrementButton, decrementButton, countTextView)
        }

        incrementButton.setOnClickListener {
            state = count.increment(countTextView.text.toString())
            state.apply(incrementButton, decrementButton, countTextView)
        }

        if (savedInstanceState == null) {
            state = count.initial(countTextView.text.toString())
            state.apply(incrementButton, decrementButton, countTextView)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val result = savedInstanceState.getSerializable(STATE_KEY)
        if (result != null) (result as UiState).apply(
            incrementButton,
            decrementButton,
            countTextView
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (!state.isEmpty()) outState.putSerializable(STATE_KEY, state)
    }

    companion object {
        private const val STATE_KEY = "state_key"
    }
}