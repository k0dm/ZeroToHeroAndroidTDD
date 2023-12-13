package ru.easycode.zerotoheroandroidtdd.task17

import kotlinx.coroutines.delay

interface Repository {

    suspend fun load()

    class Base : Repository {
        override suspend fun load() = delay(2000)
    }
}