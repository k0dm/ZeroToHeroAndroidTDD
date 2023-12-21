package ru.easycode.zerotoheroandroidtdd.task27.add.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM text_items")
    fun list(): List<ItemCache>

    @Query("SELECT * FROM text_items WHERE id = :id")
    fun item(id: Long): ItemCache

    @Insert(entity = ItemCache::class)
    fun add(item: ItemCache)

    @Query("DELETE FROM text_items WHERE ID = :id")
    fun delete(id: Long)
}