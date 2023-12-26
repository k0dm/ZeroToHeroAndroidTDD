package ru.easycode.zerotoheroandroidtdd.task29.folder.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task29.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderUi
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.task29.main.Navigation

class CreateFolderViewModel(
    private val repository: FoldersRepository.Create,
    private val liveDataWrapper: FolderListLiveDataWrapper.Create,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun createFolder(name: String) {
        viewModelScope.launch(dispatcher) {
            val id = repository.createFolder(name)
            withContext(dispatcherMain) {
                liveDataWrapper.create(FolderUi(id, name, 0))
                comeback()
            }
        }

    }

    fun comeback() {
        clear.clear(CreateFolderViewModel::class.java)
        navigation.update(FoldersListScreen)
    }
}