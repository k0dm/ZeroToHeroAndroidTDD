package ru.easycode.zerotoheroandroidtdd.task26.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ListItemBinding

class ListItemsAdapter : RecyclerView.Adapter<ListItemsAdapter.ListItemViewHolder>() {

    private val listItems = ArrayList<String>()

    fun update(newList: List<String>) {
        val diffUtil = ListItemsDiffUtilCallback(listItems, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        listItems.clear()
        listItems.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) =
        holder.bind(listItems[position])


    class ListItemViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.elementTextView.text = text
        }
    }

    class ListItemsDiffUtilCallback(
        private val oldList: List<String>,
        private val newList: List<String>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}