package com.example.flowers_shop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Flowers::class],
    version = 1,
    exportSchema = false
)

abstract class FlowerDatabase : RoomDatabase() {
    abstract fun flowerDao(): FlowerDao

    companion object {
        @Volatile
        private var instance: FlowerDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FlowerDatabase::class.java, "flowerDatabase.db"
            )
                .allowMainThreadQueries()
                .build()
    }
}