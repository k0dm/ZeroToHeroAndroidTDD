package ru.easycode.zerotoheroandroidtdd.task27.add.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(version = 1, entities = [ItemCache::class])
abstract class ItemsDataBase : RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

    companion object {

        @Volatile
        private var INSTANCE: ItemsDataBase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope): ItemsDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemsDataBase::class.java,
                    "items_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}