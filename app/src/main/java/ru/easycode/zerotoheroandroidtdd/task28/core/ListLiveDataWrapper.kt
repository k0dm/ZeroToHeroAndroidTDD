package ru.easycode.zerotoheroandroidtdd.task28.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.task28.main.ItemUi

interface ListLiveDataWrapper {

    interface Add {
        fun add(value: ItemUi)
    }

    interface Change {
        fun update(item: ItemUi)

        fun update(list: List<ItemUi>)

        fun delete(item: ItemUi)
    }

    interface ProvideLiveData {
        fun liveData(): LiveData<List<ItemUi>>
    }


    interface All : Add, Change, ProvideLiveData

    class Base() : All {

        private val listLiveData = MutableLiveData<List<ItemUi>>()
        override fun add(value: ItemUi) {
            val list = listLiveData.value ?: emptyList()
            listLiveData.value = ArrayList(list).apply { add(value) }
        }

        override fun update(item: ItemUi) {
            val list = ArrayList(listLiveData.value ?: emptyList())

            val index = list.indexOfFirst { it.areItemsSame(item) }
            if (index != -1) {
                list[index] = item
                listLiveData.value = list
            } else {
                list.add(item)
                listLiveData.value = list
            }
        }

        override fun update(list: List<ItemUi>) {
            listLiveData.value = list
        }

        override fun delete(item: ItemUi) {
            val list = ArrayList(listLiveData.value ?: emptyList())
            listLiveData.value = list.apply { remove(item) }
        }

        override fun liveData(): LiveData<List<ItemUi>> = listLiveData
    }
}