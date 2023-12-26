package ru.easycode.zerotoheroandroidtdd.task29.core

import androidx.recyclerview.widget.DiffUtil

class UiItemsDiffUtilCallback<T>(
    private val oldList: List<CompareUi<T>>,
    private val newList: List<CompareUi<T>>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].areItemsTheSame(newList[newItemPosition] as T)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}