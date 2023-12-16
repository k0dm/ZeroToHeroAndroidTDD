package ru.easycode.zerotoheroandroidtdd.task22

import android.app.Application

class App: Application() {

    val mainViewModel: MainViewModel = MainViewModel()
}