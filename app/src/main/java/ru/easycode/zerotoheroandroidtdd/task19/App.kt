package ru.easycode.zerotoheroandroidtdd.task19

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.easycode.zerotoheroandroidtdd.task19.data.Repository
import ru.easycode.zerotoheroandroidtdd.task19.data.SimpleService

class App : Application() {


    lateinit var mainViewModel: MainViewModel
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.google.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate() {
        super.onCreate()
        mainViewModel = MainViewModel(
            LiveDataWrapper.Base(),
            Repository.Base(retrofit.create(SimpleService::class.java))
        )
    }
}