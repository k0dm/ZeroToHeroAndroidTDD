package ru.easycode.zerotoheroandroidtdd.task29.core

import android.content.Context
import androidx.room.Room
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.task29.folder.details.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.main.Navigation
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NotesRepository

interface Core {

    fun foldersRepository(): FoldersRepository.All
    fun notesRepository(): NotesRepository.All
    fun folderListLiveDataWrapper(): FolderListLiveDataWrapper.All
    fun noteListLiveDataWrapper(): NoteListLiveDataWrapper.All
    fun folderLiveDataWrapper(): FolderLiveDataWrapper.All
    fun noteLiveDataWrapper(): NoteLiveDataWrapper.ReadAndUpdate
    fun navigation(): Navigation.Mutable
    fun clear(): ClearViewModels

    class Base(context: Context, private val clear: ClearViewModels) : Core {

        private val database = Room.databaseBuilder(
            context, AppDataBase::class.java, "my_notes_database"
        ).build()
        private val now = Now.Base()
        private val folderRepository =
            FoldersRepository.Base(now, database.foldersDao(), database.notesDao())
        private val noteRepository = NotesRepository.Base(now, database.notesDao())

        private val folderListLiveDataWrapper = FolderListLiveDataWrapper.Base()
        private val notesListLiveDataWrapper = NoteListLiveDataWrapper.Base()
        private val folderLiveDataWrapper = FolderLiveDataWrapper.Base()
        private val noteLiveDataWrapper = NoteLiveDataWrapper.Base()

        private val navigation = Navigation.Base()
        override fun foldersRepository() = folderRepository
        override fun notesRepository() = noteRepository
        override fun folderListLiveDataWrapper() = folderListLiveDataWrapper
        override fun noteListLiveDataWrapper() = notesListLiveDataWrapper
        override fun folderLiveDataWrapper() = folderLiveDataWrapper
        override fun noteLiveDataWrapper() = noteLiveDataWrapper
        override fun navigation() = navigation
        override fun clear() = clear
    }
}