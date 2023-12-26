package ru.easycode.zerotoheroandroidtdd.task29.folder.details

import android.icu.text.CaseMap.Fold
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task29.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideLiveData
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.folder.edit.EditFolderScreen
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderListViewModel
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderUi
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.task29.main.Navigation
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NoteUi
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NotesRepository
import ru.easycode.zerotoheroandroidtdd.task29.note.create.CreateNoteScreen
import ru.easycode.zerotoheroandroidtdd.task29.note.edit.EditNoteScreen

class FolderDetailsViewModel(
    private val noteListRepository: NotesRepository.ReadList,
    private val liveDataWrapper: NoteListLiveDataWrapper.UpdateListAndRead,
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel(), NoteListLiveDataWrapper.Read, FolderLiveDataWrapper.Read {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun init() {
        val folderId = folderLiveDataWrapper.folderId()
        viewModelScope.launch(dispatcher) {
            val notes = noteListRepository.noteList(folderId)

            withContext(dispatcherMain) {
                liveDataWrapper.update(notes.map { it.toUi() })
            }
        }
    }

    override fun noteListLiveData() = liveDataWrapper.noteListLiveData()

    override fun folderLiveData() = folderLiveDataWrapper.folderLiveData()

    fun createNote() = navigation.update(CreateNoteScreen(folderLiveDataWrapper.folderId()))

    fun editNote(noteUi: NoteUi) = navigation.update(EditNoteScreen(noteUi.id()))

    fun editFolder() = navigation.update(
        EditFolderScreen(folderLiveDataWrapper.folderId(), folderLiveDataWrapper.folderName())
    )

    fun comeback() {
        clear.clear(FolderDetailsViewModel::class.java)
        navigation.update(FoldersListScreen)
    }
}