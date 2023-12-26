package ru.easycode.zerotoheroandroidtdd.task25.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ListElementBinding

class MyListAdapter : RecyclerView.Adapter<MyListAdapter.ListHolder>() {

    private val textList = ArrayList<CharSequence>()

    fun update(newList: ArrayList<CharSequence>) {
        val diff = DiffUtil.calculateDiff(MyDiffUtilCallback(textList, newList))
        textList.clear()
        textList.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        return ListHolder(ListElementBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = textList.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(textList[position])
    }

    class ListHolder(
        private val listElementBinding: ListElementBinding,
    ) : RecyclerView.ViewHolder(listElementBinding.root) {

        fun bind(text: CharSequence) {
            listElementBinding.elementTextView.text = text
        }
    }
}

class MyDiffUtilCallback(
    private val oldList: ArrayList<CharSequence>,
    private val newList: ArrayList<CharSequence>,
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