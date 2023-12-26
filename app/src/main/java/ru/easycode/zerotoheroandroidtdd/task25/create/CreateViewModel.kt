package ru.easycode.zerotoheroandroidtdd.task25.create

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task25.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.task25.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task25.core.Navigation
import ru.easycode.zerotoheroandroidtdd.task25.main.Screen

class CreateViewModel(
    private val addLiveDataWrapper: ListLiveDataWrapper.Add,
    private val navigation: Navigation.Update,
    private val clearViewModel: ClearViewModel,
) : ViewModel() {

    init {
        Log.d("k0dm", "init CVM")
    }

    fun add(text: CharSequence) {
        addLiveDataWrapper.add(text)
        comeback()
    }

    fun comeback() {
        clearViewModel.clear(this.javaClass)
        navigation.update(Screen.Pop)
    }
}
