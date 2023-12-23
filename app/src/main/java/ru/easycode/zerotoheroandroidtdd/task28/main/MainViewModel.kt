package ru.easycode.zerotoheroandroidtdd.task28.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task28.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task28.data.Repository

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.All,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel(), ListLiveDataWrapper.ProvideLiveData {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun init() {
        viewModelScope.launch(dispatcher) {
            val result = repository.list()
            withContext(dispatcherMain) {
                liveDataWrapper.update(result.map { it.toUI() })
            }
        }
    }

    override fun liveData() = liveDataWrapper.liveData()
}