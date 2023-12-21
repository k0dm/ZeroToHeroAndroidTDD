package ru.easycode.zerotoheroandroidtdd.task27.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task27.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.task27.delete.DeleteViewModel
import ru.easycode.zerotoheroandroidtdd.task27.main.MainViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {

    class Base(private val core: Core) : ViewModelFactory {

        private val viewModelMap = mutableMapOf<Class<out ViewModel>, ViewModel>()
        override fun <T : ViewModel> viewModel(clasz: Class<out T>): T {
            val currentVm = viewModelMap[clasz]
            return if (currentVm == null) {
                val vm = when (clasz) {
                    MainViewModel::class.java -> MainViewModel(
                        core.repository(), core.listLiveDataWrapper()
                    )

                    AddViewModel::class.java -> AddViewModel(
                        core.repository(),
                        core.listLiveDataWrapper(),
                        this
                    )

                    DeleteViewModel::class.java -> DeleteViewModel(
                        core.listLiveDataWrapper(),
                        core.repository(),
                        this
                    )

                    else -> throw IllegalStateException("No such class ${clasz.name}")
                }
                viewModelMap[clasz] = vm
                vm as T
            } else {
                currentVm as T
            }
        }


        override fun clearViewModel(clasz: Class<out ViewModel>) {
            viewModelMap.remove(clasz)
        }
    }
}