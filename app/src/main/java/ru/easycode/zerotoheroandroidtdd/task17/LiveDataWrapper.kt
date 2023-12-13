package ru.easycode.zerotoheroandroidtdd.task17

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    fun liveData(): LiveData<UiState>

    fun update(value: UiState)

    fun save(bundleWrapper: BundleWrapper.Save)

    class Base : LiveDataWrapper {

        private val mutableLiveData = SingleLiveEvent<UiState>()

        override fun liveData(): LiveData<UiState> = mutableLiveData

        override fun update(value: UiState) {
            mutableLiveData.value = value
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            val uiState = mutableLiveData.value
            if (uiState != null)
                bundleWrapper.save(uiState)
        }
    }
}