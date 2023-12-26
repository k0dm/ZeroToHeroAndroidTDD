package ru.easycode.zerotoheroandroidtdd.task29.folder.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideLiveData
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderUi

class FolderLiveDataWrapper {

    interface Read {
        fun folderLiveData(): LiveData<FolderUi>
    }

    interface Update {
        fun update(folder: FolderUi)
    }

    interface ProvideData {
        fun folderId(): Long

        fun folderName(): String
    }

    interface Rename {
        fun rename(newName: String)
    }

    interface Increment {
        fun increment()
    }

    interface Decrement {
        fun decrement()
    }

    interface Mutable : Update, ProvideData, Read

    interface All : Mutable, Rename, Increment, Decrement

    class Base : All {

        private val folderLiveData = MutableLiveData<FolderUi>()
        override fun update(folder: FolderUi) {
            folderLiveData.value = folder
        }

        override fun folderId() = folderLiveData.value?.id() ?: -1

        override fun folderName() = folderLiveData.value?.title() ?: ""

        override fun rename(newName: String) {
            folderLiveData.value = folderLiveData.value!!.copy(title = newName)
        }

        override fun increment() {
            folderLiveData.value?.apply { this.incrementNotesCount() }
        }


        override fun decrement() {
            folderLiveData.value?.apply { this.decrementNotesCount() }
        }

        override fun folderLiveData() = folderLiveData
    }
}