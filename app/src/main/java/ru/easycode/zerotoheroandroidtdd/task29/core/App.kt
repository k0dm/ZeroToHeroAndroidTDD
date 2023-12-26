package ru.easycode.zerotoheroandroidtdd.task29.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel, ClearViewModels {

    private lateinit var factory: ProvideViewModel.Factory

    override fun onCreate() {
        super.onCreate()
        factory = ProvideViewModel.Factory(ProvideViewModel.Base(Core.Base(this, this)))
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>) = factory.viewModel(clasz)

    override fun clear(vararg viewModelClasses: Class<out ViewModel>) =
        viewModelClasses.forEach { factory.clear(it) }

}

