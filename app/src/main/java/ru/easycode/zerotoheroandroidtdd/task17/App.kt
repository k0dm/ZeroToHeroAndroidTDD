package ru.easycode.zerotoheroandroidtdd.task17

import android.app.Application

class App: Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())
    }

}