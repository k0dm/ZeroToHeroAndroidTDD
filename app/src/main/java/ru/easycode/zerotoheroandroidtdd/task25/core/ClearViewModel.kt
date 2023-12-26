package ru.easycode.zerotoheroandroidtdd.task25.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {

    fun clear(viewModelClass: Class<out ViewModel>)
}
