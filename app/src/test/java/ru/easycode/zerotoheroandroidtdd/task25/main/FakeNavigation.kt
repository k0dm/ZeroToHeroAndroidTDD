package ru.easycode.zerotoheroandroidtdd.task25.main

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.task25.core.Navigation

interface FakeNavigation : Navigation.Mutable {

    fun checkUpdateCalled(expected: List<Screen>)

    class Base : FakeNavigation {

        private val callsList = mutableListOf<Screen>()

        override fun checkUpdateCalled(expected: List<Screen>) {
            assertEquals(expected, callsList)
        }

        override fun update(value: Screen) {
            callsList.add(value)
        }

        override fun liveData(): LiveData<Screen> {
            throw IllegalStateException("not used")
        }
    }
}