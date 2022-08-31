package com.example.flowers_shop.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Basket(
    @PrimaryKey( autoGenerate = true)
    @SerializedName("basketid")
    val basketId: Int,
    @SerializedName("flowerid")
    val flowerId:Int,
    @SerializedName("userid")
    val userId:Int,
    val isSelected:Boolean = false
)
