package ru.easycode.zerotoheroandroidtdd.task29.note.core

import ru.easycode.zerotoheroandroidtdd.task29.core.NoteCache
import ru.easycode.zerotoheroandroidtdd.task29.core.NotesDao
import ru.easycode.zerotoheroandroidtdd.task29.core.Now

interface NotesRepository {

    interface Create {
        suspend fun createNote(folderId: Long, text: String): Long
    }
    interface ReadList {
        suspend fun noteList(folderId: Long): List<MyNote>
    }

    interface Edit {
        suspend fun note(noteId: Long): MyNote
        suspend fun deleteNote(noteId: Long)
        suspend fun renameNote(noteId: Long, newName: String)
    }

    interface All : Create, ReadList, Edit

    class Base(
        private val now: Now,
        private val dao: NotesDao,
    ) : All {
        override suspend fun createNote(folderId: Long, text: String) =
            now.timeInMillis().apply { dao.insert(NoteCache(this, text, folderId)) }

        override suspend fun note(noteId: Long) = dao.note(noteId).let {
            MyNote(it.id, it.text, it.folderId)
        }

        override suspend fun noteList(folderId: Long) = dao.notes(folderId).map {
            MyNote(it.id, it.text, it.folderId)
        }

        override suspend fun deleteNote(noteId: Long) = dao.delete(noteId)

        override suspend fun renameNote(noteId: Long, newName: String) =
            dao.insert(NoteCache(noteId, newName, dao.note(noteId).folderId))
    }
}