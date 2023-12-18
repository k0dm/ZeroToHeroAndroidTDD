package ru.easycode.zerotoheroandroidtdd.task26.main.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM TEXT_TABLE")
     fun list(): List<ItemCache>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun add(item: ItemCache)
}