package ru.easycode.zerotoheroandroidtdd.task29.main

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.task29.core.SingleLiveEvent

class Navigation {

    interface Update {
        fun update(screen: Screen)
    }

    interface Read {
        fun navigation(): LiveData<Screen>
    }

    interface Mutable : Update, Read

    class Base : Mutable {

        private val screenLiveData = SingleLiveEvent<Screen>()
        override fun update(screen: Screen) {
            screenLiveData.value = screen
        }

        override fun navigation() = screenLiveData
    }
}