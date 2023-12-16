package ru.easycode.zerotoheroandroidtdd.task25.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    interface ProvideLiveData {
        fun liveData(): LiveData<List<CharSequence>>
    }

    interface Update {
        fun update(value: List<CharSequence>)
    }

    interface Add {
        fun add(source: CharSequence)
    }

    interface Save {

        fun save(bundleWrapper: BundleWrapper.Save)
    }


    interface Mutable : ProvideLiveData, Add, Update, Save

    interface All : Mutable

    class Base() : All {

        private val listLiveData = MutableLiveData<List<CharSequence>>()
        override fun liveData(): LiveData<List<CharSequence>> = listLiveData

        override fun update(value: List<CharSequence>) {
            listLiveData.value = value
        }

        override fun add(source: CharSequence) {
            val newList = ArrayList(listLiveData.value ?: emptyList())
            newList.add(source)
            listLiveData.value = newList
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            bundleWrapper.save(ArrayList(listLiveData.value ?: ArrayList()))
        }
    }
}

