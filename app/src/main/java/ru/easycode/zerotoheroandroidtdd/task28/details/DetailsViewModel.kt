package ru.easycode.zerotoheroandroidtdd.task28.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task28.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.task28.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task28.data.Repository
import ru.easycode.zerotoheroandroidtdd.task28.main.ItemUi

class DetailsViewModel(
    private val changeLiveDataWrapper: ListLiveDataWrapper.All,
    private val repository: Repository.Change,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val innerLiveData = MutableLiveData<String>()
    val liveData: LiveData<String>
        get() = innerLiveData

    fun init(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            val item = repository.item(itemId)
            withContext(dispatcherMain) {
                item.add(innerLiveData)
            }
        }
    }

    fun delete(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.delete(itemId)
            withContext(dispatcherMain){
                changeLiveDataWrapper.delete(ItemUi(itemId, liveData.value!!))
                comeback()
            }
        }
    }

    fun update(itemId: Long, newText: String) {
        viewModelScope.launch(dispatcher) {
            repository.update(itemId, newText)
            withContext(dispatcherMain) {
                changeLiveDataWrapper.update(ItemUi(itemId, newText))
                comeback()
            }
        }
    }

    fun comeback() = clear.clearViewModel(DetailsViewModel::class.java)
}
