package ru.easycode.zerotoheroandroidtdd.task24

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent

interface ListLiveDataWrapper {

    fun liveData(): LiveData<List<CharSequence>>

    fun add(new: CharSequence)

    fun save(bundle: BundleWrapper.Save)

    fun update(list: List<CharSequence>)

    class Base : ListLiveDataWrapper {

        private val mutableLiveData = SingleLiveEvent<List<CharSequence>>()

        override fun liveData(): LiveData<List<CharSequence>> = mutableLiveData

        override fun add(new: CharSequence) {
            val list = (mutableLiveData.value ?: emptyList()).toMutableList()
            list.add(new)
            mutableLiveData.value = list
        }

        override fun save(bundle: BundleWrapper.Save) {
            mutableLiveData.value?.let {
                bundle.save(ArrayList(it))
            }
        }

        override fun update(list: List<CharSequence>) {
            mutableLiveData.value = list
        }
    }
}