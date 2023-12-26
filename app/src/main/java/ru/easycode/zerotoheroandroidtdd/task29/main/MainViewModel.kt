package ru.easycode.zerotoheroandroidtdd.task29.main

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task29.folder.list.FoldersListScreen

class MainViewModel(private val navigation: Navigation.Mutable) : ViewModel(), Navigation.Read {

    fun init(firstRun: Boolean) {
        if (firstRun) navigation.update(FoldersListScreen)
    }

    override fun navigation() = navigation.navigation()
}