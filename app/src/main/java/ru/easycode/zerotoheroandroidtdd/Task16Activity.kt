package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.task16.App
import ru.easycode.zerotoheroandroidtdd.task16.MainViewModel
import ru.easycode.zerotoheroandroidtdd.task16.UiState

class Task16Activity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task16)

        viewModel  = (application as App).viewModel

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val actionButton = findViewById<Button>(R.id.actionButton)

        actionButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.liveData().observe(this) {uiState->
            uiState.show(titleTextView, progressBar, actionButton)
        }

        if (savedInstanceState == null) {
            UiState.Initial.show(titleTextView, progressBar, actionButton)
        }else {
            viewModel.liveData().value?.show(titleTextView, progressBar, actionButton)
        }
    }
}