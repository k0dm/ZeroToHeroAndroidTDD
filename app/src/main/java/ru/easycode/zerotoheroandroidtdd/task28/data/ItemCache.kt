package ru.easycode.zerotoheroandroidtdd.task28.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_items")
data class ItemCache(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String
)