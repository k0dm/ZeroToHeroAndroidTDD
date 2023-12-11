package ru.easycode.zerotoheroandroidtdd.task22

import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var cachedContentTextViews = listOf<String>()
    fun saveContentViews(list: List<String>) {
        cachedContentTextViews = list
    }

    fun restoreViews() : List<String> = cachedContentTextViews
}