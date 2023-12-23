package ru.easycode.zerotoheroandroidtdd.task28.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Item(private val id: Long, private val text: String) {

    fun add(mutableLiveData: MutableLiveData<String>) {
        mutableLiveData.value = text
    }

    fun toUI() = ItemUi(id, text)
}