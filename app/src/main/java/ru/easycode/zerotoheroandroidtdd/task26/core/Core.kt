package ru.easycode.zerotoheroandroidtdd.task26.core

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.task26.main.Repository
import ru.easycode.zerotoheroandroidtdd.task26.main.data.ItemsDataBase

interface Core {

    fun repository(): Repository.Mutable

    fun listLiveDataWrapper(): ListLiveDataWrapper.All

    fun dispatcherMain(): CoroutineDispatcher

    fun dispatcherIO(): CoroutineDispatcher

    class Base(context: Context) : Core {


        private val dataBase = ItemsDataBase.getDatabase(context)
        private val repository = Repository.Base(dataBase.itemsDao(), Now.Base())
        private val listLiveDataWrapper = ListLiveDataWrapper.Base()
        private val dispatcherMain = Dispatchers.Main
        private val dispatcherIO = Dispatchers.IO
        override fun repository() = repository

        override fun listLiveDataWrapper() = listLiveDataWrapper

        override fun dispatcherMain() = dispatcherMain

        override fun dispatcherIO() = dispatcherIO
    }
}