package ru.easycode.zerotoheroandroidtdd.task25.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.task25.main.Screen

interface Navigation {

    interface Update{
        fun update(value: Screen)
    }

    interface Observe{
        fun liveData(): LiveData<Screen>
    }

    interface Mutable: Update, Observe

    class Base(): Mutable{

        private val screenLiveData = SingleLiveEvent<Screen>()
        override fun update(value: Screen) {
            screenLiveData.value = value
        }

        override fun liveData(): LiveData<Screen> = screenLiveData
    }
}