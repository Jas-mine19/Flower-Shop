package com.example.flowers_shop.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCategory(category: Category)

    @Query("SELECT * FROM category")
    fun readAllData(): List<Category>

}