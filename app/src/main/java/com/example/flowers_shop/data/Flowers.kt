package com.example.flowers_shop.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Flowers (
    @PrimaryKey( autoGenerate = true)
    @SerializedName("flowerid")
    val flowerId: Int,
    @SerializedName("flowername")
    val flowerName: String,
    @SerializedName("flowerimage")
    val imageURL:String?,
    @SerializedName("flowerprice")
    val flowerPrice:Int,
    @SerializedName("flowerinformation")
    val flowerInformation:String,
    @SerializedName("isselected")
    val isFavorite: Boolean
)