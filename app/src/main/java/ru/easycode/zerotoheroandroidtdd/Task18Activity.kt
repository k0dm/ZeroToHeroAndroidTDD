package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask18Binding
import ru.easycode.zerotoheroandroidtdd.task18.App
import ru.easycode.zerotoheroandroidtdd.task18.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.task18.MainViewModel

class Task18Activity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityTask18Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask18Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = (application as App).mainViewModel
        mainViewModel.provideLiveData().observe(this) { uiState ->
            uiState.show(binding)
        }

        binding.actionButton.setOnClickListener {
            mainViewModel.load()
        }

        mainViewModel.initial(savedInstanceState == null)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mainViewModel.restore(BundleWrapper.Base(savedInstanceState))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mainViewModel.save(BundleWrapper.Base(outState))
    }
}