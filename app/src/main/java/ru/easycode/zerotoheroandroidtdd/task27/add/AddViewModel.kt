package ru.easycode.zerotoheroandroidtdd.task27.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task27.add.data.Repository
import ru.easycode.zerotoheroandroidtdd.task27.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.task27.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task27.main.ItemUi

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun add(value: String) {
        viewModelScope.launch(dispatcher) {
            val id = repository.add(value)
            withContext(dispatcherMain) {
                liveDataWrapper.add(ItemUi(id, value))
                comeback()
            }
        }
    }

    fun comeback(){
        clear.clearViewModel(AddViewModel::class.java)
    }
}