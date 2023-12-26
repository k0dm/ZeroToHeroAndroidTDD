package ru.easycode.zerotoheroandroidtdd.task29.folder.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.FolderItemListBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.UiItemsDiffUtilCallback

class FolderAdapter(
    private val viewModel: FolderListViewModel
) : RecyclerView.Adapter<FolderAdapter.FolderHolder>() {

    private val list = ArrayList<FolderUi>()

    fun update(newList: List<FolderUi>) {
        val diffUtilCallback = UiItemsDiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FolderHolder(FolderItemListBinding.inflate(LayoutInflater.from(parent.context)))


    override fun onBindViewHolder(holder: FolderHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class FolderHolder(private val binding: FolderItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(folderUi: FolderUi) {
            folderUi.show(binding)
            binding.folderLinearLayout.setOnClickListener {
                viewModel.folderDetails(folderUi)
            }
        }
    }
}