package ru.easycode.zerotoheroandroidtdd.task25.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.ListFragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask25Binding
import ru.easycode.zerotoheroandroidtdd.task25.core.App
import ru.easycode.zerotoheroandroidtdd.task25.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.task25.core.ProvideViewModel

class Task25Activity : AppCompatActivity(), ProvideViewModel, ClearViewModel {


    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task25)

        mainViewModel = viewModel(MainViewModel::class.java)

        mainViewModel.navigation().observe(this) {screen->
            Log.d("k0dm", "MA LiveData Screen ${screen.javaClass.name}")
            screen.show(supportFragmentManager, R.id.rootLayout)
        }

        mainViewModel.init(savedInstanceState == null)
    }

    override fun clear(viewModelClass: Class<out ViewModel>) {
        (application as App).clear(viewModelClass)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as App).viewModel(viewModelClass)
    }
}