package ru.easycode.zerotoheroandroidtdd.task12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.R

class Task12Activity : AppCompatActivity() {

    private lateinit var countTextView: TextView
    private lateinit var incrementButton: Button
    private var state: UiState = UiState.Empty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task12)

        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById<Button>(R.id.incrementButton)

        val count = Count.Base(2, 4)

        incrementButton.setOnClickListener {
            state = count.increment(countTextView.text.toString())
            state.show(incrementButton, countTextView)

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        (savedInstanceState.getSerializable(KEY) as UiState).show(incrementButton, countTextView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    companion object {
        private val KEY = "ENABLE_KEY"
    }

}