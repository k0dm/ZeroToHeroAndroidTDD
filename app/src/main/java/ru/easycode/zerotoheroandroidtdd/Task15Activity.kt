package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.task15.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task15.MainViewModel
import ru.easycode.zerotoheroandroidtdd.task15.Repository
import ru.easycode.zerotoheroandroidtdd.task15.UiState

class Task15Activity : AppCompatActivity() {

    private val viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task15)

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val actionButton = findViewById<Button>(R.id.actionButton)

        viewModel.observeUiState(this) { uiState ->
            uiState.show(titleTextView, progressBar, actionButton)
        }

        actionButton.setOnClickListener {
            viewModel.load()
        }

        if (savedInstanceState == null) UiState.Initial.show(
            titleTextView,
            progressBar,
            actionButton
        )
    }
}