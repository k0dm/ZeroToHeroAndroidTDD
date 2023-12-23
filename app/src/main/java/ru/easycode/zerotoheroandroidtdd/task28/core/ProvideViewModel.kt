package ru.easycode.zerotoheroandroidtdd.task28.core

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import ru.easycode.zerotoheroandroidtdd.task28.data.ItemsDataBase
import ru.easycode.zerotoheroandroidtdd.task28.data.Repository

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clasz: Class<out T>): T
}

interface Core {

    fun repository(): Repository.All

    fun listLiveDataWrapper(): ListLiveDataWrapper.All

    class Base(context: Context) : Core {

        private val database by lazy {
            Room.databaseBuilder(context, ItemsDataBase::class.java, "items_database").build()
        }
        private val repository = Repository.Base(database.itemsDao(), Now.Base())
        private val listLiveDataWrapper = ListLiveDataWrapper.Base()

        override fun repository() = repository

        override fun listLiveDataWrapper() = listLiveDataWrapper
    }
}