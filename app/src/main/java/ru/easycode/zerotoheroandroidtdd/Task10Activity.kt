package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class Task10Activity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var removeButton: Button
    private lateinit var rootLayout: LinearLayout
    private var state: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task10)
        titleTextView = findViewById(R.id.titleTextView)
        rootLayout = findViewById<LinearLayout>(R.id.rootLayout)
        removeButton = findViewById<Button>(R.id.removeButton)
        removeButton.setOnClickListener {
            state = State.Removed
            state.apply(titleTextView, removeButton, rootLayout)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val state = savedInstanceState.getSerializable(KEY) as State
        state.apply(titleTextView, removeButton, rootLayout)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    companion object {
        private const val KEY = "KEY"
    }
}

interface State : Serializable {

    fun apply(textView: TextView, button: Button, linearLayout: LinearLayout) = Unit

    object Initial : State

    object Removed : State {
        override fun apply(textView: TextView, button: Button, linearLayout: LinearLayout) {
            linearLayout.removeView(textView)
            button.isEnabled = false
        }
    }
}
