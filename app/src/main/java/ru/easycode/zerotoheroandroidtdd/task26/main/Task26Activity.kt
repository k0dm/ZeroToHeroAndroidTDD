package ru.easycode.zerotoheroandroidtdd.task26.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask26Binding
import ru.easycode.zerotoheroandroidtdd.task26.add.AddBottomFragment
import ru.easycode.zerotoheroandroidtdd.task26.core.ProvideViewModel

class Task26Activity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityTask26Binding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask26Binding.inflate(layoutInflater)
        setContentView(binding.rootLayout)

        viewModel = viewModel(MainViewModel::class.java)

        val adapter = ListItemsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.addButton.setOnClickListener {
            AddBottomFragment().show(supportFragmentManager, "k0dm")
        }

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }


        viewModel.init()
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
        return (application as ProvideViewModel).viewModel(clasz)
    }
}