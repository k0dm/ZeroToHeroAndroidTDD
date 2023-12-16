package ru.easycode.zerotoheroandroidtdd.task16

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch(Dispatchers.IO) {
            repository.load()
            withContext(Dispatchers.Main) {
                liveDataWrapper.update(UiState.ShowData)
            }
        }
    }

    fun liveData() = liveDataWrapper.liveData()
}


