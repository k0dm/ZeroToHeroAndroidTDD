package ru.easycode.zerotoheroandroidtdd.task27.add.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.easycode.zerotoheroandroidtdd.task27.core.Item

@Entity(tableName = "text_items")
data class ItemCache(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String,
) {

    fun toItem() = Item(id, text)
}