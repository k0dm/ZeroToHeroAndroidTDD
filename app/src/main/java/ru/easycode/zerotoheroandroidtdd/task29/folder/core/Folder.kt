package ru.easycode.zerotoheroandroidtdd.task29.folder.core

import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FolderUi

data class Folder(
    private val id: Long,
    private val title: String,
    private val notesCount: Long,
) {

    fun toUi() = FolderUi(id, title, notesCount)
}