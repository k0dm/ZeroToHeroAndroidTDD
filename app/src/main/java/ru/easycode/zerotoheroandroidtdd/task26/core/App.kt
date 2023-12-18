package ru.easycode.zerotoheroandroidtdd.task26.core

import android.app.Application
import android.text.Spannable.Factory
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel{

    private lateinit var factory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        factory = ViewModelFactory.Base(Core.Base(this))
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T = factory.viewModel(clasz)
}