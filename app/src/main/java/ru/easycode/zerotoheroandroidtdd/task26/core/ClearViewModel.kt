package ru.easycode.zerotoheroandroidtdd.task26.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {

    fun clearViewModel(clasz: Class<out ViewModel>)
}