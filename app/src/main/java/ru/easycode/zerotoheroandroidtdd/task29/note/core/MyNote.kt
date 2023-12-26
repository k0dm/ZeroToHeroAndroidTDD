package ru.easycode.zerotoheroandroidtdd.task29.note.core

data class MyNote(
    private val id: Long,
    private val title: String,
    private val folderId: Long,
) {

    fun toUi() = NoteUi(id, title, folderId)

    fun update(noteLiveDataWrapper: NoteLiveDataWrapper.ReadAndUpdate) =
        noteLiveDataWrapper.update(title)
}