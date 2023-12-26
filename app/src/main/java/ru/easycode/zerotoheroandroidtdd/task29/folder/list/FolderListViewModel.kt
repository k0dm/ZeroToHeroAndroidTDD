package ru.easycode.zerotoheroandroidtdd.task29.folder.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideLiveData
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.task29.folder.create.CreateFolderScreen
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.task29.main.Navigation

class FolderListViewModel(
    private val repository: FoldersRepository.ReadList,
    private val listLiveDataWrapper: FolderListLiveDataWrapper.UpdateListAndRead,
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Update,
    private val navigation: Navigation.Update,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel(), FolderListLiveDataWrapper.Read {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun init() {
        viewModelScope.launch(dispatcher) {
            val folders = repository.folders()
            withContext(dispatcherMain) {
                listLiveDataWrapper.update(folders.map { it.toUi() })
            }
        }
    }

    fun addFolder() {
        navigation.update(CreateFolderScreen)
    }

    fun folderDetails(folderUi: FolderUi) {
        folderLiveDataWrapper.update(folderUi)
        navigation.update(FolderDetailsScreen)
    }

    override fun folderListLiveData() = listLiveDataWrapper.folderListLiveData()
}