package ru.easycode.zerotoheroandroidtdd.task15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    fun update(value: UiState)

    fun liveData(): LiveData<UiState>

    class Base : LiveDataWrapper {

        private val mutableLiveData = MutableLiveData<UiState>()

        override fun update(value: UiState) {
            mutableLiveData.value = value
        }

        override fun liveData(): LiveData<UiState> {
            return mutableLiveData
        }
    }
}