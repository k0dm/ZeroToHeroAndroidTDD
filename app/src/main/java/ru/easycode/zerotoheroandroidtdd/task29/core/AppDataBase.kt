package ru.easycode.zerotoheroandroidtdd.task29.core

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ FolderCache::class, NoteCache::class,]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    abstract fun foldersDao(): FoldersDao
}
