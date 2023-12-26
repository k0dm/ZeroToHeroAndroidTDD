package ru.easycode.zerotoheroandroidtdd.task29.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = FolderCache::class,
            parentColumns = ["id"],
            childColumns = ["folder_id"]
        )
    ]
)
data class NoteCache(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "folder_id")
    val folderId: Long
)