package ru.easycode.zerotoheroandroidtdd.task28.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM text_items")
    fun list(): List<ItemCache>

    @Insert(entity = ItemCache::class, onConflict = OnConflictStrategy.REPLACE)
    fun add(item: ItemCache)

    @Query("SELECT * FROM text_items WHERE id = :id")
    fun item(id: Long): ItemCache

    @Query("DELETE FROM text_items WHERE id = :id")
    fun delete(id: Long)
}

