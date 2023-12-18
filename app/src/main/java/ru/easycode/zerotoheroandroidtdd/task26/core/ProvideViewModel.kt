package ru.easycode.zerotoheroandroidtdd.task26.core

import androidx.lifecycle.ViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clasz: Class<T>): T
}