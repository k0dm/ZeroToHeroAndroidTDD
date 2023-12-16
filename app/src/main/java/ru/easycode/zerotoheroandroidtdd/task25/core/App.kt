package ru.easycode.zerotoheroandroidtdd.task25.core

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task25.create.CreateViewModel
import ru.easycode.zerotoheroandroidtdd.task25.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.task25.main.MainViewModel

class App : Application(), ProvideViewModel, ClearViewModel {

    private lateinit var factory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        factory = ViewModelFactory.Base(ProvideViewModel.Base(Core.Base(), this))
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        factory.viewModel(viewModelClass)

    override fun clear(viewModelClass: Class<out ViewModel>) {
        factory.clear(viewModelClass)
    }
}