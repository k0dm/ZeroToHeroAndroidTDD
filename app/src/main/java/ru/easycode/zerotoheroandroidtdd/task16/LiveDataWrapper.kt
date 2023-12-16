package ru.easycode.zerotoheroandroidtdd.task16

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    fun liveData(): LiveData<UiState>

    fun update(value: UiState)

    class Base : LiveDataWrapper {

        private val mutableLiveData = MutableLiveData<UiState>()

        override fun liveData() = mutableLiveData

        override fun update(value: UiState) {
            mutableLiveData.value = value
        }
    }
}