package ru.easycode.zerotoheroandroidtdd.task16

import android.app.Application
import androidx.lifecycle.ViewModel

class App: Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())
    }

}