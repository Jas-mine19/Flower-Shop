package com.example.flowers_shop.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "flower")
data class Flowers (
    @PrimaryKey( autoGenerate = true)
    @SerializedName("flowerid")
    var flowerId: Int,
    @SerializedName("flowername")
    val flowerName: String,
    @SerializedName("flowerimage")
    val imageURL:String?,
    @SerializedName("flowerprice")
    val flowerPrice:String,
    @SerializedName("flowerinformation")
    val flowerInformation:String,
    @SerializedName("isselected")
    val isFavorite: Boolean,
    @SerializedName("number_of_flower")
    val numberOfFlower:Int,
    @SerializedName("total_price")
    val totalPrice:Double
)