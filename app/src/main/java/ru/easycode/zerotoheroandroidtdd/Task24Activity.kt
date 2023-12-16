package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask24Binding
import ru.easycode.zerotoheroandroidtdd.task24.App
import ru.easycode.zerotoheroandroidtdd.task24.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.task24.ListAdapter
import ru.easycode.zerotoheroandroidtdd.task24.MainViewModel

class Task24Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask24Binding
    private lateinit var viewModel: MainViewModel
    private val adapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask24Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = (application as App).viewModel

        viewModel.liveData().observe(this) {list->
            adapter.updateList(list)
        }


        binding.apply {
            recyclerView.adapter = adapter

            actionButton.setOnClickListener {
                val txt = inputEditText.text
                inputEditText.setText("")
                txt?.let {
                    viewModel.add(it.toString())
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restore(BundleWrapper.Base(savedInstanceState))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }
}