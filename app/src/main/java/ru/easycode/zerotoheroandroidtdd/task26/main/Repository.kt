package ru.easycode.zerotoheroandroidtdd.task26.main

import ru.easycode.zerotoheroandroidtdd.task26.core.Now
import ru.easycode.zerotoheroandroidtdd.task26.main.data.ItemCache
import ru.easycode.zerotoheroandroidtdd.task26.main.data.ItemsDao

interface Repository {

    interface Read {
        suspend fun list(): List<String>
    }

    interface Add {
        suspend fun add(value: String)
    }

    interface Mutable : Read, Add

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : Mutable {
        override suspend fun list(): List<String> = dataSource.list().map { it.text }
        override suspend fun add(value: String) {

            dataSource.add(ItemCache(id = now.nowMillis(), text = value))
        }
    }
}