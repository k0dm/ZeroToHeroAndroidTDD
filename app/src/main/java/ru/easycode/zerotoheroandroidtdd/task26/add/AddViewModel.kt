package ru.easycode.zerotoheroandroidtdd.task26.add

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task26.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.task26.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task26.main.Repository

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun add(value: String) = viewModelScope.launch(dispatcher) {

        repository.add(value)
        withContext(dispatcherMain) {
            liveDataWrapper.add(value)
            comeback()
        }
    }


    fun comeback() {
        clear.clearViewModel(AddViewModel::class.java)
    }
}