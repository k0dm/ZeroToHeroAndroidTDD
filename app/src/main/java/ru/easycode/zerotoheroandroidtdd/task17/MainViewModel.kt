package ru.easycode.zerotoheroandroidtdd.task17

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository,
) : ViewModel(), ProvideLiveData<UiState> {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

   // private var cachedUiState: UiState = UiState.Empty

    fun initial(firstTime: Boolean) {
        if (firstTime) liveDataWrapper.update(UiState.Initial)
    }

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch(Dispatchers.IO) {
            repository.load()
          //  cachedUiState = UiState.ShowData
            withContext(Dispatchers.Main) {
                liveDataWrapper.update(UiState.ShowData)
            }
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        //cachedUiState =
        liveDataWrapper.update(bundleWrapper.restore())
    }

    override fun provideLiveData(): LiveData<UiState> = liveDataWrapper.liveData()
}

interface ProvideLiveData<T> {
    fun provideLiveData(): LiveData<T>
}


