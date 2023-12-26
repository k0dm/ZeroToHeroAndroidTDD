package ru.easycode.zerotoheroandroidtdd.folder.details

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.core.Order
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderUi

interface FakeFolderLiveDataWrapper : FolderLiveDataWrapper.Mutable {

    fun check(expected: FolderUi)

    class Base(private val order: Order) : FakeFolderLiveDataWrapper {

        private lateinit var actual: FolderUi

        override fun check(expected: FolderUi) {
            assertEquals(expected, actual)
        }

        override fun update(folder: FolderUi) {
            order.add(UPDATE_FOLDER_LIVEDATA)
            actual = folder
        }

        override fun folderId(): Long {
            return actual.id()
        }

        override fun folderName(): String {
            return ""
        }

        override fun folderLiveData(): LiveData<FolderUi> {
            throw IllegalStateException("don't use in unit test")
        }
    }
}

const val UPDATE_FOLDER_LIVEDATA = "FolderLiveDataWrapper.Mutable#update"