package ru.easycode.zerotoheroandroidtdd.task26.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ListLiveDataWrapper {

    interface Update {
        fun update(value: List<String>)
    }

    interface ProvideLiveData {
        fun liveData(): LiveData<List<String>>
    }

    interface Add {
        fun add(value: String)
    }

    interface Mutable : Update, ProvideLiveData

    interface All: Mutable, Add

    class Base : All {

        private val listTextLiveData = MutableLiveData<List<String>>()

        override fun update(value: List<String>) {
            listTextLiveData.value = value
        }

        override fun liveData(): LiveData<List<String>> = listTextLiveData

        override fun add(value: String) {
            val newList = ArrayList(listTextLiveData.value ?: emptyList()).apply {
                add(value)
            }
            listTextLiveData.value = newList
        }
    }
}