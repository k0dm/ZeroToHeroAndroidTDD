package ru.easycode.zerotoheroandroidtdd.task29.folder.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideLiveData

class FolderListLiveDataWrapper {

    interface Read{
        fun folderListLiveData(): LiveData<List<FolderUi>>
    }
    interface UpdateListAndRead: Read {
        fun update(list: List<FolderUi>)
    }

    interface Update {
        fun update(folder: FolderUi)
    }

    interface Create {
        fun create(folderUi: FolderUi)
    }

    interface All : UpdateListAndRead, Update, Create

    class Base() : All {

        private val listFolderUiLiveData = MutableLiveData<List<FolderUi>>()
        override fun update(list: List<FolderUi>) {
            listFolderUiLiveData.value = list
        }

        override fun update(folder: FolderUi) {
            val list = ArrayList(listFolderUiLiveData.value ?: emptyList())

            val index = list.indexOfFirst { it.areItemsTheSame(folder) }
            if (index != -1) {
                list[index] = folder
            }else {
                list.add(folder)
            }
            update(list)
        }

        override fun create(folderUi: FolderUi) {
            val newList = ArrayList(listFolderUiLiveData.value ?: emptyList()).apply {
                add(folderUi)
            }
            update(newList)
        }

        override fun folderListLiveData() = listFolderUiLiveData
    }
}