package ru.easycode.zerotoheroandroidtdd.task29.folder.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.NoteItemListBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.UiItemsDiffUtilCallback
import ru.easycode.zerotoheroandroidtdd.task29.note.core.NoteUi

class NoteAdapter(
    private val viewModel: FolderDetailsViewModel
) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private val list = ArrayList<NoteUi>()

    fun update(newList: List<NoteUi>) {
        val diffUtilCallback = UiItemsDiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteHolder(NoteItemListBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: NoteHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size

    inner class NoteHolder(private val binding: NoteItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(noteUi: NoteUi) {
            noteUi.show(binding)
            binding.noteTitleTextView.setOnClickListener {
                viewModel.editNote(noteUi)
            }
        }
    }
}