package ru.easycode.zerotoheroandroidtdd.task25.main

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task25.core.Navigation
import ru.easycode.zerotoheroandroidtdd.task25.list.ListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
) : ViewModel() {

    fun init(firstRun: Boolean) {
        if(firstRun) {
            navigation.update((ListScreen))
        }
    }

    fun navigation() = navigation.liveData()
}