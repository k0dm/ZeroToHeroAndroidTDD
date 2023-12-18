package ru.easycode.zerotoheroandroidtdd.task22

import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var cachedContentViews = listOf<View>()
    fun saveContentViews(list: List<View>) {
        cachedContentViews = list
    }

    fun restoreViews() : List<View> = cachedContentViews
}