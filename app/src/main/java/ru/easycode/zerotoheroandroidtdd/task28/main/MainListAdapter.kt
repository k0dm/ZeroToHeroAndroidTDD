package ru.easycode.zerotoheroandroidtdd.task28.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemListBinding
import ru.easycode.zerotoheroandroidtdd.task28.details.DetailBottomSheetFragment

class MainListAdapter(private val supportFragmentManager: FragmentManager) :
    RecyclerView.Adapter<MainListAdapter.ItemViewHolder>() {

    private val list = ArrayList<ItemUi>()

    fun update(newList: List<ItemUi>) {
        val diffUtilCallback = ListItemsDiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    inner class ItemViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemUi: ItemUi) {
            itemUi.show(binding.elementTextView)
            binding.elementTextView.setOnClickListener {
                DetailBottomSheetFragment.newInstance(itemUi).show(supportFragmentManager, "k0dm")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(list[position])

    class ListItemsDiffUtilCallback(
        private val oldList: List<ItemUi>,
        private val newList: List<ItemUi>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].areItemsSame(newList[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}