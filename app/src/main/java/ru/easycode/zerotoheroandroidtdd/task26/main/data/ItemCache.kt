package ru.easycode.zerotoheroandroidtdd.task26.main.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "text_table")
data class ItemCache(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "text") val text: String,
)