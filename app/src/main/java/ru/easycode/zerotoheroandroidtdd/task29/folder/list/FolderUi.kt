package ru.easycode.zerotoheroandroidtdd.task29.folder.list

import ru.easycode.zerotoheroandroidtdd.databinding.FolderItemListBinding
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderDetailsBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.CompareUi

data class FolderUi(
    private val id: Long,
    private val title: String,
    private var notesCount: Long,
) : CompareUi<FolderUi> {

    fun id() = id

    fun title() = title

    fun incrementNotesCount() = notesCount++

    fun decrementNotesCount() = notesCount--

    fun show(binding: FolderItemListBinding) {
        binding.folderTitleTextView.text = title
        binding.folderCountTextView.text = notesCount.toString()
    }

    fun show(binding: FragmentFolderDetailsBinding) {
        binding.folderNameTextView.text = title
        binding.notesCountTextView.text = notesCount.toString()
    }
    override fun areItemsTheSame(uiItem: FolderUi): Boolean = uiItem.id == this.id
}

