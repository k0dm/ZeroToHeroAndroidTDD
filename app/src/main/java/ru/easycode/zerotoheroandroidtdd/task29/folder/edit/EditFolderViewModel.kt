package ru.easycode.zerotoheroandroidtdd.task29.folder.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task29.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.task29.main.Navigation

class EditFolderViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Rename,
    private val repository: FoldersRepository.Edit,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel(){

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun renameFolder(folderId: Long, newName: String) {
        viewModelScope.launch(dispatcher) {
            repository.rename(folderId, newName)

            withContext(dispatcherMain) {
                folderLiveDataWrapper.rename(newName)
                comeback()
            }
        }
    }

    fun deleteFolder(folderId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.delete(folderId)
            withContext(dispatcherMain) {
                clear.clear(EditFolderViewModel::class.java, FolderDetailsViewModel::class.java)
                navigation.update(FoldersListScreen)
            }
        }
    }

    fun comeback() {
        clear.clear(EditFolderViewModel::class.java)
        navigation.update(FolderDetailsScreen)
    }
}