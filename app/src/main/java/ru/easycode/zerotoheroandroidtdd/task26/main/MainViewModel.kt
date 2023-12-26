package ru.easycode.zerotoheroandroidtdd.task26.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task26.core.ListLiveDataWrapper

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher,
) : ViewModel(), ListLiveDataWrapper.ProvideLiveData {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun init() {

        viewModelScope.launch(dispatcher) {
            val result = repository.list()
            withContext(dispatcherMain) {
                liveDataWrapper.update(result)
            }
        }
    }

    override fun liveData(): LiveData<List<String>> = liveDataWrapper.liveData()
}