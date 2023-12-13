package ru.easycode.zerotoheroandroidtdd.task24

import android.app.Application

class App: Application() {

    val viewModel = MainViewModel(ListLiveDataWrapper.Base())
}