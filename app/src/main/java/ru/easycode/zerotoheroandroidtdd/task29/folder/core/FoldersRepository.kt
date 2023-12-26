package ru.easycode.zerotoheroandroidtdd.task29.folder.core

import ru.easycode.zerotoheroandroidtdd.task29.core.FolderCache
import ru.easycode.zerotoheroandroidtdd.task29.core.FoldersDao
import ru.easycode.zerotoheroandroidtdd.task29.core.NotesDao
import ru.easycode.zerotoheroandroidtdd.task29.core.Now

interface FoldersRepository {

    interface Create {
        suspend fun createFolder(name: String): Long
    }

    interface ReadList {
        suspend fun folders(): List<Folder>
    }

    interface Edit {
        suspend fun delete(folderId: Long)
        suspend fun rename(folderId: Long, newName: String)
    }

    interface All : Create, ReadList, Edit

    class Base(
        private val now: Now,
        private val foldersDao: FoldersDao,
        private val notesDao: NotesDao,
    ) : All {
        override suspend fun createFolder(name: String) = now.timeInMillis().apply {
            foldersDao.insert(FolderCache(this, name))
        }

        override suspend fun folders() = foldersDao.folders().map { folderCache ->
            Folder(
                folderCache.id,
                folderCache.text,
                notesDao.notes(folderCache.id).size.toLong()
            )
        }

        override suspend fun delete(folderId: Long) {
            notesDao.deleteByFolderId(folderId)
            foldersDao.delete(folderId)
        }

        override suspend fun rename(folderId: Long, newName: String) =
            foldersDao.insert(FolderCache(folderId, newName))
    }
}