package com.example.flowers_shop.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category")
data class Category(
    @PrimaryKey( autoGenerate = true)
    @SerializedName("categoryid")
    val categoryId:Int,
    @SerializedName("categoryname")
    val categoryName:String,
    @SerializedName("categoryimage")
    val imageUrl:String?,
    var isSelected:Boolean = false
)