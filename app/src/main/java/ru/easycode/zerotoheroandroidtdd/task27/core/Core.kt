package ru.easycode.zerotoheroandroidtdd.task27.core

import android.content.Context
import androidx.room.Room
import ru.easycode.zerotoheroandroidtdd.task27.add.data.ItemsDataBase
import ru.easycode.zerotoheroandroidtdd.task27.add.data.Repository

interface Core {

    fun repository(): Repository.All

    fun listLiveDataWrapper(): ListLiveDataWrapper.All

    class Base(context: Context) : Core {

        private val dataBase by lazy {
            Room.databaseBuilder(
                context, ItemsDataBase::class.java, "items_database"
            ).build()
        }
        private val listLiveDataWrapper = ListLiveDataWrapper.Base()
        private val repository = Repository.Base(dataBase.itemsDao(), Now.Base())
        override fun repository() = repository

        override fun listLiveDataWrapper() = listLiveDataWrapper
    }
}