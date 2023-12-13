package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class Task9Activity
    : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var rootLayout: LinearLayout
    private var state: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task9)
        titleTextView = findViewById(R.id.titleTextView)
        rootLayout = findViewById<LinearLayout>(R.id.rootLayout)
        findViewById<Button>(R.id.removeButton).setOnClickListener {
            state = State.Removed
            state.apply(titleTextView, rootLayout)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val state = savedInstanceState.getSerializable(KEY) as State
        state.apply(titleTextView, rootLayout)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    companion object {
        private const val KEY = "KEY"
    }
}


interface State: Serializable {

    fun apply(textView: TextView, linearLayout: LinearLayout) = Unit

    object Initial: State

    object Removed: State {
        override fun apply(textView: TextView, linearLayout: LinearLayout) {
            linearLayout.removeView(textView)
        }
    }
}

