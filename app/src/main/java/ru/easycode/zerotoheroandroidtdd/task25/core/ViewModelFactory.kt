package ru.easycode.zerotoheroandroidtdd.task25.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ClearViewModel, ProvideViewModel {

    class Base(private val provideViewModel: ProvideViewModel) : ViewModelFactory {

        private val viewModelMap = mutableMapOf<Class<out ViewModel>, ViewModel>()

        override fun clear(viewModelClass: Class<out ViewModel>) {
            viewModelMap.remove(viewModelClass)
        }

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            val result = viewModelMap[viewModelClass]
            return if (result == null) {
                val newViewModel = provideViewModel.viewModel(viewModelClass)
                viewModelMap[viewModelClass] = newViewModel
                newViewModel
            } else {
                result as T
            }
        }
    }
}

