package ru.easycode.zerotoheroandroidtdd.task27.add

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ListItemBinding
import ru.easycode.zerotoheroandroidtdd.task27.delete.DeleteDialogFragment
import ru.easycode.zerotoheroandroidtdd.task27.main.ItemUi

class ListItemsAdapter(
    private val supportFragmentManager: FragmentManager
) : RecyclerView.Adapter<ListItemsAdapter.ListItemViewHolder>() {

    private val listItems = ArrayList<ItemUi>()

    fun update(newList: List<ItemUi>) {
        val diffUtil = ListItemsDiffUtilCallback(listItems, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        listItems.clear()
        listItems.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context)),
            supportFragmentManager
        )
    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) =
        holder.bind(listItems[position])

    class ListItemViewHolder(
        private val binding: ListItemBinding,
        private val supportFragmentManager: FragmentManager,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemUi: ItemUi) {
            itemUi.show(binding.elementTextView)
            binding.elementTextView.setOnClickListener {
                DeleteDialogFragment.newInstance(itemUi).show(supportFragmentManager, "k0dm")
            }
        }
    }

    class ListItemsDiffUtilCallback(
        private val oldList: List<ItemUi>,
        private val newList: List<ItemUi>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].areItemsTheSame(newList[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}