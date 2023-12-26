package ru.easycode.zerotoheroandroidtdd.task29.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideViewModel

class Task29Activity : AppCompatActivity(), ProvideViewModel {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task29)

        val viewModel = viewModel(MainViewModel::class.java)
        viewModel.navigation().observe(this) { screen ->
            screen.show(supportFragmentManager, R.id.rootLayout)
        }
        viewModel.init(savedInstanceState == null)
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>) =
        (application as ProvideViewModel).viewModel(clasz)
}