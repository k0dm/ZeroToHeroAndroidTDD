package ru.easycode.zerotoheroandroidtdd.task29.core

import androidx.lifecycle.LiveData

interface ProvideLiveData<T> {

    fun liveData(): LiveData<T>
}