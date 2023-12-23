package ru.easycode.zerotoheroandroidtdd.task28.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask28Binding
import ru.easycode.zerotoheroandroidtdd.databinding.ItemListBinding
import ru.easycode.zerotoheroandroidtdd.task28.add.AddBottomSheetFragment
import ru.easycode.zerotoheroandroidtdd.task28.core.ProvideViewModel

class Task28Activity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityTask28Binding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask28Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModel(MainViewModel::class.java)

        val adapter = MainListAdapter(supportFragmentManager)

        binding.apply {
            recyclerView.adapter = adapter

            addButton.setOnClickListener {
                AddBottomSheetFragment().show(supportFragmentManager, "k0dm")
            }
        }

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }
        viewModel.init()
    }

    override fun <T : ViewModel> viewModel(clasz: Class<out T>) =
        (application as ProvideViewModel).viewModel(clasz)
}