package ru.easycode.zerotoheroandroidtdd.task25.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task25.create.CreateViewModel
import ru.easycode.zerotoheroandroidtdd.task25.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.task25.main.MainViewModel

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base(
        private val core: Core,
        private val clearViewModel: ClearViewModel,
    ) : ProvideViewModel {

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {

            val viewModel = when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(core.navigation())

                ListViewModel::class.java -> ListViewModel(
                    core.listDataWrapper(),
                    core.navigation()
                )

                CreateViewModel::class.java -> CreateViewModel(
                    core.listDataWrapper(),
                    core.navigation(),
                    clearViewModel
                )

                else -> throw IllegalStateException("No such class ${viewModelClass.name}")
            } as T
            return viewModel
        }
    }
}