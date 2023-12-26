package ru.easycode.zerotoheroandroidtdd.task29.note.edit

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
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.main.Navigation
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NotesRepository

class EditNoteViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Decrement,
    private val noteLiveDataWrapper: NoteLiveDataWrapper.ReadAndUpdate,
    private val noteListLiveDataWrapper: NoteListLiveDataWrapper.Update,
    private val repository: NotesRepository.Edit,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel(), NoteLiveDataWrapper.Read {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun init(noteId: Long) {
        viewModelScope.launch(dispatcher) {
            val note = repository.note(noteId)
            withContext(dispatcherMain) {
                note.update(noteLiveDataWrapper)
            }
        }
    }

    override fun noteTextLiveData() = noteLiveDataWrapper.noteTextLiveData()

    fun deleteNote(noteId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.deleteNote(noteId)
            withContext(dispatcherMain) {
                folderLiveDataWrapper.decrement()
                comeback()
            }
        }
    }

    fun renameNote(noteId: Long, newText: String) {
        viewModelScope.launch(dispatcher) {
            repository.renameNote(noteId, newText)
            withContext(dispatcherMain) {
                noteListLiveDataWrapper.update(noteId, newText)
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clear(EditNoteViewModel::class.java)
        navigation.update(FolderDetailsScreen)
    }
}