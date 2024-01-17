package com.example.flowers_shop.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flowers_shop.models.Flowers

@Dao
interface FlowerDao {
    @Query("SELECT * FROM flower")
    fun readData() : List<Flowers>

    @Query("SELECT * FROM flower WHERE flowerName LIKE :searchQuery")
    fun searchData(searchQuery:String): List<Flowers>

    @Query("SELECT * FROM flower WHERE flowerId LIKE :id")
    fun findById(id: Int): Flowers

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<Flowers>)

    @Query("DELETE FROM flower")
    fun deleteAll()
}