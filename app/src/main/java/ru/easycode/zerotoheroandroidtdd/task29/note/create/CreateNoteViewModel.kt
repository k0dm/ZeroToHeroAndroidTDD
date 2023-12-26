package ru.easycode.zerotoheroandroidtdd.task29.note.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.task29.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.main.Navigation
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NoteUi
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NotesRepository

class CreateNoteViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Increment,
    private val addLiveDataWrapper: NoteListLiveDataWrapper.Create,
    private val repository: NotesRepository.Create,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun createNote(folderId: Long, text: String) {
        viewModelScope.launch(dispatcher) {
            val noteId = repository.createNote(folderId, text)
            withContext(dispatcherMain) {
                folderLiveDataWrapper.increment()
                addLiveDataWrapper.create(NoteUi(noteId, text, folderId))
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clear(CreateNoteViewModel::class.java)
        navigation.update(FolderDetailsScreen)
    }
}