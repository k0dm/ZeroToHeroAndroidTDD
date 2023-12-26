package ru.easycode.zerotoheroandroidtdd.task29.folder.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideLiveData
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NoteUi

class NoteListLiveDataWrapper {

    interface Read{
        fun noteListLiveData(): LiveData<List<NoteUi>>
    }

    interface UpdateListAndRead : Read {
        fun update(notes: List<NoteUi>)
    }

    interface Update {
        fun update(noteId: Long, newText: String)
    }

    interface Create {
        fun create(noteUi: NoteUi)
    }

    interface All : UpdateListAndRead, Update, Create

    class Base() : All {

        private val listNoteUiLiveData = MutableLiveData<List<NoteUi>>()
        override fun update(notes: List<NoteUi>) {
            listNoteUiLiveData.value = notes
        }

        override fun update(noteId: Long, newText: String) {

            val list = ArrayList(listNoteUiLiveData.value ?: emptyList())

            val index = list.indexOfFirst { it.id() == noteId }
            if (index != -1) {
                list[index] = list[index].copy(title = newText)
                update(list)
            }
        }

        override fun create(noteUi: NoteUi) {
            val newList = ArrayList(listNoteUiLiveData.value ?: emptyList()).apply {
                add(noteUi)
            }
            update(newList)
        }

        override fun noteListLiveData() = listNoteUiLiveData
    }
}