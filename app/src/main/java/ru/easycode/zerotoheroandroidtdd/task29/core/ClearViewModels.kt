package ru.easycode.zerotoheroandroidtdd.task29.core

import androidx.lifecycle.ViewModel

interface ClearViewModels {

    fun clear(vararg viewModelClasses: Class<out ViewModel>)
}