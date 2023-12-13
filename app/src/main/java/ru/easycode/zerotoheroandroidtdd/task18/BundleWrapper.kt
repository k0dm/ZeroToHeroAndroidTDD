package ru.easycode.zerotoheroandroidtdd.task18

import android.os.Bundle

interface BundleWrapper {

    interface Save {

        fun save(uiState: UiState)
    }

    interface Restore {

        fun restore(): UiState
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle,
        private val key: String = "uiStateKey",
    ) : Mutable {
        override fun save(uiState: UiState) {
            bundle.putSerializable(key, uiState)
        }

        override fun restore(): UiState {
            val result = bundle.getSerializable(key)
            return if (result != null) {
                result as UiState
            } else {
                UiState.Empty
            }
        }
    }
}
