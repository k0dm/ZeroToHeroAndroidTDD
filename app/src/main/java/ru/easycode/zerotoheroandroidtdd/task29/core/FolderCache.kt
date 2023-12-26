package ru.easycode.zerotoheroandroidtdd.task29.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderCache(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo("text")
    val text: String
)