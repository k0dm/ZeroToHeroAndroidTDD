package ru.easycode.zerotoheroandroidtdd.task27.add.data

import ru.easycode.zerotoheroandroidtdd.task27.core.Item
import ru.easycode.zerotoheroandroidtdd.task27.core.Now

interface Repository {

    interface Read {
        fun list(): List<Item>
    }

    interface Add {
        fun add(value: String): Long
    }

    interface Delete {
        fun delete(id: Long)

        fun item(id: Long): Item
    }

    interface All : Add, Delete, Read

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now,
    ) : All {
        override fun add(value: String): Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }

        override fun item(id: Long) = dataSource.item(id).toItem()

        override fun list() = dataSource.list().map { it.toItem() }
        override fun delete(id: Long) = dataSource.delete(id)
    }
}