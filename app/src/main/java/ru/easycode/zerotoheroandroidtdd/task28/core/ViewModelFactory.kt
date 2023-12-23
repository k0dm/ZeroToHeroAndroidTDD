package ru.easycode.zerotoheroandroidtdd.task28.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task28.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.task28.details.DetailsViewModel
import ru.easycode.zerotoheroandroidtdd.task28.main.MainViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {

    class Base(private val core: Core) : ViewModelFactory {

        private val viewModelMap = mutableMapOf<Class<out ViewModel>, ViewModel>()
        override fun <T : ViewModel> viewModel(clasz: Class<out T>): T {
            var viewModel = viewModelMap[clasz]

            return if (viewModel != null) {
                viewModel as T
            } else {
                viewModel = when (clasz) {
                    MainViewModel::class.java -> MainViewModel(
                        core.repository(),
                        core.listLiveDataWrapper()
                    )

                    AddViewModel::class.java -> AddViewModel(
                        core.repository(),
                        core.listLiveDataWrapper(),
                        this
                    )

                    DetailsViewModel::class.java -> DetailsViewModel(
                        core.listLiveDataWrapper(),
                        core.repository(),
                        this
                    )

                    else -> throw IllegalStateException("No such viewModel with class: ${clasz.name}")
                }
                viewModel as T
            }
        }

        override fun clearViewModel(clasz: Class<out ViewModel>) {
            viewModelMap.remove(clasz)
        }
    }
}