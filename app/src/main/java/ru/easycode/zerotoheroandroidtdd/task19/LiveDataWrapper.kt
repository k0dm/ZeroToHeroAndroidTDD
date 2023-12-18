package ru.easycode.zerotoheroandroidtdd.task19

import androidx.lifecycle.LiveData

interface LiveDataWrapper {

    interface Observe{
        fun liveData(): LiveData<UiState>
    }

    interface Update{
        fun update(value: UiState)
    }

    interface Save{
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Mutable: Observe, Update, Save

    class Base : Mutable {

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