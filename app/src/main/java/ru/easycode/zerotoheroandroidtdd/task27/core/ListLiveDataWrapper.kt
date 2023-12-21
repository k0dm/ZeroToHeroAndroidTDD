package ru.easycode.zerotoheroandroidtdd.task27.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.task27.main.ItemUi

interface ListLiveDataWrapper {

    interface Add {
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface ProvideLiveData {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Update {
        fun update(value: List<ItemUi>)
    }

    interface All : Add, Delete, ProvideLiveData, Update

    class Base : All {

        private val itemsUiLiveDate = MutableLiveData<List<ItemUi>>()
        override fun add(value: ItemUi) {
            val oldList = itemsUiLiveDate.value ?: emptyList()
            val newList = ArrayList(oldList).apply { add(value) }
            itemsUiLiveDate.value = newList
        }

        override fun delete(item: ItemUi) {
            if (!itemsUiLiveDate.value.isNullOrEmpty()) {
                val newList = ArrayList(itemsUiLiveDate.value!!).apply {
                    remove(item)
                }
                itemsUiLiveDate.value = newList
            }
        }

        override fun liveData() = itemsUiLiveDate

        override fun update(value: List<ItemUi>) {
            itemsUiLiveDate.value = value
        }
    }
}