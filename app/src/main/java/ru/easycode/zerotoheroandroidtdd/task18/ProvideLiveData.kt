package ru.easycode.zerotoheroandroidtdd.task18

import androidx.lifecycle.LiveData

interface ProvideLiveData<T> {
    fun provideLiveData(): LiveData<T>
}