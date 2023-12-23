package ru.easycode.zerotoheroandroidtdd.task28.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory
    override fun onCreate() {
        super.onCreate()
        factory = ViewModelFactory.Base(Core.Base(this))
    }

    override fun <T : ViewModel> viewModel(clasz: Class<out T>) = factory.viewModel(clasz)
}

