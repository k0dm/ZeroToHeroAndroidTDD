package ru.easycode.zerotoheroandroidtdd.task29.note.core

import ru.easycode.zerotoheroandroidtdd.databinding.NoteItemListBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.CompareUi

data class NoteUi(
    private val id: Long,
    private val title: String,
    private val folderId: Long,
) : CompareUi<NoteUi> {

    fun id() = id

    fun show(binding: NoteItemListBinding) {
        binding.noteTitleTextView.text = title
    }

    override fun areItemsTheSame(uiItem: NoteUi) = uiItem.id == this.id
}

