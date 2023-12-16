package ru.easycode.zerotoheroandroidtdd.task25.core

interface Core {

    fun navigation(): Navigation.Mutable

    fun listDataWrapper(): ListLiveDataWrapper.All

    class Base() : Core {

        private val navigation = Navigation.Base()
        private val listLiveDataWrapper = ListLiveDataWrapper.Base()
        override fun navigation() = navigation

        override fun listDataWrapper() = listLiveDataWrapper
    }
}