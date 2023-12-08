package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Task8Activity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task8)
        titleTextView = findViewById(R.id.titleTextView)
        findViewById<Button>(R.id.hideButton).setOnClickListener {
            titleTextView.visibility = View.GONE
        }
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        titleTextView.visibility = savedInstanceState.getInt(TITLE_TEXT_VIEW_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TITLE_TEXT_VIEW_KEY, titleTextView.visibility)
    }

    companion object {
        private const val TITLE_TEXT_VIEW_KEY = "TITLE_TEXT_VIEW_KEY"
    }
}