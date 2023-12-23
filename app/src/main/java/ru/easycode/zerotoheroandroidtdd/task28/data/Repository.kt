package ru.easycode.zerotoheroandroidtdd.task28.data

import ru.easycode.zerotoheroandroidtdd.task28.core.Now
import ru.easycode.zerotoheroandroidtdd.task28.main.Item

interface Repository {

    interface Read {
        fun list(): List<Item>
    }

    interface Add {
        fun add(value: String): Long
    }

    interface Change {

        fun item(id: Long): Item

        fun delete(id: Long)

        fun update(id: Long, newText: String)
    }

    interface All : Read, Add, Change

    class Base(private val dataSource: ItemsDao, private val now: Now) : All {
        override fun list() = dataSource.list().map { Item(it.id, it.text) }
        override fun add(value: String) =
            now.nowMillis().apply { dataSource.add(ItemCache(this, value)) }

        override fun item(id: Long) = dataSource.item(id).let { Item(it.id, it.text) }

        override fun delete(id: Long) = dataSource.delete(id)

        override fun update(id: Long, newText: String)  = dataSource.add(ItemCache(id, newText))
    }
}