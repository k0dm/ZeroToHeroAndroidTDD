package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Task11Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task11)

        val count = Count.Base(2)
        val textView = findViewById<TextView>(R.id.countTextView)
        findViewById<Button>(R.id.incrementButton).setOnClickListener {
            textView.text = count.increment(textView.text.toString())
        }

    }
}