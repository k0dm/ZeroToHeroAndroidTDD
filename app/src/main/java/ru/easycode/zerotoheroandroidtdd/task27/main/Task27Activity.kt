package ru.easycode.zerotoheroandroidtdd.task27.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask27Binding
import ru.easycode.zerotoheroandroidtdd.task27.add.AddBottomSheetFragment
import ru.easycode.zerotoheroandroidtdd.task27.add.ListItemsAdapter
import ru.easycode.zerotoheroandroidtdd.task27.core.ProvideViewModel

class Task27Activity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityTask27Binding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask27Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ListItemsAdapter(supportFragmentManager)
        binding.recyclerView.adapter = adapter
        binding.addButton.setOnClickListener {
            AddBottomSheetFragment().show(supportFragmentManager, "k0dm")
        }


        viewModel = viewModel(MainViewModel::class.java)
        viewModel.liveData().observe(this){
            adapter.update(it)
        }
        viewModel.init()
    }

    override fun <T : ViewModel> viewModel(clasz: Class<out T>) =
        (application as ProvideViewModel).viewModel(clasz)
}