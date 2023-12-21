package ru.easycode.zerotoheroandroidtdd.task27.core

import androidx.lifecycle.ViewModel

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(clasz: Class<out T>): T
}