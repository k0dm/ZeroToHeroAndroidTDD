package ru.easycode.zerotoheroandroidtdd.task19

import androidx.lifecycle.LiveData

interface ProvideLiveData<T> {
    fun provideLiveData(): LiveData<T>
}