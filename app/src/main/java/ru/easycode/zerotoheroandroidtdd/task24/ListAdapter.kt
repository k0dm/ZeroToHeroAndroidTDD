package ru.easycode.zerotoheroandroidtdd.task24

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ListItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListHolder>() {


    private val textList = ArrayList<CharSequence>()

    fun updateList(list: List<CharSequence>) {
        val diffCallback = ListDiffUtil(textList, list)
        val diff = DiffUtil.calculateDiff(diffCallback)
        textList.clear()
        textList.addAll(list)
        diff.dispatchUpdatesTo(this)
    }

    class ListHolder(private val itemList: ListItemBinding) :
        RecyclerView.ViewHolder(itemList.root) {

        fun bind(txt: CharSequence) {
            itemList.elementTextView.text = txt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder =
        ListHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))


    override fun getItemCount() = textList.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(textList[position])
    }
}


class ListDiffUtil(
    private val oldList: List<CharSequence>,
    private val newList: List<CharSequence>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}